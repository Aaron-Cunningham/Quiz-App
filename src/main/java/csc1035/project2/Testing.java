package csc1035.project2;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
public class Testing {
    public static void main(String[] args) {
        Scanner quizscan = null;
        Scanner questionscan = null;
        try {
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

        while (true) {
            assert quizscan != null;
            if (!quizscan.hasNextLine()) break;
            List<String> arguments = Arrays.asList(quizscan.nextLine().split(","));
            quizzesList.add(new Quiz(arguments.get(0), arguments.get(1), arguments.get(2)));
        }

        while (true) {
            assert questionscan != null;
            if (!questionscan.hasNextLine()) break;
            List<String> arguments = Arrays.asList(questionscan.nextLine().split(","));
            questionsList.add(new Question(arguments.get(0), arguments.get(1), arguments.get(2), Integer.parseInt(arguments.get(3))));
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (Quiz quiz : quizzesList) {
            session.save(quiz);
        }
        for (Question question : questionsList) {
            session.save(question);
        }
        session.getTransaction().commit();
        session.close();

    }
}
