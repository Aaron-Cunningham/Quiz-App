package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Quiz")
public class Quiz {
    //Create columns for the table in the database
    @Id
    @Column
    private int ID;
    @Column
    private String topic;
    @Column
    private String name;
    @Column
    private String difficulty;

    //Sets one-to-many relationship from quiz to SAQ
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    //Sets one-to-many relationship from quiz to MCQ
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<MCQ> MCQ;

    public void setMCQ(List<MCQ> MSQ) {
        this.MCQ = MSQ;
    }

    /**
     * This constructor represents a Quiz
     * @param topic - Relates to the topic of a quiz
     * @param name - Relates to the given ame of a quiz
     * @param difficulty - This is the difficulty of a Quiz
     * @param ID - This is the ID the quiz is given
     */
    public Quiz(String topic, String name, String difficulty, int ID) {
        this.topic = topic;
        this.name = name;
        this.difficulty = difficulty;
        this.ID = ID;
    }

    public Quiz() {

    }

    /**
     * This is a getter method to get the topic of a quiz
     * @return topic of a quiz
     */
    public String getTopic() {
        return topic;
    }

    /**
     * This is a setter method for topic
     * @param topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * This is a getter method for questions in a quiz
     * @return a list of SAQ from a quiz
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * This is a getter method for MCQ
     * @return a list of MCQ from a quiz
     */
    public List<MCQ> getMCQ(){
        return MCQ;
    }

    /**
     * This method sets a question
     * @param questions
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * This method sets the name of a quiz
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method gets the difficulty of a quiz (Easy, Medium, Hard)
     * @return difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * This method sets the difficulty of a quiz
     * @param difficulty
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * This method gets the Quiz ID
     * @return Quiz ID
     */
    public int getId() {
        return ID;
    }

    /**
     * This method sets the ID of a quiz
     * @param id
     */
    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
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
