package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

public class Step implements Element {
    String content = "";

    public void accept(IVisitor visitator) {
        visitator.visit(this);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
