package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.elements.JsonNodeToScenarioParser;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.visitor.CountStepsVisitor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scenario")
public class CountStepsController {
    /* EXAMPLE REQUEST
    POST  http://127.0.0.1:3000/scenario/countSteps
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
            "content": "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"
            }
        ]
    }
     */
    @PostMapping("/countSteps")
    public ResponseEntity<Map<String, Object>> handleScenario(@RequestBody JsonNode json) {
        Scenario scenario =  JsonNodeToScenarioParser.parseScenario(json);
        if (scenario == null) { // FAILED TO PARSE
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to parse json. Send valid json next time.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        CountStepsVisitor visitator = new CountStepsVisitor();
        scenario.accept(visitator);

        Map<String, Object> error = new HashMap<>();
        error.put("counted steps", visitator.returnStepCount());

        return new ResponseEntity<>(error, HttpStatus.OK);

    }
}
