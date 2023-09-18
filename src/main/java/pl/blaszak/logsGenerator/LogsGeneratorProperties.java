package pl.blaszak.logsGenerator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties
public class LogsGeneratorProperties {

    @Value("${app.textService.content.filename}")
    private String contentFileName;

    @Value("${app.threads.number}")
    private int threadsNumber;

    @Value("${app.logs.line.number}")
    private int logsLineNumber;

    @Value("${app.threads.maxTimeout}")
    private long threadsMaxTimeout;

    public String getContentFileName() {
        return contentFileName;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public int getLogsLineNumber() {
        return logsLineNumber;
    }

    public long getThreadsMaxTimeout() {
        return threadsMaxTimeout;
    }
}
