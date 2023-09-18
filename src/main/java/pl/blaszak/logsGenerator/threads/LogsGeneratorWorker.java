package pl.blaszak.logsGenerator.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.blaszak.logsGenerator.service.TextService;

import java.util.concurrent.ThreadLocalRandom;

public class LogsGeneratorWorker implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(LogsGeneratorWorker.class);

    private final long maxTimeout;
    private final int logsLineNumber;
    private final TextService textService;

    private LogsGeneratorWorker(Builder builder) {
        this.maxTimeout = builder.maxTimeout;
        this.logsLineNumber = builder.logsLineNumber;
        this.textService = builder.textService;
    }

    @Override
    public void run() {
        for (int i = 0; i < logsLineNumber; i++) {
            String message = textService.getLine(i);
            int level = ThreadLocalRandom.current().nextInt(0, 13);
            if (level < 3) {
                logger.trace(message);
            } else if (level < 6) {
                logger.debug(message);
            } else if (level < 9){
                logger.info(message);
            } else if (level < 12) {
                logger.warn(message);
            } else {
                logger.error(message);
            }
            long timeout = ThreadLocalRandom.current().nextLong(1, maxTimeout);
            sleep(timeout);
        }
    }

    private void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Builder {
        private final TextService textService;
        private long maxTimeout;
        private int logsLineNumber;

        public Builder(TextService textService) {
            this.textService = textService;
        }

        public Builder withMaxTimeout(long maxTimeout) {
            this.maxTimeout = maxTimeout;
            return this;
        }

        public Builder withLogsLineNumber(int logsLineNumber) {
            this.logsLineNumber = logsLineNumber;
            return this;
        }

        public LogsGeneratorWorker build() {
            return new LogsGeneratorWorker(this);
        }
    }
}
