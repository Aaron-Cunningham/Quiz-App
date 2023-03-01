package csc1035.project2;

import org.hibernate.Session;

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
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;


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

    public static void main(String[] args) {
        Quiz Math = new Quiz("Math", "CSC1031 Quiz", "Medium", 1);
        Quiz architecture = new Quiz("Computer architecture", "CSC1032 Quiz", "Hard", 2);
        Quiz databases = new Quiz("Databases", "CSC1033 Quiz", "Medium", 3);
        Quiz programming = new Quiz("Programming", "CSC1036 Quiz", "Medium", 4);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(Math);
        session.save(architecture);
        session.save(databases);
        session.save(programming);
        session.getTransaction().commit();
        session.close();
    }
}
