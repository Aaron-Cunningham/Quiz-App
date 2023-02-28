package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;



public class Quiz {

    private String topic;

    private String name;

    private String difficulty;

    private ArrayList<Question> questions;

    public Quiz(String topic, ArrayList<Question> questions, String name, String difficulty) {
        this.topic = topic;
        this.questions = questions;
        this.name = name;
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<Question> getQuestions() {
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
}
