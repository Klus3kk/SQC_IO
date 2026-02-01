package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import pl.put.poznan.transformer.logic.elements.Scenario;
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

class KeywordCountControllerTest {
    KeywordVisitor mockVisitor;

    @BeforeEach
    public void setup() {
        mockVisitor = mock(KeywordVisitor.class);
        when(mockVisitor.getCount()).thenReturn(999);
    }

    Supplier<KeywordVisitor> visitorSupplier = () -> {
        return mockVisitor;
    };

    @Test
    void testControllerWithMock1() throws Exception {
        KeywordCountController controller = new KeywordCountController(visitorSupplier);

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

        var response = controller.handleScenario(jsonNode);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(999, response.getBody().get("keyword-count"));
        verify(mockVisitor, times(2)).getCount();
        verify(mockVisitor, times(1)).visit((Scenario)any());
        verify(mockVisitor, times(2)).visit((Step)any());
    }


    @Test
    void testControllerWithMock1_null() throws Exception {
        KeywordCountController controller = new KeywordCountController(visitorSupplier);

        String jsonString = "";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

        var response = controller.handleScenario(jsonNode);

        assertEquals(400, response.getStatusCodeValue());
        verify(mockVisitor, times(0)).getCount();
        verify(mockVisitor, times(0)).visit((Scenario)any());
        verify(mockVisitor, times(0)).visit((Step)any());
    }
}