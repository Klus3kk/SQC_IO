package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.Krok;
import pl.put.poznan.transformer.logic.LiczKroki;
import pl.put.poznan.transformer.logic.Scenariusz;
import pl.put.poznan.transformer.logic.Visitator;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        LiczKroki visitator = new LiczKroki();
        Scenariusz scenariusz = new Scenariusz("testowy");
        Krok k1 = new Krok();
        Krok k2 = new Krok();
        Krok k3 = new Krok();
        Krok k4 = new Krok();

        scenariusz.add(k1);
        scenariusz.add(k2);
        scenariusz.add(k3);
        scenariusz.add(k4);

        scenariusz.accept(visitator);

        System.out.println(visitator.getIloscKrokow());

        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
