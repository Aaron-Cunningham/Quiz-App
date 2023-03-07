package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionStore {

    public void addSAQ() {

        IO IO = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
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

    public void addMCQ() {

        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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

    public void deleteSAQ() {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the question ID that you would like to delete (choose from the options provided below): ");

            // Get a list of existing questions from the database
            TypedQuery<Object[]> query = session.createQuery("SELECT q.ID, q.question FROM Question q", Object[].class);
            List<Object[]> questions = query.getResultList();

            // Print a list of existing question ID and question text
            System.out.println("\nExisting questions:");
            for (Object[] question : questions) {
                System.out.println("ID: " + question[0] + ", question: " + question[1]);
            }

            int question_ID = sc.nextInt();

            // Check if the question ID is in the list of questions
            boolean found = false;
            for (Object[] question : questions) {
                if ((int) question[0] == question_ID) {
                    found = true;
                    break;
                }
            }

            // If the question ID is not found, prompt the user with an error message
            if (!found) {
                System.out.println("Error: There is no question with ID " + question_ID);
                session.close();
                io.IOSystem();
            }

            // Otherwise, delete the question with the given ID
            TypedQuery query1 = session.createQuery("delete from Question where ID = :id");
            query1.setParameter("id", question_ID);
            int result = query1.executeUpdate();
            System.out.println(result + " question deleted.");

            // Commit the transaction and close the session
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            //if error roll back
            session.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            //Close session
            assert session != null;
            session.close();

        }
    }

    public void deleteMCQ() {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the question ID that you would like to delete (choose from the options provided below): ");

            // Get a list of existing quizIDs from the database
            TypedQuery<Object[]> query = session.createQuery("SELECT ID,question FROM MCQ ", Object[].class);
            List<Object[]> MCQs = query.getResultList();

            // Print a list of existing question ID and question (for question table)
            System.out.println("\nExisting MCQ ID and questions:");
            for (Object[] mcq : MCQs) {
                System.out.println(Arrays.toString(mcq));
            }

            System.out.println("Enter an appropriate MCQ ID that you would like to delete: ");
            int mcq_ID = sc.nextInt();

            // Check if the question ID is in the list of questions
            boolean found = false;
            for (Object[] mcq : MCQs) {
                if ((int) mcq[0] == mcq_ID) {
                    found = true;
                    break;
                }
            }

            // If the question ID is not found, prompt the user with an error message
            if (!found) {
                System.out.println("Error: There is no MCQ  with ID " + mcq_ID);
                session.close();
                io.IOSystem();
            }
            // Otherwise, delete the question with the given ID
            TypedQuery query1 = session.createQuery("delete from MCQ where id = :id");
            query1.setParameter("id", mcq_ID);
            int result = query1.executeUpdate();
            System.out.println(result + " MCQ deleted.");

            // Commit the transaction and close the session
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e){
            //if error roll back
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            //Close session
            assert session != null;
            session.close();
        }
    }

}
