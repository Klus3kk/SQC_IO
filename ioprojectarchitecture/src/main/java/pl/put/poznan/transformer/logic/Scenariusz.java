package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Scenariusz implements AcceptVisitator {
    List<Krok> kroki = new ArrayList<>();
    String tytul;

    public Scenariusz(String tytul) {
        this.tytul = tytul;
    }

    public void addKrok(Krok k) {
        kroki.add(k);
    }

    public void accept(Visitator visitator) {
        visitator.visit(this);

        for (Krok k : kroki) {
            k.accept(visitator);
        }
    }


}
