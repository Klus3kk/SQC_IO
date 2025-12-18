package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.visitor.CountStepsVisitor;
import pl.put.poznan.transformer.logic.elements.Scenario;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class Application {

    public static void main(String[] args) {
        /* EXAMPLE CODE
        CountStepsVisitor visitator = new CountStepsVisitor();
        Scenario scenariusz = new Scenario("testowy");
        Step k1 = new Step();
        Step k2 = new Step();
        Step k3 = new Step();
        Step k4 = new Step();

        scenariusz.add(k1);
        scenariusz.add(k2);
        scenariusz.add(k3);
        scenariusz.add(k4);

        scenariusz.accept(visitator);

        System.out.println(visitator.returnStepCount());
        */
        SpringApplication.run(Application.class, args);
    }
}
