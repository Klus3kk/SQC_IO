package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.elements.Scenario;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa jest wizytatorem implementujacy interfejs {@link IVisitor}.
 * Odwiedza ona obiekt klasy {@link Scenario} oraz {@link Step}.
 * Klasa sprawdza czy nazwy kroków {@link Step} rozpoczynaja sie od aktorow zdefiniowyanych w {@link Scenario} (słowa kluczowe IF / ELSE / FOR EACH są ignorowane).
 * W przypadku bledu w ktorym krok nie rozpoczyna sie od aktora, zapisuje takie kroki
 */
public class CheckActorVisitor implements IVisitor {
    /**
     * kroki bledne, nie rozpoczynajace sie od nazwy aktora
     */
    private List<String> invalidSteps = new ArrayList<>();
    /**
     * aktorzy pobrani z {@link Scenario}
     */
    private List<String> actors = new ArrayList<>();

    /**
     * Odwiedziny obiektu klasy {@link Scenario}. Dodanie wszystkich aktorow do zmiennej `actors` w celu przyszlego sprawdzenia czy krok rozpoczyna sie od aktora.
     * @param scenario obiekt scenariusza
     */
    public void visit(Scenario scenario) {
        // Zbierz aktorów ze scenariusza (także z pod-scenariuszy)
        actors.addAll(scenario.getActors());

        // System actor - bierzemy niepustego
        if (!scenario.getSystemActor().isEmpty()) {
            actors.add(scenario.getSystemActor());
        }
    }

    /**
     * Odwiedziny obiektu klasy {@link Step}. Sprawdzenie czy na poczatku nazwy kroku jest aktor (z pominieciem slow kluczowych). Gdy zdanie nie rozpoczyna sie od aktora, jest one dodane do `invalidSteps`
     * @param step obiekt kroku
     */
    public void visit(Step step) {
        String content = step.getContent();

        // Usuń słowa kluczowe z początku kroku
        String contentWithoutKeywords = removeKeywords(content);

        // Sprawdź czy krok rozpoczyna się od aktora
        if (!startsWithActor(contentWithoutKeywords)) {
            invalidSteps.add(content);
        }
    }

    /**
     * Usuwa slowo kluczowe z kroku
     * @param content tresc kroku
     * @return tresc kroku bez slowa kluczowego
     */
    private String removeKeywords(String content) {
        String trimmed = content.trim();

        // Usuń słowa kluczowe IF, ELSE, FOR EACH (case-insensitive)
        String[] keywords = {"IF ", "ELSE ", "FOR EACH "};

        for (String keyword : keywords) {
            if (trimmed.toUpperCase().startsWith(keyword)) {
                trimmed = trimmed.substring(keyword.length()).trim();
                // Rekurencyjnie usuń kolejne słowa kluczowe
                return removeKeywords(trimmed);
            }
        }

        return trimmed;
    }

    /**
     *
     * @param content tresc kroku
     * @return zwraca prawde jezeli krok zaczyna sie od aktora, falsz gdy nie zaczyna sie od aktora
     */
    private boolean startsWithActor(String content) {
        if (content.isEmpty()) {
            return false;
        }

        // Sprawdź czy zaczyna się od któregoś z aktorów
        for (String actor : actors) {
            if (content.toLowerCase().startsWith(actor.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return lista blednych krokow
     */
    public List<String> getInvalidSteps() {
        return invalidSteps;
    }
}
