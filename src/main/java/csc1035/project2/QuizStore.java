package csc1035.project2;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizStore {


    /**
     * -Allows user to add a quiz to the database.
     * -Asks the user for the name of the quiz
     * -Checks if that quiz name already exists through a query
     * -If it doesn't exist it will ask for more details about the quiz, otherwise it will return to menu
     */

    public void addQuiz() {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Scanner sc = new Scanner(System.in);
            //Asks user for name of the quiz
            System.out.println("Enter the name of the quiz");
            String name = sc.nextLine();
            //Makes the topic lower case
            name = name.toLowerCase();
            //Querying the database to check if name is already there
            TypedQuery<Quiz> query = session.createQuery("FROM Quiz WHERE name = :name", Quiz.class);
            query.setParameter("name", name);

            //A list to store the results of the query
            List<Quiz> results = query.getResultList();
            //If there is an item in the list it will return this
            if (results.size() > 0) {
                System.err.println("This quiz already exists");
                session.close();
                io.IOSystem();
            }

            //If the results list is empty it will ask for more details.
            System.out.println("Enter the topic of the quiz");
            String topic = sc.nextLine();
            System.out.println("Enter the difficulty");
            String difficulty = sc.nextLine();
            System.out.println("Enter ID");
            int ID = sc.nextInt();

            TypedQuery<Quiz> queryID = session.createQuery("FROM Quiz WHERE ID = :ID");
            queryID.setParameter("ID", ID);
            List<Quiz> IDResults = queryID.getResultList();
            if (IDResults.size() > 0) {
                System.err.println("This quiz ID already exists");
                io.IOSystem();
            }

            Quiz quiz = new Quiz(topic, name, difficulty, ID);
            //Saves the quiz
            session.save(quiz);
            //Commits
            session.getTransaction().commit();
        } catch (HibernateException e) {
            //if error roll back
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            //Close session
            assert session != null;
            session.close();
        }
    }

    public void deleteQuiz() {

        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            Scanner sc = new Scanner(System.in);

            Query query = s.createQuery("SELECT q.ID, q.name FROM Quiz q");


            List<Object[]> quizzes = query.getResultList();
            System.out.println("Quizzes:");
            for (Object[] q : quizzes) {
                System.out.println("ID: " + q[0] + "\tName: " + q[1]);
            }

            System.out.println("\nPlease enter the ID of the quiz you would like to delete");
            int quizID = sc.nextInt();
            Quiz targetQuiz = s.get(Quiz.class, quizID);
            System.out.println(targetQuiz);
            s.delete(targetQuiz);
            s.getTransaction().commit();
        } finally {
            // Close session
            assert s != null; // verifies variable session is not null
            System.out.println("The SAQ has been successfully deleted... ");
            s.close();
        }
    }

    public void updateQuiz() {

        Session session = null;
        Session s = HibernateUtil.getSessionFactory().openSession();


        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter the ID of the quiz they want to update
        Query query = s.createQuery("SELECT q.ID, q.name, q.difficulty, q.topic FROM Quiz q");


        List<Object[]> quizzes = query.getResultList();
        System.out.println("Quizzes:");
        for (Object[] q : quizzes) {
            System.out.println("ID: " + q[0] + "\tName: " + q[1] + "\tDifficulty: " + q[2] +
                    "\tTopic: " + q[3]);
        }
        System.out.println("Please give the ID of the quiz you want to update: ");
        int questionUpdateID = scanner.nextInt();
        scanner.nextLine(); // buffer


        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Quiz quiz = (session.get(Quiz.class, questionUpdateID));


            System.out.println("Enter the new quiz name: ");
            String newName = scanner.nextLine();
            quiz.setName(newName);

            System.out.println("Enter the new topic name: ");
            String newTopic = scanner.nextLine();
            quiz.setTopic(newTopic);

            System.out.println("Enter the new difficulty level: ");
            String newDifficulty = scanner.nextLine();
            quiz.setDifficulty(newDifficulty);


            session.update(quiz);
            session.getTransaction().commit();


        } catch (HibernateException e) {
            if (session != null) session.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }

    }
    }

