package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class QuestionStore {

    // addQuestion(question, category, answer, quizID)
    public void addQuestion() {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the question: ");
        String question = sc.nextLine();

        System.out.println("\nEnter the category: ");
        String category = sc.nextLine();

        System.out.println("\nEnter the answer: ");
        String answer = sc.nextLine();

        System.out.println("\nEnter which of the following quizID's you would like to link this question with: ");
        int quizID = sc.nextInt();

        // Check if quizID exists in the database
        Query query = session.createQuery("FROM Quiz WHERE questions = :quizID");
        query.setParameter("quizID", quizID);
        List<Quiz> quizList = query.getResultList();

        if (quizList.isEmpty()) {
            System.out.println("\nError: Quiz with quizID " + quizID + " does not exist.");
            session.close();
            io.IOSystem();
        } else {
            Question Q = new Question(question, category, answer, quizID);

            session.save(Q);
            session.getTransaction().commit();
            System.out.println("\nQuestion added successfully to quiz " + quizID + ".");
        }

        session.close();
    }

    public static void deleteQuestion() {

    }

    public static void updateQuestion() {

    }

}
