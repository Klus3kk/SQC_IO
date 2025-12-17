package pl.put.poznan.transformer.logic;

public class Visitator {
    void visit(Scenariusz scenariusz) {
        System.out.println("wizyt scenariusz");
    }

    void visit(Krok krok) {
        System.out.println("wizyt krok");
    }

}

