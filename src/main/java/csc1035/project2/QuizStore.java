package csc1035.project2;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class QuizStore {


    public void addQuiz(){
        IO io = new IO();
        int ID;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the topic of the quiz");
        String topic = sc.nextLine();
        //Makes the topic lower case
        topic.toLowerCase();
        //Querying the database to check if topic is already there
        String hql = "FROM Quiz WHERE topic = :topic";
        Query query = session.createQuery(hql);
        query.setParameter("topic", topic);
        List<Quiz> results = query.getResultList();
        if (results.size() > 0) {
            System.err.println("This quiz already exists");
            session.close();
            io.IOSystem();
        }

        topic.toLowerCase();
        System.out.println("Enter the name of the quiz");
        String name = sc.nextLine();
        System.out.println("Enter the difficulty");
        String difficulty = sc.nextLine();
        System.out.println("Enter the ID for your quiz");
        ID = sc.nextInt();
        Quiz quiz = new Quiz(topic, name, difficulty, ID);
        session.save(quiz);
        session.getTransaction().commit();
        session.close();
    }
}
