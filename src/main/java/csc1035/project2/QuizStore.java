package csc1035.project2;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class QuizStore {


    /**
     * -Allows user to add a quiz to the database.
     * -Asks the user for the name of the quiz
     * -Checks if that quiz name already exists through a query
     * -If it doesn't exist it will ask for more details about the quiz, otherwise it will return to menu
     */

    public void addQuiz(){
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
            if(IDResults.size() > 0){
                System.err.println("This quiz ID already exists");
                io.IOSystem();
            }

            Quiz quiz = new Quiz(topic, name, difficulty, ID);
            //Saves the quiz
            session.save(quiz);
            //Commits
            session.getTransaction().commit();
        }catch (HibernateException e){
            //if error roll back
            if(session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            //Close session
            assert session != null;
            session.close();
        }
    }

    public void deleteQuiz(){

    }

}
