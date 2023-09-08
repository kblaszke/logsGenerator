package pl.blaszak.logsGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import pl.blaszak.logsGenerator.service.LogsGeneratorService;

@EnableConfigurationProperties
@SpringBootApplication
public class LogsGeneratorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LogsGeneratorApplication.class, args);
		LogsGeneratorService service = context.getBean(LogsGeneratorService.class);
		service.run();
	}

}
