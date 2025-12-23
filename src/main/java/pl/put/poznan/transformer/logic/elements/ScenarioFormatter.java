package pl.put.poznan.transformer.logic.elements;

import java.util.List;

/**
 * Klasa ScenarioFormatter służy do formatowania scenariusza do postaci tekstowej z numeracją kroków.
 * Numeracja jest hierarchiczna - kroki na tym samym poziomie są numerowane od 1,
 * a zagnieżdżone scenariusze dziedziczą numer rodzica (np. 1.1., 1.2., 2.1., itd.).
 */
public class ScenarioFormatter {

    private StringBuilder result;

    public ScenarioFormatter() {
        this.result = new StringBuilder();
    }

    /**
     * Formatuje scenariusz do postaci tekstowej z numeracją.
     * @param scenario scenariusz do sformatowania
     * @return String zawierający sformatowany tekst scenariusza z numeracją
     */
    public String format(Scenario scenario) {
        result = new StringBuilder();

        if (scenario.getTitle() != null && !scenario.getTitle().isEmpty()) {
            result.append("Tytuł: ").append(scenario.getTitle()).append("\n");
        }

        if (scenario.getSystemActor() != null && !scenario.getSystemActor().isEmpty()) {
            result.append("Aktor systemowy: ").append(scenario.getSystemActor()).append("\n");
        }

        List<String> actors = scenario.getActors();
        if (actors != null && !actors.isEmpty()) {
            result.append("Aktorzy: ");
            for (int i = 0; i < actors.size(); i++) {
                result.append(actors.get(i));
                if (i < actors.size() - 1) {
                    result.append(", ");
                }
            }
            result.append("\n");
        }

        result.append("\n");

        formatElements(scenario.getElements(), "");

        return result.toString();
    }

    /**
     * Rekurencyjna metoda formatująca elementy scenariusza.
     * @param elements lista elementów do sformatowania
     * @param prefix prefiks numeracji (np. "1." lub "1.2.")
     */
    private void formatElements(List<Element> elements, String prefix) {
        int stepNumber = 1;

        for (Element element : elements) {
            String currentNumber = prefix.isEmpty() ? String.valueOf(stepNumber) : prefix + stepNumber;

            if (element instanceof Step) {
                Step step = (Step) element;
                result.append(currentNumber).append(". ").append(step.getContent()).append("\n");
                stepNumber++;
            } else if (element instanceof Scenario) {
                Scenario subScenario = (Scenario) element;
                result.append(currentNumber).append(". ").append(subScenario.getTitle()).append("\n");

                String newPrefix = currentNumber + ".";
                formatElements(subScenario.getElements(), newPrefix);

                stepNumber++;
            }
        }
    }
}
