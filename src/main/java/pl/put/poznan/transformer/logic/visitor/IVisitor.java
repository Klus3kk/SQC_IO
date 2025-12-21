package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.elements.Scenario;

/**
 * Interfejs IVisitor jest interfejsem dla wszystkich wizytatorow, ktore chca odwiedzic obiekty klasy {@link Scenario} lub {@link Step}.
 */
public interface IVisitor {
    /**
     * Odwiedziny obiektu klasy {@link Scenario}
     * @param scenario obiekt scenariusza
     */
    public void visit(Scenario scenario);

    /**
     * Odwiedziny obiektu klasy {@link Step}
     * @param step obiekt kroku
     */
    public void visit(Step step);
}
