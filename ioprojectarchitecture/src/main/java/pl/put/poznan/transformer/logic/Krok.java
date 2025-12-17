package pl.put.poznan.transformer.logic;

public class Krok implements AcceptVisitator {
    public void accept(VisitatorInterface visitator) {
        visitator.visit(this);
    }
}
