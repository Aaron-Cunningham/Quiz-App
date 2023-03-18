package csc1035.project2;

import javax.persistence.*;
@Entity
@Table(name = "Questions")
public class Question {
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

    @ManyToOne
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    Quiz quiz;

    public Question() {

    }

    public Quiz getQuiz() {
        return quiz;
    }

    public int setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
        return quiz_id;
    }

    public int getID() {
        return ID;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getQuestion() {
        return question;
    }

    public String setQuestion(String question) {
        this.question = question;
        question = question.toLowerCase();
        return question;
    }

    public String getCategory() {
        return category;
    }

    public String setCategory(String category) {
        this.category = category;
        return category;
    }

    public String getAnswer() {
        return answer;
    }

    public String setAnswer(String answer) {
        this.answer = answer;
        return answer;
    }



    public Question(String question, String category, String answer, int quiz_id) {
        this.question = question;
        this.category = category;
        this.answer = answer;
        this.quiz_id = quiz_id;
    }
}
