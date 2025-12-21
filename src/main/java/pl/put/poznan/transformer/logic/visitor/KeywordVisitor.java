package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

/**
 * Klasa implementujaca interfejs {@link IVisitor}. Przeglada ona obiekt {@link Scenario} w celu znalezienia ilosci krokow na poczatku ktorych znajduje sie slowo kluczowe (if, else, for each)
 */
public class KeywordVisitor implements IVisitor{
    /**
     * lista ze slowami kluczowymi
     */
    private static final String[] keywords = {"if", "else", "for each"};
    /**
     * licznik wystapien slow kluczowych
     */
    private Integer count = 0;

    /**
     * W przypadku odwiedzin obiektu klasy {@link Scenario} nie robimy nic, gdyż nie jest on klasy {@link Step}
     * @param scenario obiekt scenariusza
     */
    @Override
    public void visit(Scenario scenario) {

    }

    /**
     * W przypadku odwiedzin obiektu klasy {@code Step} patrzymy czy zawiera ono slowo kluczowe na poczatku zdania i jesli tak to inkrementujemy licznik
     * @param step obiekt kroku
     */
    @Override
    public void visit(Step step) {
        String loweredContext = step.getContent().toLowerCase().strip();
        for(String keyword : keywords){
            if(loweredContext.startsWith(keyword)){
                count++;
                break;
            }
        }
    }

    public Integer getCount(){
        return count;
    }
}
