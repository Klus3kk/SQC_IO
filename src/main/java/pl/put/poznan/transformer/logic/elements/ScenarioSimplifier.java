package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.elements.Element;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

public class ScenarioSimplifier {
    /*
    CODE FOR TESTING
    Scenario scenario = new Scenario("title");
    scenario.setSystemActor("ROOT");
    Step k1 = new Step("k1");
    Step k2 = new Step("k2");
    Step k3 = new Step("k3");
    Step k4 = new Step("k4");

    Scenario subscenario = new Scenario("subtitle");
    Step k5 = new Step("k5");
    Step k6 = new Step("k6");

    Scenario subsubscenario1 = new Scenario("subsubtitle1");
    Step k7 = new Step("k7");
    Step k8 = new Step("k8");
    subsubscenario1.add(k7);
    subsubscenario1.add(k8);

    Scenario subsubscenario2 = new Scenario("subsubtitle2");
    subsubscenario2.setSystemActor("SYSTEM");
    Step k9 = new Step("k9");
    subsubscenario2.add(k9);

    subsubscenario1.add(subsubscenario2);

    subscenario.add(k5);
    subscenario.add(subsubscenario1);
    subscenario.add(k6);
    subscenario.add(subsubscenario2);

    scenario.add(k1);
    scenario.add(k2);
    scenario.add(subscenario);
    scenario.add(k3);
    scenario.add(k4);
    scenario.add(subsubscenario1);

    ScenarioSimplifier.notPrettyPrint(scenario);
    System.out.println("");
    Scenario simplifiedScenario = ScenarioSimplifier.getSimplifiedScenario(scenario, 2);
    ScenarioSimplifier.notPrettyPrint(simplifiedScenario);
     */

    public static void notPrettyPrint(Scenario scenario){
        print(scenario, 0);
    }

    private static void print(Scenario scenario, Integer depth) {
        System.out.println("-".repeat(depth) + "#" + scenario.getTitle() + "<" + scenario.getSystemActor() + ">");
        for(Element element : scenario.getElements()){
            if(element instanceof Scenario){
                print((Scenario) element, depth + 1);
            }
            else if (element instanceof Step) {
                print((Step) element, depth + 1);
            }
        }
    }

    private static void print(Step step, Integer depth) {
        System.out.println("-".repeat(depth) + step.getContent());
    }

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
