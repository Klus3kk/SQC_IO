package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.elements.Scenario;

public class CountStepsVisitor implements IVisitor {
    Integer count = 0;

    public void visit(Scenario scenario) {}

    public void visit(Step step) {
        count += 1;
    }

    public Integer returnStepCount() {
        return count;
    }

}


