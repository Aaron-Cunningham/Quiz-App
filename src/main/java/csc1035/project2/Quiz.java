package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int ID;
    @Column
    private String topic;
    @Column
    private String name;
    @Column
    private String difficulty;
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;


    public Quiz(String topic, String name, String difficulty) {
        this.topic = topic;
        this.name = name;
        this.difficulty = difficulty;
    }

    public Quiz() {

    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Question> getQuestions() {
        return questions;
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


}
