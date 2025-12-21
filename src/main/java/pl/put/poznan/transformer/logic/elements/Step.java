package pl.put.poznan.transformer.logic.elements;

import pl.put.poznan.transformer.logic.visitor.IVisitor;

/**
 * Obiekt Step reprezentuje krok w scenariuszu. Implementuje ona interfejs {@link Element}, dzieki któremu może przyjmować wizytatora
 * Obiekt Step przechowuje informacje o tresci danego kroku.
 */
public class Step implements Element {
    /**
     * Zmienna przechowujaca zawartosc danego kroku
     */
    String content = "";

    public Step(){
    }

    public Step(String str){
        this.content = str;
    }

    /**
     * Przyjmujemy w tej metodzie wizytatora, a nastepnie akceptujemy jego na sobie. Tak, aby wizytator mogl odwiedzic ten obiekt.
     * @param visitator przyjmuje obiekt implementujacy interfejs IVisitator
     */
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
