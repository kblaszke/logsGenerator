package pl.blaszak.logsGenerator.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class LogsGeneratorThreadExecutor {

    private final ThreadPoolExecutor executor;

    private LogsGeneratorThreadExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public static LogsGeneratorThreadExecutor newInstance(int threadsNumber) {
        return new LogsGeneratorThreadExecutor((ThreadPoolExecutor) Executors.newFixedThreadPool(threadsNumber));
    }

    public Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
