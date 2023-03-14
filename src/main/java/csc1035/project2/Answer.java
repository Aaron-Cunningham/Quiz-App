package csc1035.project2;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Answer {

    /**
     * This method uses a for each loop to iterate through each question and then asks the user to answer it.
     * If the answer matches the user input they score a point.
     */
    public void answerSCQ(){
        //Sets Scanner as sc
        Scanner sc = new Scanner(System.in);
        //Open a hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        System.out.println("What quiz would you like to do?");
        //Query prints out the name and ID of quiz
        Query query = session.createQuery("SELECT q.ID, q.name FROM Quiz q");

        //Stores query in a list
        List<Object[]> quizzes = query.getResultList();
        System.out.println("Quizzes:");
        for (Object[] q: quizzes) {
            System.out.println("ID: " + q[0] + "\tName: " + q[1]);
        }

        //scanner for user to input a QuizID
        int quizID = sc.nextInt();
        sc.nextLine();
        Quiz targetQuiz = session.get(Quiz.class, quizID);

        //Count which is initially set to 0
        int count = 0;
        //For each loop to print out every question matching ID
        for (Question question : targetQuiz.getQuestions()){
            System.out.println(question.getQuestion());
            String userAnswer = sc.nextLine();
            //Checks answer matches user input
            if(userAnswer.equalsIgnoreCase(question.getAnswer())){
                System.out.println();
                count++;
            }
        }
        //Prints out the user score
        System.out.println("You scored " + count);

    }

    public static void main(String[] args) {
        Answer a = new Answer();
        a.answerSCQ();
    }
}
