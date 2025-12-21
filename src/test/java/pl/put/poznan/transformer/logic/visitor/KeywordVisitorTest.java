package pl.put.poznan.transformer.logic.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.elements.Scenario;
import pl.put.poznan.transformer.logic.elements.Step;

import static org.junit.jupiter.api.Assertions.*;

class KeywordVisitorTest {

    @Test
    public void noKeywords() {
        Scenario scenario = new Scenario();
        Step step1 = new Step(); step1.setContent("adbfqo 2d abiu dq2nindaoi 2n"); scenario.add(step1);
        Step step2 = new Step(); step2.setContent("sdf asf dfdf ddsf fds df dfs"); scenario.add(step2);
        Step step3 = new Step(); step3.setContent("cdsfghgdsfdgnsdgfgsdfdadsfdg"); scenario.add(step3);

        KeywordVisitor visitor = new KeywordVisitor();
        scenario.accept(visitor);
        assertEquals(0, visitor.getCount());
    }


    @Test
    public void keywordIF() {
        Scenario scenario = new Scenario();
        Step step1 = new Step(); step1.setContent("adbfqo 2d abiu dq2nindaoi 2n"); scenario.add(step1);
        Step step2 = new Step(); step2.setContent(" if sdf asf dfdf ddsf fds df dfs"); scenario.add(step2);
        Step step3 = new Step(); step3.setContent("cdsfghgdsfdgnsdgfgsdfdadsfdg"); scenario.add(step3);

        KeywordVisitor visitor = new KeywordVisitor();
        scenario.accept(visitor);
        assertEquals(1, visitor.getCount());
    }


    @Test
    public void keywordELSE() {
        Scenario scenario = new Scenario();
        Step step1 = new Step(); step1.setContent("adbfqo 2d abiu dq2nindaoi 2n"); scenario.add(step1);
        Step step2 = new Step(); step2.setContent(" ElSE sdf asf dfdf ddsf fds df dfs"); scenario.add(step2);
        Step step3 = new Step(); step3.setContent("cdsfghgdsfdgnsdgfgsdfdadsfdg"); scenario.add(step3);

        KeywordVisitor visitor = new KeywordVisitor();
        scenario.accept(visitor);
        assertEquals(1, visitor.getCount());
    }



    @Test
    public void keywordFOREACH() {
        Scenario scenario = new Scenario();
        Step step1 = new Step(); step1.setContent("adbfqo 2d abiu dq2nindaoi 2n"); scenario.add(step1);
        Step step2 = new Step(); step2.setContent(" for each sdf asf dfdf ddsf fds df dfs"); scenario.add(step2);
        Step step3 = new Step(); step3.setContent("cdsfghgdsfdgnsdgfgsdfdadsfdg"); scenario.add(step3);

        KeywordVisitor visitor = new KeywordVisitor();
        scenario.accept(visitor);
        assertEquals(1, visitor.getCount());
    }


    @Test
    public void multipleKeywords() {
        Scenario scenario = new Scenario();
        Step step1 = new Step(); step1.setContent(" else adbfqo 2d abiu dq2nindaoi 2n"); scenario.add(step1);
        Step step2 = new Step(); step2.setContent(" for each sdf asf dfdf ddsf fds df dfs"); scenario.add(step2);
        Step step3 = new Step(); step3.setContent("cdsfghgdsfdgnsdgfgsdfdadsfdg"); scenario.add(step3);

        KeywordVisitor visitor = new KeywordVisitor();
        scenario.accept(visitor);
        assertEquals(2, visitor.getCount());
    }


    @Test
    public void keywordInSentence() {
        Scenario scenario = new Scenario();
        Step step1 = new Step(); step1.setContent("asf dssa else adbfqo 2d abiu dq2nindaoi 2n"); scenario.add(step1);
        Step step2 = new Step(); step2.setContent("sadf gsfad if sdf asf dfdf ddsf fds df dfs"); scenario.add(step2);
        Step step3 = new Step(); step3.setContent("cdsfghgdsfdgnsdgfgsdfdadsfdg"); scenario.add(step3);

        KeywordVisitor visitor = new KeywordVisitor();
        scenario.accept(visitor);
        assertEquals(0, visitor.getCount());
    }

}