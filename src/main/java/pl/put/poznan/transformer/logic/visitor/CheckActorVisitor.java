package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.elements.Scenario;

import java.util.ArrayList;
import java.util.List;

public class CheckActorVisitor implements IVisitor {
    private List<String> invalidSteps = new ArrayList<>();
    private List<String> actors = new ArrayList<>();

    public void visit(Scenario scenario) {
        // Zbierz aktorów ze scenariusza (także z pod-scenariuszy)
        actors.addAll(scenario.getActors());

        // System actor - bierzemy niepustego
        if (!scenario.getSystemActor().isEmpty()) {
            actors.add(scenario.getSystemActor());
        }
    }

    public void visit(Step step) {
        String content = step.getContent();

        // Usuń słowa kluczowe z początku kroku
        String contentWithoutKeywords = removeKeywords(content);

        // Sprawdź czy krok rozpoczyna się od aktora
        if (!startsWithActor(contentWithoutKeywords)) {
            invalidSteps.add(content);
        }
    }

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

    public List<String> getInvalidSteps() {
        return invalidSteps;
    }
}
