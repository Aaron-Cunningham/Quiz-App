package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class QuestionStore {

    /**
     * Inside the function:
     * - First asks the user to enter the question,category, answer and quizID
     * - Note that when asking for the quizID (it checks if it exists within the database -> if yes then add, else delete)
     * - The queries used are hql based......
     */
    public void addQuestion() {

        // Create an instance of the IO class.
        IO io = new IO();

        // Create a new Hibernate session and begin a transaction.
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Create a new scanner object to read input from the user.
        Scanner sc = new Scanner(System.in);

        // Prompt the user to enter the question.
        System.out.println("\nEnter the question: ");
        String question = sc.nextLine();

        // Convert the question to lowercase.
        question = question.toLowerCase();

        // Prompt the user to enter the category.
        System.out.println("\nEnter the category: ");
        String category = sc.nextLine();

        // Prompt the user to enter the answer.
        System.out.println("\nEnter the answer: ");
        String answer = sc.nextLine();

        // Convert the answer to lowercase.
        answer = answer.toLowerCase();

        // Prompt the user to choose a quizID to link the question to.
        System.out.println("\nEnter which of the following quizID's you would like to link this question with: ");

        // Get a list of existing quizIDs from the database.
        TypedQuery<Integer> query = session.createQuery("SELECT DISTINCT quiz_id FROM Question ", Integer.class);
        List<Integer> quizIDs = query.getResultList();

        // Print a list of existing quizIDs.
        System.out.println("\nExisting quiz IDs:");
        for (Integer id : quizIDs) {
            System.out.println(id);
        }

        // Ask the user to choose from the following quizID's.
        int quizID = sc.nextInt();

        // Check if quizID exists in the database.
        TypedQuery<Question> query1 = session.createQuery("FROM Question WHERE quiz_id = :quiz_ID", Question.class);
        query1.setParameter("quiz_ID", quizID);
        List<Question> quizList = query1.getResultList();

        // If quizID doesn't exist, display an error message and exit the program.
        if (quizList.isEmpty()) {
            System.out.println("\nError: Quiz with quizID " + quizID + " does not exist.");
            session.close();
            io.IOSystem();
        }
        // Otherwise, create a new question object and add it to the database.
        else {
            Question Q = new Question(question, category, answer, quizID);
            session.save(Q);
            session.getTransaction().commit();
            System.out.println("\nQuestion added successfully to quizID: " + quizID);
        }

        // Close the Hibernate session.
        session.close();

    }
    public void deleteQuestion() {
        // Print out the list of questions associated with the quiz_ID
        // Then select the questions that you would like to delete (maybe display the questionID with the question and then delete the question)
    }


}
