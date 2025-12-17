package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Scenariusz implements AcceptVisitator {
    List<AcceptVisitator> kroki = new ArrayList<>();
    String tytul;

    public Scenariusz(String tytul) {
        this.tytul = tytul;
    }

    public void add(Krok k) {
        kroki.add(k);
    }

    public void add(Scenariusz s) {
        kroki.add(s);
    }

    public void accept(VisitatorInterface visitator) {
        visitator.visit(this);

        for (AcceptVisitator e : kroki) {
            e.accept(visitator);
        }
    }


}
