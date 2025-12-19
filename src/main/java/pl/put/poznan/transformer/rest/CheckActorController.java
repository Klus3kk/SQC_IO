package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.elements.JsonNodeToScenarioParser;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.visitor.CheckActorVisitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scenario")
public class CheckActorController {
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
        Scenario scenario = JsonNodeToScenarioParser.parseScenario(json);

        if (scenario == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to parse json. Send valid json next time.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        CheckActorVisitor visitor = new CheckActorVisitor();
        scenario.accept(visitor);

        List<String> invalidSteps = visitor.getInvalidSteps();

        Map<String, Object> response = new HashMap<>();
        response.put("invalid steps", invalidSteps);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
