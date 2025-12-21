package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.elements.Scenario;

/**
 * Klasa implementuja interfejs {@link IVisitor} do przegladu obiektu klasy {@link Scenario} oraz wszystkich podscenariuszy w celu ustalenia ilosci krokow w scenariuszu.
 */
public class CountStepsVisitor implements IVisitor {
    /**
     * licznik odwiedzonych krokow
     */
    Integer count = 0;

    /**
     * W przypadku odwiedzin obiektu klasy {@link Scenario} nie robimy nic, gdyż nie liczymy jej wystapien
     * @param scenario scenariusz do odwiedzenia
     */
    public void visit(Scenario scenario) {}

    /**
     * W przypadku odwiedzin obiektu klasy {@link Step} zwiekszamy licznik odwiedzonych krokow o jeden.
     * @param step krok do odwiedzenia
     */
    public void visit(Step step) {
        count += 1;
    }

    /**
     * Zwraca obliczona liczbe krokow. Przed wywolaniem tej funkcji nalezy odwiedzic badany obiekt.
     * @return liczba krokow
     */
    public Integer returnStepCount() {
        return count;
    }

}


