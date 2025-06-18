package Geometri.threading;

import java.util.concurrent.*;

public class ThreadExecutor {
    private static ThreadExecutor instance;
    private final ExecutorService executor;
    private final int poolSize;
    private boolean isShutdown;

    // Private constructor (Singleton)
    private ThreadExecutor(int poolSize) {
        this.poolSize = poolSize;
        this.executor = Executors.newFixedThreadPool(poolSize);
        this.isShutdown = false;
    }

    // Ambil instance Singleton
    public static ThreadExecutor getInstance() {
        if (instance == null) {
            instance = new ThreadExecutor(Runtime.getRuntime().availableProcessors());
        }
        return instance;
    }

    // Submit Runnable task
    public Future<?> submitTask(Runnable task) {
        if (isShutdown) throw new IllegalStateException("Executor sudah shutdown");
        return executor.submit(task);
    }

    // Submit Callable task
    public <T> Future<T> submitTask(Callable<T> task) {
        if (isShutdown) throw new IllegalStateException("Executor sudah shutdown");
        return executor.submit(task);
    }

    // Shutdown executor
    public void shutdownAndAwait() {
        isShutdown = true;
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // Cek status
    public boolean isShutdown() {
        return isShutdown;
    }

    public int getPoolSize() {
        return poolSize;
    }
}
