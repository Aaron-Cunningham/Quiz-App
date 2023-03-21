package csc1035.project2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;

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
        testQuiz.setTopic("Maths");
        assertEquals(expected, testQuiz.getTopic());
    }

    @Test
    void getMCQ() {
        // getMCQ should return null as MCQ variable has not been instantiated
        assertNull(testQuiz.getMCQ());
    }

    @Test
    void setMCQ(){
        // create and populate new list of MCQ objects
        MCQ testMCQ = new MCQ("What is 10+10?", "Fundamentals of Computing", "5", "10", "25", "20", 1);
        List<MCQ> expectedMQCList = new ArrayList<>();
        expectedMQCList.add(testMCQ);

        testQuiz.setMCQ(expectedMQCList);
        assertIterableEquals(expectedMQCList, testQuiz.getMCQ());
    }
    @Test
    void getQuestions() {
        // getQuestions should return null as Questions variable has not been instantiated
        assertNull(testQuiz.getQuestions());
    }

    @Test
    void setQuestions() {
        // create and populate a new question list
        Question testQuestion = new Question("What is two plus two?", "Maths", "4", 3);
        ArrayList<Question> expectedQuestionList = new ArrayList<>();
        expectedQuestionList.add(testQuestion);

        testQuiz.setQuestions(expectedQuestionList);
        assertIterableEquals(expectedQuestionList, testQuiz.getQuestions());
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