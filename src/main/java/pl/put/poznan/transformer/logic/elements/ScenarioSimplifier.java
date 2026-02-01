package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.elements.Element;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

/**
 * Klasa upraszczajaca {@link Scenario}.
 * Posiada ona statyczna metode ScenarioSimplifier, ktora dokonuje uproszczen.
 *
 * Uproszczenie polega na zwroceniu {@link Scenario}, który zawiera pod-scenariusze tylko do określonego poziomu, aby zaprezentować uproszczoną wersję wymagań
 */
public class ScenarioSimplifier {
    /**
     * Statyczna metoda dokonujace uproszczen
     * @param scenario struktura scenariusza {@link Scenario}
     * @param depth liczba okresla poziom uproszczenia. Przy poziomie rownym 1 zwracany jest tylko scenariusz najwyższego poziomu. Przy poziomach wiekszym od 1 zwracane są scenariusze do danego poziomu włącznie (np. poziom rowny 2 oznacza scenariusz na najwyższym poziomie oraz jego bezpośrednie pod-scenariusze)
     * @return obiektu uproszczony {@link Scenario}
     */
    public Scenario getSimplifiedScenario(Scenario scenario, Integer depth){
        if(depth < 1){
            return null;
        }

        Scenario newScenario = new Scenario(scenario.getTitle(), scenario.getActors(), scenario.getSystemActor());
        for(Element element : scenario.getElements()){
            if(element instanceof Step){
                newScenario.add(element);
            }
            else if(element instanceof Scenario){
                Scenario subscenario = getSimplifiedScenario((Scenario) element, depth - 1);

                if(subscenario != null){
                    newScenario.add(subscenario);
                }
            }
        }
        return newScenario;
    }
}
