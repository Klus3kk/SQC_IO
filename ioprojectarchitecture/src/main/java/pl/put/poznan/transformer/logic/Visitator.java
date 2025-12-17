package pl.put.poznan.transformer.logic;

public class Visitator implements VisitatorInterface {
    public void visit(Scenariusz scenariusz) {
        System.out.println("wizyt scenariusz");
    }

    public void visit(Krok krok) {
        System.out.println("wizyt krok");
    }

}

