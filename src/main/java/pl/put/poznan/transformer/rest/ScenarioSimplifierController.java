package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Logger;
import pl.put.poznan.transformer.logic.elements.JsonNodeToScenarioParser;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.ScenarioSimplifier;
import pl.put.poznan.transformer.logic.elements.ScenarioToJsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequestMapping("/scenario")
public class ScenarioSimplifierController {
    private static final Logger logger = new Logger(ScenarioSimplifierController.class);
    private final Supplier<ScenarioSimplifier> simplifierSupplier;

    public ScenarioSimplifierController(Supplier<ScenarioSimplifier> scenarioSimplifierSupplier) {
        this.simplifierSupplier = scenarioSimplifierSupplier;
    }

    @PostMapping("/simplifyScenario/{depth}")
    public ResponseEntity<Object> handleScenario(@RequestBody JsonNode json, @PathVariable Integer depth) {
        logger.info("Received POST request to /scenario/simplifyScenario");
        if (depth <= 0) {
            logger.warn("Invalid parameter depth. It must be greater than 0.");
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Invalid parameter depth. It must be greater than 0.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        logger.debug("Request JSON: {}", json.toString());
        Scenario scenario =  JsonNodeToScenarioParser.parseScenario(json);
        if (scenario == null) {
            logger.warn("Failed to parse scenario - invalid JSON structure");
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to parse json. Send valid json next time.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        logger.info("Successfully parsed scenario: {}", scenario.getTitle());
        ScenarioSimplifier ssimplif = simplifierSupplier.get();
        Scenario scenarioSimplified = ssimplif.getSimplifiedScenario(scenario, depth);

        JsonNode outputScenario = ScenarioToJsonSerializer.serializeScenario(scenarioSimplified);
        return new ResponseEntity<>(outputScenario, HttpStatus.OK);

    }
}