package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.elements.Element;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

public class ScenarioSimplifier {
    public static Scenario getSimplifiedScenario(Scenario scenario, Integer depth){
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
