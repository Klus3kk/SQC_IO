package pl.put.poznan.transformer.logic.visitor;

import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

public class KeywordVisitor implements IVisitor{
    private static final String[] keywords = {"if", "else", "for each"};
    private Integer count = 0;

    @Override
    public void visit(Scenario scenario) {

    }

    @Override
    public void visit(Step step) {
        String loweredContext = step.getContent().toLowerCase().strip();
        for(String keyword : keywords){
            if(loweredContext.startsWith(keyword)){
                count++;
                break;
            }
        }
    }

    public Integer getCount(){
        return count;
    }
}
