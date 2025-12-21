package pl.put.poznan.transformer.logic.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

import static org.junit.jupiter.api.Assertions.*;

class CheckActorVisitorTest {

    @Test
    public void subScenatrioTest() {
        Scenario scenario = new Scenario();
        scenario.addActor("actor1");

        Step step1 = new Step();
        scenario.add(step1);
        step1.setContent("ActOR1 is doing something");

        Scenario scenario2 = new Scenario();
        scenario2.addActor("actor2");
        scenario.add(scenario2);

        Step step2 = new Step();
        scenario2.add(step2);
        step2.setContent("actor1 is doing sth else");

        Step step3 = new Step();
        scenario2.add(step3);
        step3.setContent("actor2 is doing nothing :(");

        CheckActorVisitor visitor1 = new CheckActorVisitor();
        scenario.accept(visitor1);
        assertEquals(0, visitor1.getInvalidSteps().size());


        CheckActorVisitor visitor2 = new CheckActorVisitor();
        scenario2.accept(visitor2);
        assertEquals(1, visitor2.getInvalidSteps().size());
        assertEquals("actor1 is doing sth else", visitor2.getInvalidSteps().get(0));
    }

    @Test
    public void systemActorsTest() {
        Scenario scenario = new Scenario();
        scenario.setSystemActor("actor1");

        Step step1 = new Step();
        scenario.add(step1);
        step1.setContent("ActOR1 is doing something");

        Scenario scenario2 = new Scenario();
        scenario2.setSystemActor("actor2");
        scenario.add(scenario2);

        Step step2 = new Step();
        scenario2.add(step2);
        step2.setContent("actor1 is doing sth else");

        Step step3 = new Step();
        scenario2.add(step3);
        step3.setContent("actor2 is doing nothing :(");

        CheckActorVisitor visitor1 = new CheckActorVisitor();
        scenario.accept(visitor1);
        assertEquals(0, visitor1.getInvalidSteps().size());


        CheckActorVisitor visitor2 = new CheckActorVisitor();
        scenario2.accept(visitor2);
        assertEquals(1, visitor2.getInvalidSteps().size());
        assertEquals("actor1 is doing sth else", visitor2.getInvalidSteps().get(0));

    }

    @Test
    public void multipleActorsDefinition() {
        Scenario scenario = new Scenario();
        scenario.addActor("actor1");
        scenario.addActor("actor2");

        Step step1 = new Step();
        scenario.add(step1);
        step1.setContent("ActOR1 is doing something");

        Scenario scenario2 = new Scenario();
        scenario.add(scenario2);

        Step step2 = new Step();
        scenario2.add(step2);
        step2.setContent("actor1 is doing sth else");

        Step step3 = new Step();
        scenario2.add(step3);
        step3.setContent("actor2 is doing nothing :(");

        CheckActorVisitor visitor1 = new CheckActorVisitor();
        scenario.accept(visitor1);
        assertEquals(0, visitor1.getInvalidSteps().size());


        CheckActorVisitor visitor2 = new CheckActorVisitor();
        scenario2.accept(visitor2);
        assertEquals(2, visitor2.getInvalidSteps().size());
        assertEquals("actor1 is doing sth else", visitor2.getInvalidSteps().get(0));
        assertEquals("actor2 is doing nothing :(", visitor2.getInvalidSteps().get(1));

    }


    @Test
    public void checkIfRemovalIF() {
        Scenario scenario = new Scenario();
        scenario.addActor("actor1");

        Step step1 = new Step();
        scenario.add(step1);
        step1.setContent("IF ActOR1 is doing something");

        Scenario scenario2 = new Scenario();
        scenario2.addActor("actor2");
        scenario.add(scenario2);

        Step step2 = new Step();
        scenario2.add(step2);
        step2.setContent("actor1 is doing sth else");

        Step step3 = new Step();
        scenario2.add(step3);
        step3.setContent("FOR EACH actor2 is doing nothing :(");

        CheckActorVisitor visitor1 = new CheckActorVisitor();
        scenario.accept(visitor1);
        assertEquals(0, visitor1.getInvalidSteps().size());


        CheckActorVisitor visitor2 = new CheckActorVisitor();
        scenario2.accept(visitor2);
        assertEquals(1, visitor2.getInvalidSteps().size());
        assertEquals("actor1 is doing sth else", visitor2.getInvalidSteps().get(0));
    }

}