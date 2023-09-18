package pl.blaszak.logsGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.blaszak.logsGenerator.service.LogsGeneratorService;
import pl.blaszak.logsGenerator.threads.LogsGeneratorThreadExecutor;
import pl.blaszak.logsGenerator.service.TextService;
import pl.blaszak.logsGenerator.util.LogsGeneratorUtils;

import java.io.FileNotFoundException;

@Configuration
public class LogsGeneratorConfiguration {

    @Autowired
    private LogsGeneratorProperties appProperties;

    @Bean
    public LogsGeneratorService getLogsGeneratorService(TextService textService, LogsGeneratorThreadExecutor threadExecutor) {
        return new LogsGeneratorService(appProperties.getThreadsNumber(), appProperties.getLogsLineNumber(), appProperties.getThreadsMaxTimeout(), threadExecutor, textService);
    }

    @Bean
    public TextService getTextService() throws FileNotFoundException {
        return new TextService(LogsGeneratorUtils.loadResourceFile(appProperties.getContentFileName()));
    }

    @Bean
    public LogsGeneratorThreadExecutor getLogsGeneratorThreadExecutor() {
        return LogsGeneratorThreadExecutor.newInstance(appProperties.getThreadsNumber());
    }
}
