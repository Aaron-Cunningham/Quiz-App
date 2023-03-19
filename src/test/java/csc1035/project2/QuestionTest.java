package csc1035.project2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    // Attributes and variables

    private  Question testQuestion;


    @BeforeEach
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
        testQuestion.setQuestion(expected);
        assertEquals(expected, testQuestion.getQuestion());
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
        testQuestion.setCategory(expected);
        assertEquals(expected, testQuestion.getCategory());
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
        testQuestion.setAnswer(expected);
        assertEquals(expected, testQuestion.getAnswer());

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
        testQuestion.setQuiz_id(expected);
        assertEquals(expected, testQuestion.getQuiz_id());
    }

}
