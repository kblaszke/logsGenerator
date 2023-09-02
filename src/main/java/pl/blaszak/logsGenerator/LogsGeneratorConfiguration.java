package pl.blaszak.logsGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.blaszak.logsGenerator.service.LogsGeneratorService;

@Configuration
public class LogsGeneratorConfiguration {

    @Bean
    public LogsGeneratorService getLogsGeneratorService() {
        return new LogsGeneratorService();
    }
}
