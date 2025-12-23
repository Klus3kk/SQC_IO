package pl.put.poznan.transformer.logic.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioFormatterTest {

    @Test
    public void convertScenario() {
        Scenario scenario = new Scenario();
        scenario.setTitle("Test title");
        scenario.setSystemActor("Test system actor");
        scenario.addActor("actor");

        Step step1 = new Step();
        step1.setContent("Test step 1");
        Step step2 = new Step();
        step2.setContent("Test step 2");
        Step step3 = new Step();
        step3.setContent("Test step 3");

        scenario.add(step1);
        scenario.add(step2);
        scenario.add(step3);

        Scenario subScenario = new Scenario();
        subScenario.setTitle("Test title2");
        subScenario.setSystemActor("Test");
        scenario.add(subScenario);
        Step step4 = new Step("Test step 4");
        subScenario.add(step4);

        String string_scenario = (new ScenarioFormatter()).format(scenario);

        assertEquals("Tytuł: Test title\n" +
                "Aktor systemowy: Test system actor\n" +
                "Aktorzy: actor\n" +
                "\n" +
                "1. Test step 1\n" +
                "2. Test step 2\n" +
                "3. Test step 3\n" +
                "4. Test title2\n" +
                "4.1. Test step 4\n", string_scenario);

    }

}