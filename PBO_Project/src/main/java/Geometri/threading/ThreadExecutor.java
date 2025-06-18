package Geometri.threading;

import java.util.concurrent.*;
import java.util.*;

public class ThreadExecutor {
    private final ExecutorService executor;
    private final List<Future<?>> futures = new ArrayList<>();

    public ThreadExecutor(int poolSize) {
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public void submitTask(Runnable task) {
        // Bungkus Runnable dengan handler exception
        Runnable safeTask = () -> {
            try {
                task.run();
            } catch (Exception e) {
                System.err.println("[ERROR] exception in " + task + ": " + e);
                e.printStackTrace();
            }
        };
        futures.add(executor.submit(safeTask));
    }

    public void shutdownAndAwait() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void cancelAll() {
        for (Future<?> f : futures) {
            if (!f.isDone()) f.cancel(true);
        }
    }
}
