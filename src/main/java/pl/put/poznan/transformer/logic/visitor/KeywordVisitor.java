package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

public class KeywordVisitor implements IVisitor{
    private Integer count = 0;

    @Override
    public void visit(Scenario scenario) {

    }

    @Override
    public void visit(Step step) {
        String loweredContext = step.getContent().toLowerCase();
        if(loweredContext.contains("if") || loweredContext.contains("else") || loweredContext.contains("for each")){
            count++;
        }
    }

    public Integer getCount(){
        return count;
    }
}
