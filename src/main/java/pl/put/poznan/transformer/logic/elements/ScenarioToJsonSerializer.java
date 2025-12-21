package pl.put.poznan.transformer.logic.elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Serializes Scenario objects to JSON format.
 *
 * @author Scenario Quality Checker Team
 * @version 1.0.0
 */
public class ScenarioToJsonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts a Scenario object to JsonNode representation.
     *
     * @param scenario Scenario to serialize
     * @return JsonNode representation of the scenario, null if scenario is null
     */
    public static JsonNode serializeScenario(Scenario scenario) {
        if (scenario == null) {
            return null;
        }

        ObjectNode node = mapper.createObjectNode();

        // Set type
        node.put("type", "scenario");

        // Set title
        node.put("title", scenario.getTitle());

        // Set system actor
        node.put("system actor", scenario.getSystemActor());

        // Set actors array
        ArrayNode actorsArray = mapper.createArrayNode();
        for (String actor : scenario.getActors()) {
            actorsArray.add(actor);
        }
        node.set("actors", actorsArray);

        // Set elements array (steps and nested scenarios)
        ArrayNode elementsArray = mapper.createArrayNode();
        for (Element element : scenario.getElements()) {
            if (element instanceof Step) {
                JsonNode stepNode = serializeStep((Step) element);
                if (stepNode != null) {
                    elementsArray.add(stepNode);
                }
            } else if (element instanceof Scenario) {
                JsonNode nestedScenario = serializeScenario((Scenario) element);
                if (nestedScenario != null) {
                    elementsArray.add(nestedScenario);
                }
            }
        }
        node.set("elements", elementsArray);

        return node;
    }

    /**
     * Converts a Step object to JsonNode representation.
     *
     * @param step Step to serialize
     * @return JsonNode representation of the step, null if step is null
     */
    public static JsonNode serializeStep(Step step) {
        if (step == null) {
            return null;
        }

        ObjectNode node = mapper.createObjectNode();

        // Set type
        node.put("type", "step");

        // Set content
        node.put("content", step.getContent());

        return node;
    }
}