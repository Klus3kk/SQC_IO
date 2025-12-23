package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Logger;
import pl.put.poznan.transformer.logic.elements.JsonNodeToScenarioParser;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.ScenarioFormatter;

import java.util.HashMap;
import java.util.Map;

/**
 * Kontroler REST do eksportu scenariusza w postaci tekstowej z numeracją kroków.
 */
@RestController
@RequestMapping("/scenario")
public class ScenarioFormatterController {

    private static final Logger logger = new Logger(ScenarioFormatterController.class);

    /**
     * Endpoint do pobierania scenariusza w postaci tekstowej z numeracją.
     * @param json JSON zawierający scenariusz
     * @return ResponseEntity z tekstem scenariusza lub błędem
     */
    @PostMapping("/toText")
    public ResponseEntity<Map<String, Object>> formatScenarioToText(@RequestBody JsonNode json) {
        logger.info("Received POST request to /scenario/toText");
        logger.debug("Request JSON: {}", json.toString());

        Scenario scenario = JsonNodeToScenarioParser.parseScenario(json);
        if (scenario == null) {
            logger.warn("Failed to parse scenario - invalid JSON structure");
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to parse json. Send valid json next time.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        logger.info("Successfully parsed scenario: {}", scenario.getTitle());

        ScenarioFormatter formatter = new ScenarioFormatter();
        String formattedText = formatter.format(scenario);

        logger.info("Successfully formatted scenario to text");

        Map<String, Object> response = new HashMap<>();
        response.put("formatted_text", formattedText);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
