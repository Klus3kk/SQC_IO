package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class Scenario implements Element {
    List<Element> elements = new ArrayList<>();
    List<String> actors = new ArrayList<>();
    String title = "";
    String systemActor = "";

    public Scenario(){

    }

    public Scenario(String title){
        this.title = title;
    }

    public Scenario(String title, List<String> actors, String systemActor){
        this.title = title;
        this.actors = actors;
        this.systemActor = systemActor;
    }

    public void addActor(String actor) {
        actors.add(actor);
    }

    public List<String> getActors() {
        return this.actors;
    }

    public void setSystemActor(String systemActor) {
        this.systemActor = systemActor;
    }

    public String getSystemActor() {
        return this.systemActor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void add(Element k) {
        elements.add(k);
    }

    public List<Element> getElements() { return this.elements; }

    public void accept(IVisitor visitator) {
        visitator.visit(this);

        for (Element e : elements) {
            e.accept(visitator);
        }
    }


}
