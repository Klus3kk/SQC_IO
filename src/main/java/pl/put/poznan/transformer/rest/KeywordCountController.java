package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.elements.JsonNodeToScenarioParser;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.Logger;
import pl.put.poznan.transformer.logic.visitor.CountStepsVisitor;
import pl.put.poznan.transformer.logic.visitor.KeywordVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequestMapping("/scenario")
public class KeywordCountController {
    private static final Logger logger = new Logger(KeywordCountController.class);
    private final Supplier<KeywordVisitor> visitorSupplier;

    public KeywordCountController(Supplier<KeywordVisitor> keywordVisitorSupplier) {
        this.visitorSupplier = keywordVisitorSupplier;
    }

    @PostMapping("/countKeywords")
    public ResponseEntity<Map<String, Object>> handleScenario(@RequestBody JsonNode json) {
        Scenario scenario =  JsonNodeToScenarioParser.parseScenario(json);

        logger.info("Received POST request to /scenario/countKeywords");
        logger.debug("Request JSON: {}", json.toString());

        if (scenario == null) {
            logger.error("Parsed input scenario is null!");

            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to parse json. Send valid json next time.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        KeywordVisitor visitor = visitorSupplier.get();
        scenario.accept(visitor);

        logger.info("Keywords in {} scenario were successfully counted.", scenario.getTitle());
        logger.info("Counted keywords in scenario: {}", visitor.getCount());

        Map<String, Object> reply = new HashMap<>();
        reply.put("keyword-count", visitor.getCount());

        return new ResponseEntity<>(reply, HttpStatus.OK);

    }
}
