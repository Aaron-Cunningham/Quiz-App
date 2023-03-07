
package csc1035.project2;

import org.hibernate.Session;

public class addingData {
    public static void main(String[] args) {

        //Quizzes
        Quiz Math = new Quiz("Math", "CSC1031 Quiz", "Medium");
        Quiz architecture = new Quiz("Computer architecture", "CSC1032 Quiz", "Hard");
        Quiz databases = new Quiz("Databases", "CSC1033 Quiz", "Medium");
        Quiz programming = new Quiz("Programming", "CSC1036 Quiz", "Medium");

        //Maths questions
        Question maths1 = new Question("What is the square root of 25?", "Maths", "5", 1);
        Question maths2 = new Question("What is the value of x in the equation 2x + 5 = 11?", "Maths", "3", 1);
        Question maths3 = new Question("Simplify the expression 5a+2b-3a-4b", "Maths", "2a-2b", 1);

        //Architecture questions
        Question architecture1 = new Question("What is this binary number 0001?", "Architecture", "1", 2);
        Question architecture2 = new Question("What is the main memory of a computer called?", "Architecture", "RAM", 2);
        Question architecture3 = new Question("What is the arithmetic logic unit responsible for?", "Architecture", "Calculations", 2);
        Question architecture4 = new Question("What does the acronym CPU stand for? ", "Architecture", "Central Processing Unit", 2);

        //Databases questions
        Question database1 = new Question("What type of database stores data in tables?", "Databases", "Relational", 3);
        Question database2 = new Question("What is the process of finding specific data in a database called?", "Databases", "Querying", 3);
        Question database3 = new Question("What is the unique identifier for each record in a database table called?", "Databases", "Primary key", 3);
        Question database4 = new Question("What is the process of combining data from multiple tables called?", "Databases", "Joining", 3);

        //Programming questions
        Question programming1 = new Question("What is the most popular programming language as of 2023", "Programming", "Python", 4);
        Question programming2 = new Question("What is the process of finding and fixing errors in code called?", "Programming", "Debugging", 4);
        Question programming3 = new Question("What is the term used to describe a piece of reusable code?", "Programming", "Function", 4);
        Question programming4 = new Question("What is the act of converting source code into machine-readable code called?", "Programming", "Compiling", 4);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        /*
        Saves quizzes
         */
//        session.save(Math);
//        session.save(architecture);
//        session.save(databases);
//        session.save(programming);

        /*
        Sets the maths Questions
         */
//        maths1.setQuiz(Math);
//        maths2.setQuiz(Math);
//        maths3.setQuiz(Math);
        /*
        Saves the maths questions
         */
//        session.save(maths2);
//        session.save(maths3);

        /*
        Sets architecture questions
         */
//        architecture1.setQuiz(architecture);
//        architecture2.setQuiz(architecture);
//        architecture3.setQuiz(architecture);
//        architecture4.setQuiz(architecture);

        /*
        Saves architecture questions
         */
//        session.save(architecture1);
//        session.save(architecture2);
//        session.save(architecture3);
//        session.save(architecture4);


        /*
        Sets database questions
         */
        database1.setQuiz(databases);
        database2.setQuiz(databases);
        database3.setQuiz(databases);
        database4.setQuiz(databases);

        /*
        Saves database questions
         */
        session.save(database1);
        session.save(database2);
        session.save(database3);
        session.save(database4);

        /*
        Sets Programming questions
         */
        programming1.setQuiz(programming);
        programming2.setQuiz(programming);
        programming3.setQuiz(programming);
        programming4.setQuiz(programming);

        /*
        Saves Programming questions
         */
        session.save(programming1);
        session.save(programming2);
        session.save(programming3);
        session.save(programming4);



        //Commits changes
        session.getTransaction().commit();
        //Closes database
        session.close();
    }
}

