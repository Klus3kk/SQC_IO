package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

public interface Element {
    void accept(IVisitor visitator);
}
