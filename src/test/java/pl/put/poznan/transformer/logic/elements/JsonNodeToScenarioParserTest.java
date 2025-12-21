package pl.put.poznan.transformer.logic.elements;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonNodeToScenarioParserTest {
    private static JsonNode getTestScenario() {
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
        return jsonNode;
    }

    @Test
    public void testJsonNodeParsing() {
        JsonNode json = getTestScenario();

        Scenario scenario = JsonNodeToScenarioParser.parseScenario(json);

        assertNotNull(scenario);
        assertEquals("Test title", scenario.getTitle());
        assertEquals("Test system actor", scenario.getSystemActor());
        assertEquals(1, scenario.getActors().size());
        assertEquals("actor", scenario.getActors().get(0));
        assertEquals(4, scenario.getElements().size());
        assertEquals(Step.class, scenario.getElements().get(0).getClass());
        assertEquals(Step.class, scenario.getElements().get(1).getClass());
        assertEquals(Step.class, scenario.getElements().get(2).getClass());
        assertEquals(Scenario.class, scenario.getElements().get(3).getClass());
        assertEquals("Test step 1", ((Step)scenario.getElements().get(0)).getContent() );
        assertEquals("Test step 2", ((Step)scenario.getElements().get(1)).getContent() );
        assertEquals("Test step 3", ((Step)scenario.getElements().get(2)).getContent() );
        assertEquals("Test title2", ((Scenario)scenario.getElements().get(3)).getTitle() );
        assertEquals("Test", ((Scenario)scenario.getElements().get(3)).getSystemActor() );
        assertEquals(0, ((Scenario)scenario.getElements().get(3)).getElements().size() );
        assertEquals(0, ((Scenario)scenario.getElements().get(3)).getActors().size() );

    }

}