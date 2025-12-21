package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Step;
import pl.put.poznan.transformer.logic.elements.Scenario;

public interface IVisitor {
    public void visit(Scenario scenario);
    public void visit(Step step);
}
