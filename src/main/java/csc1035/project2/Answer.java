package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class Answer {
    public void answerSCQ(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        System.out.println("What quiz would you like to do?");
        Query query = session.createQuery("SELECT q.ID, q.name FROM Quiz q");


        List<Object[]> quizzes = query.getResultList();
        System.out.println("Quizzes:");
        for (Object[] q: quizzes) {
            System.out.println("ID: " + q[0] + "\tName: " + q[1]);
        }




    }

    public static void main(String[] args) {
        Answer a = new Answer();
        a.answerSCQ();
    }
}
