package csc1035.project2;


import javax.persistence.*;

@Entity
@Table(name = "MSQQuestions")
public class MCQ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int ID;

    @Column
    String question;
    @Column
    String category;
    @Column
    String answer1;
    @Column
    String answer2;
    @Column
    String answer3;
    @Column
    String actualAnswer;

    @Column
    private int quiz_id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    Quiz quiz;


    /**
     * This contrsutor is representing a MCQ object
     * @param question - The question been asked
     * @param category - The category the question is related to
     * @param answer1 - Possible answer 1
     * @param answer2 - Possible answer 2
     * @param answer3 - Possible answer 3
     * @param actualAnswer - This is one of the possible answers
     * @param quiz_id - The Quiz the question is connected to
     */
    public MCQ(String question, String category, String answer1, String answer2, String answer3, String actualAnswer, int quiz_id) {
        this.question = question;
        this.category = category;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.actualAnswer = actualAnswer;
        this.quiz_id = quiz_id;
    }

    /**
     * This method returns the ID of the quiz
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * This method sets the ID of the quiz
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * This method gets the question
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * This method sets the question
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * This method gets the category of a question
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method sets the category of a quesiton
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * This method gets a possible answer
     * @return answer1
     */
    public String getAnswer1() {
        return answer1;
    }

    /**
     * This methods sets a possible answer
     * @param answer1
     */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    /**
     * This method gets a second possible answer
     * @return answer2
     */
    public String getAnswer2() {
        return answer2;
    }

    /**
     * This method sets a possible answer
     * @param answer2
     */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    /**
     * This method gets a 3rd possible answer
     * @return
     */
    public String getAnswer3() {
        return answer3;
    }

    /**
     * This method sets a 3rd possible answer
     * @param answer3
     */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    /**
     * This method returns the Actual answer
     * @return
     */
    public String getActualAnswer() {
        return actualAnswer;
    }

    /**
     * This method sets the actual answer
     * @param actualAnswer
     */
    public void setActualAnswer(String actualAnswer) {
        this.actualAnswer = actualAnswer;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public MCQ() {

    }


    /**
     * This method prints out the MCQ in this following format
     * @return question, category, actualAnswer
     */
    @Override
    public String toString() {
        return
                "Question: " + question + '\'' +
                "Category: " + category + '\'' +
                "Correct Answer: " + actualAnswer + '\'';
    }
}
