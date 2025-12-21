package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

/**
 * Interfejs Element jest klasa rodzicem dla klas {@link Scenario} i {@link Step}, ktore implementuja go.
 * Jest ona potrzebna szczegolnie dla klas {@link Scenario}, ktore chca miec odwolania do innych elementow niezaleznie od klasy.
 * Interfejs Element posiada rowniez metody do pracy z wizytatorami.
 */
public interface Element {
    /**
     * Metoda `accept` jest wymagana dla pracy z wizytatorami.
     * Kazda klasa implementująca ten interfejs moze przyjac wizytatora i wywolac na nim pewne funkcje
     * @param visitator przyjmuje obiekt implementujący interfejs IVisitator
     */
    void accept(IVisitor visitator);
}
