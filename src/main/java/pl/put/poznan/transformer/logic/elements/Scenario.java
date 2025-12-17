package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class Scenario implements Element {
    List<Element> elements = new ArrayList<>();
    String title;

    public Scenario(String title) {
        this.title = title;
    }

    public void add(Element k) {
        elements.add(k);
    }

    public void accept(IVisitor visitator) {
        visitator.visit(this);

        for (Element e : elements) {
            e.accept(visitator);
        }
    }


}
