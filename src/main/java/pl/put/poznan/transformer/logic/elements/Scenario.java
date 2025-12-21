package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa Scenario przechowuje informacje o scenariuszu.  Implementuje ona interfejs {@link Element}, dzieki ktoremu moze przyjmowac wizytatora
 * Klasa ta może zawierać kroki {@link Step} oraz inne podscenariusze {@link Scenario}
 * Klasa ta zawiera rowniez takie informacje jak: tytul scenariusza oraz aktorzy w nim zawarci
 */
public class Scenario implements Element {
    /**
     * Lista elementów. Są to kroki {@link Step} jak i podscenariusze {@link Scenario}
     */
    List<Element> elements = new ArrayList<>();
    /**
     * Lista aktorów występujących w scenariuszu
     */
    List<String> actors = new ArrayList<>();
    /**
     * Tytuł scenariusza
     */
    String title = "";
    /**
     * Aktor systemowy
     */
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

    /**
     * Metoda dodaje aktora do naszego scenariusza
     * @param actor aktor do dodania do scenariusza
     */
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

    /**
     * Dodaje obiektu implemetujacego interfejs {@link Element}, ktory moze byc {@link Step} krokiem lub innym podscenariuszem {@link Scenario}.
     * Beda one dzialaly jako dzieci tego obiektu znajdujacie się w uporzadkowanej liscie.
     * @param k {@link Element} do dodania jako dzieci
     */
    public void add(Element k) {
        elements.add(k);
    }

    public List<Element> getElements() { return this.elements; }

    /**
     * Przyjmujemy w tej metodzie wizytatora, a następnie akceptujemy jego na sobie. Tak, aby wizytator mogl odwiedzic ten obiekt.
     * Nastepnie przekazujemy wizytatora wszystkim naszym obiektom dziecia {@link Element}
     * @param visitator przyjmuje obiekt implementujący interfejs IVisitator
     */
    public void accept(IVisitor visitator) {
        visitator.visit(this);

        for (Element e : elements) {
            e.accept(visitator);
        }
    }


}
