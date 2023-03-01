package csc1035.project2;

import javax.persistence.*;
@Entity
@Table(name = "Questions")
public class Question {
    @Id
    @Column
    private int ID;
    @Column
    private String question;
    @Column
    private String category;
    @Column
    private String answer;

    @ManyToOne
    @JoinColumn(nullable = false)
    Quiz quiz;
    public Question() {

    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question(int ID, String question, String category, String answer) {
        this.question = question;
        this.category = category;
        this.answer = answer;
        this.ID = ID;


    }
}
