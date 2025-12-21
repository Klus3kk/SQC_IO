package pl.put.poznan.transformer.logic.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

import static org.junit.jupiter.api.Assertions.*;

class CountStepsVisitorTest {

    @Test
    public void zeroStepsCount() {
        Scenario scenario = new Scenario();
        CountStepsVisitor visitor = new CountStepsVisitor();
        scenario.accept(visitor);
        assertEquals(0, visitor.returnStepCount());
    }

    @Test
    public void twoStepsCount() {
        Scenario scenario = new Scenario();
        Step step1 = new Step();
        Step step2 = new Step();
        scenario.add(step1);
        scenario.add(step2);

        CountStepsVisitor visitor = new CountStepsVisitor();
        scenario.accept(visitor);
        assertEquals(2, visitor.returnStepCount());
    }

    @Test
    public void subStepsCount() {
        Scenario scenario = new Scenario();
        Step step1 = new Step();
        Step step2 = new Step();
        scenario.add(step1);
        scenario.add(step2);

        Scenario scenario2 = new Scenario();
        Step step21 = new Step();
        scenario2.add(step21);
        scenario.add(scenario2);

        CountStepsVisitor visitor = new CountStepsVisitor();
        scenario.accept(visitor);
        assertEquals(3, visitor.returnStepCount());
    }

    @Test
    public void subStepsMoreCount() {
        Scenario scenario = new Scenario();
        Step step1 = new Step();
        Step step2 = new Step();
        scenario.add(step1);
        scenario.add(step2);

        Scenario scenario2 = new Scenario();
        Step step21 = new Step();
        scenario2.add(step21);
        Step step22 = new Step();
        scenario2.add(step22);
        Step step23 = new Step();
        scenario2.add(step23);
        Step step24 = new Step();
        scenario2.add(step24);
        Step step25 = new Step();
        scenario2.add(step25);
        scenario.add(scenario2);

        CountStepsVisitor visitor = new CountStepsVisitor();
        scenario.accept(visitor);
        assertEquals(7, visitor.returnStepCount());
    }


}