package csc1035.project2;

import javax.persistence.*;
@Entity
@Table(name = "Questions")
public class Question {
    //Creates columns for the questions table in the database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int ID;
    @Column
    private String question;
    @Column
    private String category;
    @Column
    private String answer;


    @Column
    private int quiz_id;

    //Joins the Questions to a quiz via the quiz_id
    @ManyToOne
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    Quiz quiz;

    public Question() {

    }

    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * This method sets the quiz ID to q question
     * @param quiz_id
     */
    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getID() {
        return ID;
    }


    /**
     * Gets the quiz ID the question is related to
     * @return quiz_id
     */
    public int getQuiz_id() {
        return quiz_id;
    }


    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * gets the question from a question
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question for the question
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
        question = question.toLowerCase();
    }

    /**
     * Gets the category of a question
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * sets the category of a question
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the answer of a question
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answer of a question
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }


    /**
     * This construtor is representing a Question(SAQ)
     * @param question - The actual question been asked
     * @param category - The category ofn the question i.e maths, programming
     * @param answer - The answer of the question
     * @param quiz_id - This quiz ID the question is related to
     */
    public Question(String question, String category, String answer, int quiz_id) {
        this.question = question;
        this.category = category;
        this.answer = answer;
        this.quiz_id = quiz_id;
    }
}
