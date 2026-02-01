package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.ScenarioFormatter;
import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.visitor.CheckActorVisitor;
import pl.put.poznan.transformer.logic.visitor.CountStepsVisitor;
import pl.put.poznan.transformer.logic.visitor.KeywordVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
        import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ScenarioFormatterControllerTest {
    ScenarioFormatter mock;

    @BeforeEach
    public void setup() {
        mock = mock(ScenarioFormatter.class);
        when(mock.format((Scenario) any())).thenReturn("formated :)");
    }

    Supplier<ScenarioFormatter> supplier = () -> {
        return mock;
    };

    @Test
    void testControllerWithMock1() throws Exception {
        ScenarioFormatterController controller = new ScenarioFormatterController(supplier);

        String jsonString = "{\n" +
                "  \"type\": \"scenario\",\n" +
                "  \"title\": \"Test Keywords\",\n" +
                "  \"system actor\": \"System\",\n" +
                "  \"actors\": [\"User\"],\n" +
                "  \"elements\": [\n" +
                "    {\"type\": \"step\", \"content\": \"IF User is logged in\"},\n" +
                "    {\"type\": \"step\", \"content\": \"ELSE System shows error\"}\n" +
                "  ]\n" +
                "}";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

        var response = controller.formatScenarioToText(jsonNode);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("formated :)", response.getBody().get("formatted_text"));
        verify(mock, times(1)).format((Scenario) any());
    }


    @Test
    void testControllerWithMock1_null() throws Exception {
        ScenarioFormatterController controller = new ScenarioFormatterController(supplier);

        String jsonString = "";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

        var response = controller.formatScenarioToText(jsonNode);

        assertEquals(400, response.getStatusCodeValue());
        verify(mock, times(0)).format((Scenario) any());
    }
}