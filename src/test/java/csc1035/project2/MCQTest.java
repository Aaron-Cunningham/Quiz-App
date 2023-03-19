package csc1035.project2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MCQTest {

    //declaring private MCQ variable for testing
    private MCQ testMCQ;

    @BeforeEach
    void setUp() {
        testMCQ = new MCQ("What is 10+10?", "Fundamentals of Computing", "5", "10", "25", "20", 1);
    }

    @Test
    void getID() {
        assertEquals(0, testMCQ.getID());
    }

    @Test
    void setID() {
    }

    @Test
    void getQuestion() {
        assertEquals("What is 10+10?", testMCQ.getQuestion());
    }

    @Test
    void setQuestion() {
    }

    @Test
    void getCategory() {
        assertEquals("Fundamentals of Computing", testMCQ.getCategory());
    }

    @Test
    void setCategory() {
    }

    @Test
    void getAnswer1() {
        assertEquals("5", testMCQ.getAnswer1());
    }

    @Test
    void setAnswer1() {
    }

    @Test
    void getAnswer2() {
        assertEquals("10", testMCQ.getAnswer2());
    }

    @Test
    void setAnswer2() {
    }

    @Test
    void getAnswer3() {
        assertEquals("25", testMCQ.getAnswer3());
    }

    @Test
    void setAnswer3() {
    }

    @Test
    void getActualAnswer() {
        assertEquals("20", testMCQ.getActualAnswer());
    }

    @Test
    void setActualAnswer() {
    }

    @Test
    void getQuiz_id() {
        assertEquals(1, testMCQ.getQuiz_id());
    }

    @Test
    void setQuiz_id() {
    }

    @Test
    void getQuiz() {
        //assertEquals("Quiz", testMCQ.getQuiz());
    }

    @Test
    void setQuiz() {
    }
}