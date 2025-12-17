package pl.put.poznan.transformer.logic;

public class Krok implements AcceptVisitator {
    public void accept(Visitator visitator) {
        visitator.visit(this);
    }
}
