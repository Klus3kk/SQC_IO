package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.ScenarioFormatter;
import pl.put.poznan.transformer.logic.elements.ScenarioSimplifier;
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

class ScenarioSimplifierControllerTest {
    ScenarioSimplifier mock;
    Scenario testScenario;

    @BeforeEach
    public void setup() {
        mock = mock(ScenarioSimplifier.class);
        when(mock.getSimplifiedScenario((Scenario) any(), eq(99))).thenReturn(new Scenario("test 123"));
    }

    Supplier<ScenarioSimplifier> supplier = () -> {
        return mock;
    };

    @Test
    void testControllerWithMock1() throws Exception {
        ScenarioSimplifierController controller = new ScenarioSimplifierController(supplier);

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

        var response = controller.handleScenario(jsonNode, 99);

        assertEquals(200, response.getStatusCodeValue());
        verify(mock, times(1)).getSimplifiedScenario((Scenario) any(), eq(99));
    }


    @Test
    void testControllerWithMock1_null() throws Exception {
        ScenarioSimplifierController controller = new ScenarioSimplifierController(supplier);

        String jsonString = "";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

        var response = controller.handleScenario(jsonNode, 99);

        assertEquals(400, response.getStatusCodeValue());
        verify(mock, times(0)).getSimplifiedScenario((Scenario) any(), eq(99));
    }
}