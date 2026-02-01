package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Logger;
import pl.put.poznan.transformer.logic.elements.JsonNodeToScenarioParser;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.ScenarioToJsonSerializer;
import pl.put.poznan.transformer.logic.visitor.CheckActorVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequestMapping("/scenario")
public class CheckActorController {
    private static final Logger logger = new Logger(CheckActorController.class);
    private final Supplier<CheckActorVisitor> visitorSupplier;

    public CheckActorController(Supplier<CheckActorVisitor> checkActorVisitorSupplier) {
        this.visitorSupplier = checkActorVisitorSupplier;
    }

    /* EXAMPLE REQUEST
    POST  http://127.0.0.1:8080/scenario/checkActors
    content-type: application/json
    {
        "type": "scenario",
        "title": "Dodanie książki",
        "system actor": "System",
        "actors": ["Bibliotekarz"],
        "elements": [{
            "type": "step",
            "content": "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"
            },
            {
            "type": "step",
            "content": "Wybiera opcje dodania nowej pozycji książkowej"
            },
            {
            "type": "step",
            "content": "IF Bibliotekarz potwierdza dane"
            }
        ]
    }
     */
    @PostMapping("/checkActors")
    public ResponseEntity<Map<String, Object>> checkActors(@RequestBody JsonNode json) {
        logger.info("Received POST request to /scenario/checkActors");
        logger.debug("Request JSON: {}", json.toString());
        Scenario scenario = JsonNodeToScenarioParser.parseScenario(json);

        if (scenario == null) {
            logger.warn("Failed to parse scenario - invalid JSON structure");
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to parse json. Send valid json next time.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        logger.info("Successfully parsed scenario: {}", scenario.getTitle());

        CheckActorVisitor visitor = visitorSupplier.get();
        scenario.accept(visitor);

        List<String> invalidSteps = visitor.getInvalidSteps();
        logger.info("Found {} invalid steps in scenario", invalidSteps.size());

        JsonNode scenarioJson = ScenarioToJsonSerializer.serializeScenario(scenario);

        Map<String, Object> response = new HashMap<>();
        response.put("invalid steps", invalidSteps);
        response.put("scenario", scenarioJson);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
