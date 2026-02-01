package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Logger;
import pl.put.poznan.transformer.logic.elements.JsonNodeToScenarioParser;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.visitor.CheckActorVisitor;
import pl.put.poznan.transformer.logic.visitor.CountStepsVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequestMapping("/scenario")
public class CountStepsController {
    private static final Logger logger = new Logger(CountStepsController.class);
    private final Supplier<CountStepsVisitor> visitorSupplier;

    public CountStepsController(Supplier<CountStepsVisitor> countStepsVisitorSupplier) {
        this.visitorSupplier = countStepsVisitorSupplier;
    }

    @PostMapping("/countSteps")
    public ResponseEntity<Map<String, Object>> handleScenario(@RequestBody JsonNode json) {
        logger.info("Received POST request to /scenario/countSteps");
        logger.debug("Request JSON: {}", json.toString());

        Scenario scenario =  JsonNodeToScenarioParser.parseScenario(json);
        if (scenario == null) {
            logger.warn("Failed to parse scenario - invalid JSON structure");
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to parse json. Send valid json next time.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        logger.info("Successfully parsed scenario: {}", scenario.getTitle());

        CountStepsVisitor visitator = visitorSupplier.get();
        scenario.accept(visitator);

        int stepCount = visitator.returnStepCount();
        logger.info("Counted {} steps in scenario", stepCount);

        Map<String, Object> error = new HashMap<>();
        error.put("counted steps", stepCount);

        return new ResponseEntity<>(error, HttpStatus.OK);

    }
}