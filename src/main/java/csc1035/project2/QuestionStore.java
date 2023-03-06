package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class QuestionStore {

    public void addQuestion() {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the question: ");
        String question = sc.nextLine();
        question = question.toLowerCase(); // such that the question entered converts to lower case characters

        System.out.println("\nEnter the category: ");
        String category = sc.nextLine();

        System.out.println("\nEnter the answer: ");
        String answer = sc.nextLine();
        answer = answer.toLowerCase(); // such that the answer entered converts to lower case characters

        System.out.println("\nEnter which of the following quizID's you would like to link this question with: ");

        // Get a list of existing quizIDs from the database
        TypedQuery<Integer> query = session.createQuery("SELECT DISTINCT quiz_id FROM Question ", Integer.class);
        List<Integer> quizIDs = query.getResultList();

        // Print a list of existing quizIDs
        System.out.println("\nExisting quiz IDs:");
        for (Integer id : quizIDs) {
            System.out.println(id);
        }

        // Asking the user to choose from the following quizID's
        int quizID = sc.nextInt();

        // Check if quizID exists in the database
        TypedQuery<Question> query1 = session.createQuery("FROM Question WHERE quiz_id = :quiz_ID", Question.class);
        query1.setParameter("quiz_ID", quizID);
        List<Question> quizList = query1.getResultList();

        if (quizList.isEmpty()) {
            System.out.println("\nError: Quiz with quizID " + quizID + " does not exist.");
            session.close();
            io.IOSystem();
        } else {
            Question Q = new Question(question, category, answer, quizID);

            session.save(Q);
            session.getTransaction().commit();
            System.out.println("\nQuestion added successfully to quizID: " + quizID);
        }

        session.close();
    }

}
