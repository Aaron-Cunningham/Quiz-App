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

import javax.persistence.criteria.CriteriaQuery;

public class Csv {
    public static void main(String[] args) throws FileNotFoundException {
        save();
    }
    public static void save() throws FileNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        CriteriaQuery<Question> criteriaQuery1 = session.getCriteriaBuilder().createQuery(Question.class);
        criteriaQuery1.from(Question.class);
        List<Question> questionResults = session.createQuery(criteriaQuery1).getResultList();
        System.out.println(questionResults);
        CriteriaQuery<Quiz> criteriaQuery2 = session.getCriteriaBuilder().createQuery(Quiz.class);
        criteriaQuery2.from(Quiz.class);
        List<Quiz> quizResults = session.createQuery(criteriaQuery2).getResultList();
        System.out.println(quizResults);


        for (Object obj : questionResults) {
            Question question = (Question) obj;
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Category: " + question.getCategory());
            System.out.println("Answer: " + question.getAnswer());
        }
        for (Object obj : questionResults) {
            Question question = (Question) obj;
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Category: " + question.getCategory());
            System.out.println("Answer: " + question.getAnswer());
        }


        PrintWriter foo = new PrintWriter("questions.csv");
        foo.close();
        PrintWriter bar = new PrintWriter("quizzes.csv");
        bar.close();
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

        tx.commit();
        session.close();

    }
    public static void load() {

        // begin session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // delete all questions
        clearDatabase(session);

        // select all to test if database empty
        Query questionQuery = session.createQuery("SELECT COUNT(*) FROM Question");
        Query quizQuery = session.createQuery("SELECT COUNT(*) FROM Quiz");
        Long count = (Long)questionQuery.uniqueResult();
        Long count2 = (Long)quizQuery.uniqueResult();
        if ((count == 0)||(count2 == 0)) {
            // if not empty
            clearDatabase(session);
            Scanner quizscan = null;
            Scanner questionscan = null;
            try {
                // open csv files
                File quizzes = new File("quizzes.csv");
                File questions = new File("questions.csv");
                quizscan = new Scanner(quizzes);
                questionscan = new Scanner(questions);
                System.out.println("Files found");
            } catch (java.io.FileNotFoundException e) {
                System.out.println("1 or more files not found!");
            }

            ArrayList<Question> questionsList = new ArrayList<>();
            ArrayList<Quiz> quizzesList = new ArrayList<>();
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

            for (Quiz quiz : quizzesList) {
                session.save(quiz);
            }
            for (Question question : questionsList) {
                session.save(question);
            }
        }
        else {
            System.out.println("Database already populated");
        }
        tx.commit();
        session.close();
    }
    public static void clearDatabase(Session session) {
        Query query = session.createQuery("DELETE FROM Question");
        query.executeUpdate();
        Query query2 = session.createQuery("DELETE FROM Quiz");
        query2.executeUpdate();
    }
}
