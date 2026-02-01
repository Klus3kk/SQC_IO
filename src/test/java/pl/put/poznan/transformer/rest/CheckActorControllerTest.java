package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.visitor.CheckActorVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckActorControllerTest {
    CheckActorVisitor mockVisitor;

    @BeforeEach
    public void setup() {
        mockVisitor = mock(CheckActorVisitor.class);
        when(mockVisitor.getInvalidSteps()).thenReturn(List.of("step1", "step2"));
    }

    Supplier<CheckActorVisitor> visitorSupplier = () -> {
        return mockVisitor;
    };

    @Test
    void testControllerWithMock1() throws Exception {
        CheckActorController controller = new CheckActorController(visitorSupplier);

        String jsonString = "{\n" +
                "  \"type\": \"scenario\",\n" +
                "  \"title\": \"Test Actors\",\n" +
                "  \"system actor\": \"System\",\n" +
                "  \"actors\": [\"User\"],\n" +
                "  \"elements\": [\n" +
                "    {\"type\": \"step\", \"content\": \"User clicks button\"},\n" +
                "    {\"type\": \"step\", \"content\": \"Clicks another button\"}\n" +
                "  ]\n" +
                "}";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

        var response = controller.checkActors(jsonNode);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(List.of("step1", "step2"), response.getBody().get("invalid steps"));
        verify(mockVisitor, times(1)).getInvalidSteps();
        verify(mockVisitor, times(1)).visit((Scenario)any());
        verify(mockVisitor, times(2)).visit((Step)any());
    }


    @Test
    void testControllerWithMock1_null() throws Exception {
        CheckActorController controller = new CheckActorController(visitorSupplier);

        String jsonString = "";
        JsonNode jsonNode = new ObjectMapper().readTree(jsonString);

        var response = controller.checkActors(jsonNode);

        assertEquals(400, response.getStatusCodeValue());
        verify(mockVisitor, times(0)).getInvalidSteps();
        verify(mockVisitor, times(0)).visit((Scenario)any());
        verify(mockVisitor, times(0)).visit((Step)any());
    }
}