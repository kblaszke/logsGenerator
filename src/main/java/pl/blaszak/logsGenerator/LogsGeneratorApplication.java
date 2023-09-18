package pl.blaszak.logsGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import pl.blaszak.logsGenerator.service.LogsGeneratorService;

import java.util.concurrent.ExecutionException;

@EnableConfigurationProperties
@SpringBootApplication
public class LogsGeneratorApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(LogsGeneratorApplication.class, args);
		LogsGeneratorService service = context.getBean(LogsGeneratorService.class);
		if (args.length == 3) {
			service.setEnv(args);
		}
		service.run();
	}

}
