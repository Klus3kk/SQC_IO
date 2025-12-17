package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.Krok;
import pl.put.poznan.transformer.logic.Scenariusz;
import pl.put.poznan.transformer.logic.Visitator;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        Visitator visitator = new Visitator();
        Scenariusz scenariusz = new Scenariusz("testowy");
        Krok k1 = new Krok();
        Krok k2 = new Krok();
        Krok k3 = new Krok();
        Krok k4 = new Krok();

        scenariusz.addKrok(k1);
        scenariusz.addKrok(k2);
        scenariusz.addKrok(k3);
        scenariusz.addKrok(k4);

        scenariusz.accept(visitator);

        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
