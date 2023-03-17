package csc1035.project2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    Quiz testQuiz = new Quiz("Programming","CSC1036 Quiz","Medium",4);
    private Quiz quiz;



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
    void getQuestions() {
    }

    @Test
    void getMCQ() {
    }

    @Test
    void setQuestions() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getDifficulty() {
    }

    @Test
    void setDifficulty() {
    }

    @Test
    void getId() {
    }

    @Test
    void setId() {
    }

}