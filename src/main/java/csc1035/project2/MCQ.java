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



    public MCQ(String question, String category, String answer1, String answer2, String answer3, String actualAnswer, int quiz_id) {
        this.question = question;
        this.category = category;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.actualAnswer = actualAnswer;
        this.quiz_id = quiz_id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getActualAnswer() {
        return actualAnswer;
    }

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

    @Override
    public String toString() {
        return
                "Question: " + question + '\'' +
                "Category: " + category + '\'' +
                "Correct Answer: " + actualAnswer + '\'';
    }
}
