package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @Column
    private int ID;
    @Column
    private String topic;
    @Column
    private String name;
    @Column
    private String difficulty;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<MCQ> MCQ;

    public void setMCQ(List<MCQ> MSQ) {
        this.MCQ = MSQ;
    }

    public Quiz(String topic, String name, String difficulty, int ID) {
        this.topic = topic;
        this.name = name;
        this.difficulty = difficulty;
        this.ID = ID;
    }

    public Quiz() {

    }

    public String getTopic() {
        return topic;
    }

    public String setTopic(String topic) {
        this.topic = topic;
        return topic;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<MCQ> getMCQ(){
        return MCQ;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "ID=" + ID +
                ", topic='" + topic + '\'' +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", questions=" + questions +
                ", MSQ=" + MCQ +
                '}';
    }
}
