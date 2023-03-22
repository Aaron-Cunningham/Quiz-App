package csc1035.project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;


public class Csv {

    public static void main(String[] args) {
        load();
    }
    public static void save() throws FileNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        CriteriaQuery<Question> criteriaQuery1 = session.getCriteriaBuilder().createQuery(Question.class);
        criteriaQuery1.from(Question.class);
        List<Question> questionResults = session.createQuery(criteriaQuery1).getResultList();
        CriteriaQuery<Quiz> criteriaQuery2 = session.getCriteriaBuilder().createQuery(Quiz.class);
        criteriaQuery2.from(Quiz.class);
        List<Quiz> quizResults = session.createQuery(criteriaQuery2).getResultList();
        CriteriaQuery<MCQ> criteriaQuery3 = session.getCriteriaBuilder().createQuery(MCQ.class);
        criteriaQuery3.from(MCQ.class);
        List<MCQ> mcQuestionResults = session.createQuery(criteriaQuery3).getResultList();

        PrintWriter foo = new PrintWriter("questions.csv");
        foo.close();
        PrintWriter bar = new PrintWriter("quizzes.csv");
        bar.close();
        PrintWriter foobar = new PrintWriter("mcquestions.csv");
        foobar.close();
        PrintWriter questionWriter = new PrintWriter(new FileOutputStream("questions.csv", true));
        questionWriter.print("");
        for (Object obj : questionResults) {
            Question question = (Question) obj;
            questionWriter.println(question.getQuestion() + "," + question.getCategory() + "," + question.getAnswer() + "," + question.getQuiz_id());
        }
        questionWriter.close();
        PrintWriter quizWriter = new PrintWriter(new FileOutputStream("quizzes.csv", true));
        quizWriter.print("");
        for (Object obj : quizResults) {
            Quiz quiz = (Quiz) obj;
            quizWriter.println(quiz.getTopic() + "," + quiz.getName() + "," + quiz.getDifficulty() + "," + quiz.getId());
        }
        quizWriter.close();
        PrintWriter mcqWriter = new PrintWriter(new FileOutputStream("mcquestions.csv", true));
        mcqWriter.print("");
        for (Object obj : mcQuestionResults) {
            MCQ mcq = (MCQ) obj;
            mcqWriter.println(mcq.getQuestion() + "," + mcq.getCategory() + "," + mcq.getAnswer1() + "," + mcq.getAnswer2() + "," + mcq.getAnswer3() + "," + mcq.getActualAnswer() + "," + mcq.getID());
        }
        mcqWriter.close();

        tx.commit();
        session.close();

    }
    public static void load() {

        // begin session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // delete all questions
        ClearDatabase();

        // select all to test if database empty
//        Query questionQuery = session.createQuery("SELECT COUNT(*) FROM Question");
//        Query quizQuery = session.createQuery("SELECT COUNT(*) FROM Quiz");
//        Long count = (Long)questionQuery.uniqueResult();
//        Long count2 = (Long)quizQuery.uniqueResult();
//        if ((count == 0)||(count2 == 0)) {
            // if not empty
        Scanner quizscan = null;
        Scanner questionscan = null;
        Scanner mcquestionscan = null;
        try {
            // open csv files
            File quizzes = new File("quizzes.csv");
            File questions = new File("questions.csv");
            File mcquestions = new File("mcquestions.csv");
            quizscan = new Scanner(quizzes);
            questionscan = new Scanner(questions);
            mcquestionscan = new Scanner(mcquestions);
            System.out.println("Files found");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("1 or more files not found!");
        }

        ArrayList<Question> questionsList = new ArrayList<>();
        ArrayList<Quiz> quizzesList = new ArrayList<>();
        ArrayList<MCQ> mcQuestionsList = new ArrayList<>();
        // make lists of questions and quizzes read from files

        while (true) {
            assert quizscan != null;
            // while there is still more csv quizzes
            if (!quizscan.hasNextLine()) break;
            List<String> arguments = Arrays.asList(quizscan.nextLine().split(","));
            // split csv
            quizzesList.add(new Quiz(arguments.get(0), arguments.get(1), arguments.get(2), Integer.parseInt(arguments.get(3))));
            // add new objects
        }

        while (true) {
            assert questionscan != null;
            if (!questionscan.hasNextLine()) break;
            List<String> arguments = Arrays.asList(questionscan.nextLine().split(","));
            questionsList.add(new Question(arguments.get(0), arguments.get(1), arguments.get(2), Integer.parseInt(arguments.get(3))));
        }

        while (true) {
            assert mcquestionscan != null;
            if (!mcquestionscan.hasNextLine()) break;
            List<String> arguments = Arrays.asList(mcquestionscan.nextLine().split(","));
            mcQuestionsList.add(new MCQ(arguments.get(0), arguments.get(1), arguments.get(2), arguments.get(3), arguments.get(4),arguments.get(5), Integer.parseInt(arguments.get(6))));
        }

        for (Quiz quiz : quizzesList) {
            session.save(quiz);
        }
        for (Question question : questionsList) {
            session.save(question);
        }
        for (MCQ mcq : mcQuestionsList) {
            session.save(mcq);
        }
        tx.commit();
        session.close();
    }
    public static void ClearDatabase() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        TypedQuery<Quiz> query = session.createQuery("FROM Quiz", Quiz.class);
        List<Quiz> quizzes = query.getResultList();

        for(Quiz quiz:quizzes){
            session.delete(quiz);

        }
        tx.commit();
        session.close();

    }
}
