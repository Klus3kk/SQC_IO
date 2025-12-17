package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

public class Step implements Element {
    public void accept(IVisitor visitator) {
        visitator.visit(this);
    }
}
