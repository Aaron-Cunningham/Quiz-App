package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class QuestionStore {

    public void addSAQ() {

        IO IO = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the question: ");
        String question = sc.nextLine();
        question = question.toLowerCase(); // such that the question entered converts to lower case characters

        // The category can only be Maths, Architecture, Databases and Programming
        System.out.println("\nEnter the category: ");
        String category = sc.nextLine();

        System.out.println("\nEnter the answer: ");
        String answer = sc.nextLine();
        answer = answer.toLowerCase(); // such that the answer entered converts to lower case characters

        System.out.println("\nEnter which of the following quizID's you would like to link this question with: ");

        // Get a list of existing quizIDs from the database (tale Quiz)
        TypedQuery<Integer> query = session.createQuery("SELECT DISTINCT ID FROM Quiz ", Integer.class);
        List<Integer> quizIDs = query.getResultList();

        // Print a list of existing quizIDs
        System.out.println("\nExisting quiz IDs:");
        for (Integer id : quizIDs) {
            System.out.println(id);
        }

        // Asking the user to choose from the following quizID's
        int quizID = sc.nextInt();

        // Check if quizID exists in the database (table Quiz)
        TypedQuery<Quiz> query1 = session.createQuery("FROM Quiz WHERE ID = :ID", Quiz.class);
        query1.setParameter("ID", quizID);
        List<Quiz> quizList = query1.getResultList();

        if (quizList.isEmpty()) {
            System.out.println("\nError: Quiz with quizID " + quizID + " does not exist.");
            session.close();
            IO.IOSystem();
        } else {
            Question Q = new Question(question, category, answer, quizID);

            session.save(Q);
            session.getTransaction().commit();
            System.out.println("\nQuestion added successfully to quizID: " + quizID);
        }
        session.close();
    }

    public void addMCQ(){

        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the question: ");
        String question = sc.nextLine();
        question = question.toLowerCase(); // such that the question entered converts to lower case characters

        System.out.println("\nEnter the category: ");
        String category = sc.nextLine();

        System.out.println("Enter possible answer");
        String answer1 = sc.nextLine();
        System.out.println("Enter the next possible answer");
        String answer2 = sc.nextLine();
        System.out.println("Enter the final possible answer");
        String answer3 = sc.nextLine();
        System.out.println("\nEnter the actual answer: ");
        String actualAnswer = sc.nextLine();
        actualAnswer = actualAnswer.toLowerCase(); // such that the answer entered converts to lower case characters

        System.out.println("\nEnter which of the following quizID's you would like to link this question with: ");

        // Get a list of existing quizIDs from the database
        TypedQuery<Integer> query = session.createQuery("SELECT DISTINCT  ID FROM Quiz ", Integer.class);
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
            MCQ Q = new MCQ(question, category, answer1, answer2, answer3, actualAnswer, quizID);

            session.save(Q);
            session.getTransaction().commit();
            System.out.println("\nQuestion added successfully to quizID: " + quizID);
        }
        session.close();

    }
    public void deleteQuestion() {
        // Print out the list of questions associated with the quiz_ID
        // Then select the questions that you would like to delete (maybe display the questionID with the question and then delete the question)
    }

    public void updateQuestion() {

    }


}
