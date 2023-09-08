package pl.blaszak.logsGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.blaszak.logsGenerator.service.LogsGeneratorService;
import pl.blaszak.logsGenerator.service.LogsGeneratorUtils;
import pl.blaszak.logsGenerator.service.TextService;

import java.io.FileNotFoundException;

@Configuration
public class LogsGeneratorConfiguration {

    @Autowired
    private LogsGeneratorProperties appProperties;

    @Bean
    public LogsGeneratorService getLogsGeneratorService(TextService textService) {
        return new LogsGeneratorService(textService);
    }

    @Bean
    public TextService getTextService() throws FileNotFoundException {
        return new TextService(LogsGeneratorUtils.loadResourceFile(appProperties.getContentFileName()));
    }

}
