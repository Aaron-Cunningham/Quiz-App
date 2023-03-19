package csc1035.project2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    private Quiz testQuiz;

    @BeforeEach
    void setUp() {
        testQuiz = new Quiz("Programming","CSC1036 Quiz","Medium",4);
    }

    @Test
    void getTopic() {
        String expected =  "Programming";
        String actual = testQuiz.getTopic();
        assertEquals(expected, actual);
    }

    @Test
    void setTopic() {
        String expected = "Maths";
        String actual = testQuiz.setTopic("Maths");
        assertEquals(expected, actual);
    }

    @Test
    void getMCQ() {
    }

    @Test
    void setMCQ(){

    }
    @Test
    void getQuestions() {
    }

    @Test
    void setQuestions() {
    }

    @Test
    void getName() {
        assertEquals("CSC1036 Quiz", testQuiz.getName());
    }

    @Test
    void setName() {
        String expected = "Test Name";
        testQuiz.setName(expected);
        assertEquals(expected, testQuiz.getName());
    }

    @Test
    void getDifficulty() {
        assertEquals("Medium", testQuiz.getDifficulty());
    }

    @Test
    void setDifficulty() {
        String expected = "Easy";
        testQuiz.setDifficulty(expected);
        assertEquals(expected, testQuiz.getDifficulty());
    }

    @Test
    void getId() {
        assertEquals(4, testQuiz.getId());
    }

    @Test
    void setId() {
        int expected = 6;
        testQuiz.setId(expected);
        assertEquals(expected, testQuiz.getId());
    }
}