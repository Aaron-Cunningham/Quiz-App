package csc1035.project2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MCQTest {

    // declaring private MCQ variable for testing
    private MCQ testMCQ;

    // initialising MCQ variable
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
        int expected = 5;
        testMCQ.setID(expected);
        assertEquals(expected, testMCQ.getID());
    }

    @Test
    void getQuestion() {
        assertEquals("What is 10+10?", testMCQ.getQuestion());
    }

    @Test
    void setQuestion() {
        String expected = "What is 1000*1000?";
        testMCQ.setQuestion(expected);
        assertEquals(expected, testMCQ.getQuestion());
    }

    @Test
    void getCategory() {
        assertEquals("Fundamentals of Computing", testMCQ.getCategory());
    }

    @Test
    void setCategory() {
        String expected = "Programming";
        testMCQ.setCategory(expected);
        assertEquals(expected, testMCQ.getCategory());

    }

    @Test
    void getAnswer1() {
        assertEquals("5", testMCQ.getAnswer1());
    }

    @Test
    void setAnswer1() {
        String expected = "1";
        testMCQ.setAnswer1(expected);
        assertEquals(expected, testMCQ.getAnswer1());
    }

    @Test
    void getAnswer2() {
        assertEquals("10", testMCQ.getAnswer2());
    }

    @Test
    void setAnswer2() {
        String expected = "1000";
        testMCQ.setAnswer2(expected);
        assertEquals(expected, testMCQ.getAnswer2());
    }

    @Test
    void getAnswer3() {
        assertEquals("25", testMCQ.getAnswer3());
    }

    @Test
    void setAnswer3() {
        String expected = "99999";
        testMCQ.setAnswer3(expected);
        assertEquals(expected, testMCQ.getAnswer3());
    }

    @Test
    void getActualAnswer() {
        assertEquals("20", testMCQ.getActualAnswer());
    }

    @Test
    void setActualAnswer() {
        String expected = "123";
        testMCQ.setActualAnswer(expected);
        assertEquals(expected, testMCQ.getActualAnswer());
    }

    @Test
    void getQuiz_id() {
        assertEquals(1, testMCQ.getQuiz_id());
    }

    @Test
    void setQuiz_id() {
        int expected = 9;
        testMCQ.setQuiz_id(expected);
        assertEquals(expected, testMCQ.getQuiz_id());
    }
/*
    @Test
    void getQuiz() {
        //assertEquals(Quiz, testMCQ.getQuiz());
    }

    @Test
    void setQuiz() {


    }
 */
}