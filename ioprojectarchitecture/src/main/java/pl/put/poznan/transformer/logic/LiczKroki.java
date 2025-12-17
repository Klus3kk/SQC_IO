package pl.put.poznan.transformer.logic;

public class LiczKroki implements VisitatorInterface {
    Integer ilosc = 0;

    public void visit(Scenariusz scenariusz) {}

    public void visit(Krok krok) {
        ilosc += 1;
    }

    public Integer getIloscKrokow() {
        return ilosc;
    }

}


