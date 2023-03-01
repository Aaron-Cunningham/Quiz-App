package csc1035.project2;

import org.hibernate.Session;

public class addingData {
    public static void main(String[] args) {
        Quiz Math = new Quiz("Math", "CSC1031 Quiz", "Medium", 1);
        Quiz architecture = new Quiz("Computer architecture", "CSC1032 Quiz", "Hard", 2);
        Quiz databases = new Quiz("Databases", "CSC1033 Quiz", "Medium", 3);
        Quiz programming = new Quiz("Programming", "CSC1036 Quiz", "Medium", 4);
        Question maths1 = new Question("What is the square root of 25?", "Maths", "5");
        Question maths2 = new Question("What is the value of x in the equation 2x + 5 = 11?", "Maths", "3");
        Question maths3 = new Question("Simplify the expression 5a+2b-3a-4b", "Maths", "2a-2b");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        /*
        Saves quizzes
         */
        session.save(Math);
        session.save(architecture);
        session.save(databases);
        session.save(programming);

        /*
        Sets the maths Questions
         */
        maths1.setQuiz(Math);
        maths2.setQuiz(Math);
        maths3.setQuiz(Math);
        /*
        Saves the maths questions
         */
        session.save(maths2);
        session.save(maths3);
        //Commits changes
        session.getTransaction().commit();
        //Closes database
        session.close();
    }
}
