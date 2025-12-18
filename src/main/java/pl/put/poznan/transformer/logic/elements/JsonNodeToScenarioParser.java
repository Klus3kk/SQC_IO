package pl.put.poznan.transformer.logic.elements;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Objects;

public class JsonNodeToScenarioParser {
    public static Scenario parseScenario(JsonNode json) {
        try {
            String type = json.path("type").asText();
            if (!Objects.equals(type, "scenario")) {
                throw new Exception();
            }

            Scenario out = new Scenario();
            if (!json.path("title").isMissingNode() && !json.path("title").isNull()) {
                String title = json.path("title").asText();
                out.setTitle(title);
            } else {
                throw new Exception();
            }


            if (!json.path("system actor").isMissingNode() && !json.path("system actor").isNull()) {
                String systemActor = json.path("system actor").asText();
                out.setSystemActor(systemActor);
            } else {
                throw new Exception();
            }

            JsonNode actors = json.path("actors");
            if (actors.isMissingNode() || actors.isNull() || !actors.isArray()) {
                throw new Exception();
            }
            for (JsonNode actor : actors) {
                String name = actor.asText();
                out.addActor(name);
            }

            JsonNode elements = json.path("elements");
            if (elements.isMissingNode() || elements.isNull() || !elements.isArray()) {
                throw new Exception();
            }

            for (JsonNode element : elements) {
                String elementType = element.path("type").asText();
                if (Objects.equals(elementType, "step")) {
                    Step e = parseStep(element);
                    if (e == null) {
                        throw new Exception();
                    }
                    out.add(e);
                } else if (Objects.equals(elementType, "scenario")) {
                    Scenario e = parseScenario(element);
                    if (e == null) {
                        throw new Exception();
                    }
                    out.add(e);
                } else {
                    throw new Exception();
                }

            }


            return out;
        } catch (Exception ignored) {}

        return null;
    }

    public static Step parseStep(JsonNode json) {
        try {
            String type = json.path("type").asText();
            if (!Objects.equals(type, "step")) {
                throw new Exception();
            }

            Step out = new Step();
            if (!json.path("content").isMissingNode() && !json.path("content").isNull()) {
                String content = json.path("content").asText();
                out.setContent(content);
            } else {
                throw new Exception();
            }


            return out;

        } catch (Exception ignored) {}

        return null;
    }

}
