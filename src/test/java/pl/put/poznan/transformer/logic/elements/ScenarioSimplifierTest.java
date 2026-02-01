package pl.put.poznan.transformer.logic.elements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioSimplifierTest {
    private static Scenario templateScenario;

    @BeforeEach
    public void generalScenarioInit() {
        templateScenario = new Scenario("Title1");
        templateScenario.add(new Step("Step1.1"));
        templateScenario.add(new Step("Step1.2"));
        templateScenario.add(new Step("Step1.3"));

        Scenario subscenario = new Scenario("Title2");
        templateScenario.add(subscenario);
        subscenario.add(new Step("Step2.1"));
        subscenario.add(new Step("Step2.2"));

        Scenario subsubscenario = new Scenario("Title3");
        subscenario.add(subsubscenario);
        subsubscenario.add(new Step("Step3.1"));
    }

    @Test
    public void zeroDepthNull() {
        Scenario scenario = new Scenario();
        assertEquals(null, (new ScenarioSimplifier()).getSimplifiedScenario(scenario, 0));
    }

    @Test
    public void oneDepthTest() {
        Scenario simplified = (new ScenarioSimplifier()).getSimplifiedScenario(templateScenario, 1);

        assertEquals("Title1", simplified.getTitle());
        assertEquals(3, simplified.getElements().size());
        assertEquals(Step.class, simplified.getElements().get(0).getClass());
        assertEquals("Step1.1", ((Step)simplified.getElements().get(0)).getContent() );
        assertEquals(Step.class, simplified.getElements().get(1).getClass());
        assertEquals("Step1.2", ((Step)simplified.getElements().get(1)).getContent() );
        assertEquals(Step.class, simplified.getElements().get(2).getClass());
        assertEquals("Step1.3", ((Step)simplified.getElements().get(2)).getContent() );

    }

    @Test
    public void twoDepthTest() {
        Scenario simplified = (new ScenarioSimplifier()).getSimplifiedScenario(templateScenario, 2);

        assertEquals("Title1", simplified.getTitle());
        assertEquals(4, simplified.getElements().size());
        assertEquals(Step.class, simplified.getElements().get(0).getClass());
        assertEquals("Step1.1", ((Step)simplified.getElements().get(0)).getContent() );
        assertEquals(Step.class, simplified.getElements().get(1).getClass());
        assertEquals("Step1.2", ((Step)simplified.getElements().get(1)).getContent() );
        assertEquals(Step.class, simplified.getElements().get(2).getClass());
        assertEquals("Step1.3", ((Step)simplified.getElements().get(2)).getContent() );

        assertEquals(Scenario.class, simplified.getElements().get(3).getClass());
        assertEquals("Title2", ((Scenario)simplified.getElements().get(3)).getTitle() );
        assertEquals(2, ((Scenario)simplified.getElements().get(3)).getElements().size());
    }

    @Test
    public void threeDepthTest() {
        Scenario simplified = (new ScenarioSimplifier()).getSimplifiedScenario(templateScenario, 3);

        assertEquals("Title1", simplified.getTitle());
        assertEquals(4, simplified.getElements().size());
        assertEquals(Step.class, simplified.getElements().get(0).getClass());
        assertEquals("Step1.1", ((Step)simplified.getElements().get(0)).getContent() );
        assertEquals(Step.class, simplified.getElements().get(1).getClass());
        assertEquals("Step1.2", ((Step)simplified.getElements().get(1)).getContent() );
        assertEquals(Step.class, simplified.getElements().get(2).getClass());
        assertEquals("Step1.3", ((Step)simplified.getElements().get(2)).getContent() );

        assertEquals(Scenario.class, simplified.getElements().get(3).getClass());
        assertEquals("Title2", ((Scenario)simplified.getElements().get(3)).getTitle() );
        assertEquals(3, ((Scenario)simplified.getElements().get(3)).getElements().size());

        assertEquals(Scenario.class, ((Scenario)simplified.getElements().get(3)).getElements().get(2).getClass());
        assertEquals("Title3", ((Scenario)((Scenario)simplified.getElements().get(3)).getElements().get(2)).getTitle() );
    }


}