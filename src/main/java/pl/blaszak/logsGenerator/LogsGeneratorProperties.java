package pl.blaszak.logsGenerator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("LogsGenerator.properties")
@Component
@ConfigurationProperties
public class LogsGeneratorProperties {

    @Value("${app.textService.content.filename}")
    private String contentFileName;

    public String getContentFileName() {
        return contentFileName;
    }
}
