package pl.put.poznan.transformer.logic.elements;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioToJsonSerializerTest {

    @Test
    public void testSerialization() {
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

        JsonNode jsonNode = ScenarioToJsonSerializer.serializeScenario(scenario);
        String jsonString = jsonNode.toString();

        String expected = "{\"type\":\"scenario\",\"title\":\"Test title\",\"system actor\":\"Test system actor\",\"actors\":[\"actor\"],\"elements\":[{\"type\":\"step\",\"content\":\"Test step 1\"},{\"type\":\"step\",\"content\":\"Test step 2\"},{\"type\":\"step\",\"content\":\"Test step 3\"},{\"type\":\"scenario\",\"title\":\"Test title2\",\"system actor\":\"Test\",\"actors\":[],\"elements\":[]}]}";

        assertTrue(expected.equals(jsonString));
    }

}