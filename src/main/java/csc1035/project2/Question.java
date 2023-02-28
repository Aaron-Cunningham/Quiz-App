package csc1035.project2;

public class Question {
    String question, category, answer;

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

    public Question(String question, String category, String answer) {
        this.question = question;
        this.category = category;
        this.answer = answer;


    }
}
