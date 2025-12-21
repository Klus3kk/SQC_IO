package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import pl.put.poznan.transformer.logic.Logger;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {
    private static final Logger logger = new Logger(Application.class);
    private final Environment environment;

    public Application(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logApplicationReady() {
        String port = environment.getProperty("server.port", "8080");
        logger.info("REST API available at http://localhost:{}", port);
    }
}
