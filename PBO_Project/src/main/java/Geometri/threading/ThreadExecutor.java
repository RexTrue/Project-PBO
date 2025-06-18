package Geometri.threading;

import java.util.concurrent.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ThreadExecutor {
    private static volatile ThreadExecutor instance;

    private final ExecutorService executor;
    private final List<Future<?>> futures;
    private final Set<ThreadExecutionListener> listeners;
    private final Logger logger;

    private final int poolSize;
    private final long shutdownTimeout;
    private volatile boolean isShutdown;

    private ThreadExecutor(int poolSize, long shutdownTimeout) {
        this.poolSize = poolSize;
        this.shutdownTimeout = shutdownTimeout;
        this.executor = Executors.newFixedThreadPool(poolSize);
        this.futures = Collections.synchronizedList(new ArrayList<>());
        this.listeners = Collections.synchronizedSet(new HashSet<>());
        this.logger = Logger.getLogger(ThreadExecutor.class.getName());
        this.isShutdown = false;
    }

    public static ThreadExecutor getInstance() {
        if (instance == null) {
            synchronized (ThreadExecutor.class) {
                if (instance == null) {
                    instance = new ThreadExecutor(Runtime.getRuntime().availableProcessors(), 60);
                }
            }
        }
        return instance;
    }

    public static class Builder {
        private int poolSize = Runtime.getRuntime().availableProcessors();
        private long shutdownTimeout = 60;
        
        public Builder poolSize(int poolSize) {
            this.poolSize = poolSize;
            return this;
        }
        
        public Builder shutdownTimeout(long timeout) {
            this.shutdownTimeout = timeout;
            return this;
        }
        
        public ThreadExecutor build() {
            return new ThreadExecutor(poolSize, shutdownTimeout);
        }
    }

    public interface ThreadExecutionListener {
        void onTaskSubmitted(Runnable task);
        void onTaskCompleted(Runnable task, Object result);
        void onTaskFailed(Runnable task, Exception exception);
        void onExecutorShutdown();
    }

    public interface TaskStrategy {
        void execute();
        String getTaskName();
        int getPriority();
    }

    public void addListener(ThreadExecutionListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ThreadExecutionListener listener) {
        listeners.remove(listener);
    }

    private void notifyTaskSubmitted(Runnable task) {
        for (ThreadExecutionListener listener : listeners) {
            try {
                listener.onTaskSubmitted(task);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error notifying listener", e);
            }
        }
    }
    
    private void notifyTaskCompleted(Runnable task, Object result) {
        for (ThreadExecutionListener listener : listeners) {
            try {
                listener.onTaskCompleted(task, result);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error notifying listener", e);
            }
        }
    }
    
    private void notifyTaskFailed(Runnable task, Exception exception) {
        for (ThreadExecutionListener listener : listeners) {
            try {
                listener.onTaskFailed(task, exception);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error notifying listener", e);
            }
        }
    }
    
    private void notifyExecutorShutdown() {
        for (ThreadExecutionListener listener : listeners) {
            try {
                listener.onExecutorShutdown();
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error notifying listener", e);
            }
        }
    }

    public Future<?> submitTask(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("Executor is shutdown");
        }
        
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        Runnable enhancedTask = createEnhancedTask(task);
        
        notifyTaskSubmitted(task);
        
        Future<?> future = executor.submit(enhancedTask);
        futures.add(future);
        
        return future;
    }

    public <T> Future<T> submitTask(Callable<T> task) {
        if (isShutdown) {
            throw new IllegalStateException("Executor is shutdown");
        }
        
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        
        Callable<T> enhancedTask = createEnhancedCallable(task);
        
        notifyTaskSubmitted(() -> {
            try {
                enhancedTask.call();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in callable task", e);
            }
        });
        
        Future<T> future = executor.submit(enhancedTask);
        futures.add(future);
        
        return future;
    }

    private Runnable createEnhancedTask(Runnable task) {
        return () -> {
            long startTime = System.currentTimeMillis();
            try {
                task.run();
                long executionTime = System.currentTimeMillis() - startTime;
                logger.info("Task completed successfully in " + executionTime + "ms");
                notifyTaskCompleted(task, null);
            } catch (Exception e) {
                long executionTime = System.currentTimeMillis() - startTime;
                logger.log(Level.SEVERE, "Task failed after " + executionTime + "ms", e);
                notifyTaskFailed(task, e);
            }
        };
    }

    private <T> Callable<T> createEnhancedCallable(Callable<T> task) {
        return () -> {
            long startTime = System.currentTimeMillis();
            try {
                T result = task.call();
                long executionTime = System.currentTimeMillis() - startTime;
                logger.info("Callable task completed successfully in " + executionTime + "ms");
                notifyTaskCompleted(() -> {}, result);
                return result;
            } catch (Exception e) {
                long executionTime = System.currentTimeMillis() - startTime;
                logger.log(Level.SEVERE, "Callable task failed after " + executionTime + "ms", e);
                notifyTaskFailed(() -> {}, e);
                throw e;
            }
        };
    }

    public Future<?> submitTaskWithPriority(TaskStrategy taskStrategy) {
        return submitTask(() -> {
            Thread.currentThread().setPriority(taskStrategy.getPriority());
            taskStrategy.execute();
        });
    }

    public List<Future<?>> submitBatch(List<Runnable> tasks) {
        List<Future<?>> batchFutures = new ArrayList<>();
        for (Runnable task : tasks) {
            batchFutures.add(submitTask(task));
        }
        return batchFutures;
    }

    public void shutdownAndAwait() {
        if (isShutdown) {
            return;
        }
        
        isShutdown = true;
        logger.info("Shutting down ThreadExecutor...");
        
        executor.shutdown();
        try {
            if (!executor.awaitTermination(shutdownTimeout, TimeUnit.SECONDS)) {
                logger.warning("Forcing shutdown after timeout");
                executor.shutdownNow();
                
                if (!executor.awaitTermination(shutdownTimeout, TimeUnit.SECONDS)) {
                    logger.severe("Executor did not terminate");
                }
            }
        } catch (InterruptedException ex) {
            logger.log(Level.WARNING, "Shutdown interrupted", ex);
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        } finally {
            notifyExecutorShutdown();
            logger.info("ThreadExecutor shutdown completed");
        }
    }

    public void cancelAll() {
        int cancelledCount = 0;
        for (Future<?> future : futures) {
            if (!future.isDone()) {
                if (future.cancel(true)) {
                    cancelledCount++;
                }
            }
        }
        logger.info("Cancelled " + cancelledCount + " pending tasks");
    }

    public ExecutionStats getExecutionStats() {
        int completedCount = 0;
        int pendingCount = 0;
        int cancelledCount = 0;
        
        for (Future<?> future : futures) {
            if (future.isDone()) {
                if (future.isCancelled()) {
                    cancelledCount++;
                } else {
                    completedCount++;
                }
            } else {
                pendingCount++;
            }
        }
        
        return new ExecutionStats(completedCount, pendingCount, cancelledCount, poolSize);
    }

    public static class ExecutionStats {
        private final int completedTasks;
        private final int pendingTasks;
        private final int cancelledTasks;
        private final int poolSize;
        
        public ExecutionStats(int completedTasks, int pendingTasks, int cancelledTasks, int poolSize) {
            this.completedTasks = completedTasks;
            this.pendingTasks = pendingTasks;
            this.cancelledTasks = cancelledTasks;
            this.poolSize = poolSize;
        }

        public int getCompletedTasks() { return completedTasks; }
        public int getPendingTasks() { return pendingTasks; }
        public int getCancelledTasks() { return cancelledTasks; }
        public int getPoolSize() { return poolSize; }
        public int getTotalTasks() { return completedTasks + pendingTasks + cancelledTasks; }
        
        @Override
        public String toString() {
            return String.format("ExecutionStats{completed=%d, pending=%d, cancelled=%d, poolSize=%d}", 
                               completedTasks, pendingTasks, cancelledTasks, poolSize);
        }
    }

    public boolean isShutdown() {
        return isShutdown;
    }

    public int getPoolSize() {
        return poolSize;
    }
}
