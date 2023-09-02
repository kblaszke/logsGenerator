package pl.blaszak.logsGenerator.service;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogsGeneratorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogsGeneratorService.class);

    @PreDestroy
    public void closeService() {
        LOGGER.info("Closing service");
    }

    public void run() {
        LOGGER.info("Running service");
    }
}
