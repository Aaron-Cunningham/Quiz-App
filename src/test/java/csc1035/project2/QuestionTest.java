package csc1035.project2;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    // Attributes and variables

    private  Question testQuestion;


    @Before
    public void setUp() {
        testQuestion = new Question("What is two plus two?", "Maths", "4", 3);
    }

    @Test
    void getQuestion() {
        String expected =  "What is two plus two?";
        String actual = testQuestion.getQuestion();
        assertEquals(expected, actual);
    }

    @Test
    void setQuestion() {
        String expected = "What is four plus four?";
        String actual = testQuestion.setQuestion("What is four plus four?");
        assertEquals(expected, actual);
    }

    @Test
    void getCategory() {
        String expected = "Maths";
        String actual = testQuestion.getCategory();
        assertEquals(expected, actual);
    }

    @Test
    void setCategory() {
        String expected = "Programming";
        String actual = testQuestion.setCategory("Programming");
        assertEquals(expected, actual);
    }

    @Test
    void getAnswer() {
        String expected = "4";
        String actual = testQuestion.getAnswer();
        assertEquals(expected, actual);
    }

    @Test
    void setAnswer() {
        String expected = "8";
        String actual = testQuestion.setAnswer("8");
        assertEquals(expected, actual);

    }

    @Test
    void getQuizID() {
        int expected = 3;
        int actual = testQuestion.getQuiz_id();
        assertEquals(expected, actual);
    }

    @Test
    void setQuizID() {
        int expected = 2;
        int actual = testQuestion.setQuiz_id(2);
        assertEquals(expected, actual);
    }

}
