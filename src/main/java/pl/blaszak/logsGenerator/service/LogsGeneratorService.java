package pl.blaszak.logsGenerator.service;

import pl.blaszak.logsGenerator.threads.LogsGeneratorThreadExecutor;
import pl.blaszak.logsGenerator.threads.LogsGeneratorWorker;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogsGeneratorService {

    private final TextService textService;
    private final LogsGeneratorThreadExecutor threadExecutor;
    private int threadsNumber;
    private int logsLineNumber;
    private long threadsMaxTimeout;

    public LogsGeneratorService(int threadsNumber, int logsLineNumber, long threadsMaxTimeout, LogsGeneratorThreadExecutor threadExecutor, TextService textService) {
        this.threadsNumber = threadsNumber;
        this.logsLineNumber = logsLineNumber;
        this.threadsMaxTimeout = threadsMaxTimeout;
        this.threadExecutor = threadExecutor;
        this.textService = textService;
    }

    public void setEnv(String[] args) {
        try {
            int threadsNumber = Integer.parseInt(args[0]);
            int logLinesNumber = Integer.parseInt(args[1]);
            long threadsMaxTimeout = Long.parseLong(args[2]);
            setEnv(threadsNumber, logLinesNumber, threadsMaxTimeout);
        } catch (NumberFormatException ex) {

        }
    }

    public void setEnv(int threadsNumber, int logsLineNumber, long threadsMaxTimeout) {
        this.threadsNumber = threadsNumber;
        this.logsLineNumber = logsLineNumber;
        this.threadsMaxTimeout = threadsMaxTimeout;
    }

    public void run() throws ExecutionException, InterruptedException {
        List<Future<?>> futures = IntStream.range(0, threadsNumber).boxed()
                .map(i -> createWorker())
                .map(w -> threadExecutor.submit(w))
                .collect(Collectors.toList());
        for(Future future: futures) {
            future.get();
        }
        threadExecutor.shutdown();
    }

    private LogsGeneratorWorker createWorker() {
        long timeout = ThreadLocalRandom.current().nextLong(1, threadsMaxTimeout);
        return new LogsGeneratorWorker.Builder(textService)
                .withLogsLineNumber(logsLineNumber)
                .withMaxTimeout(timeout)
                .build();
    }
}
