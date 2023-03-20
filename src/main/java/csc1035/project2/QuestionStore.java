package csc1035.project2;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionStore {

    /**
     * Method for adding SAQs to database
     * - Input question, category, answer
     * - Assign it to an existing quiz ID
     * - If typed quiz ID does not exist then prompt error
     * - Otherwise successfully assign the SAQ to the Question table
     */
    public void addSAQ() throws FileNotFoundException {

        IO IO = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Scanner sc = new Scanner(System.in);

            // Takes the input from the user for question
            System.out.println("\nEnter the question (SAQ): ");
            String question = sc.nextLine();
            question = question.toLowerCase(); // such that the question entered converts to lower case characters

            // Takes the input from the user for the questions category
            System.out.println("\nEnter the category: ");
            String category = sc.nextLine();

            // Takes the input from the user for the answer
            System.out.println("\nEnter the answer: ");
            String answer = sc.nextLine();
            answer = answer.toLowerCase(); // such that the answer entered converts to lower case characters

            Query query = session.createQuery("SELECT ID, name FROM Quiz");


            List<Object[]> quizzes = query.getResultList();
            System.out.println("Enter ID of Quiz you wish to add question to: ");
            for (Object[] q : quizzes) {
                System.out.println("ID: " + q[0] + "\tName: " + q[1]);
            }

            // Asking the user to choose from the following quizID's
            int quizID = sc.nextInt();

            // Check if quizID exists in the database (table Quiz)
            TypedQuery<Quiz> query1 = session.createQuery("FROM Quiz WHERE ID = :ID", Quiz.class);
            query1.setParameter("ID", quizID);
            List<Quiz> quizList = query1.getResultList();

            // If statement such that if the quiz list is empty, then prompt an error that the quiz ID doesn't exist
            if (quizList.isEmpty()) {
                System.out.println("\nError: Quiz with quizID " + quizID + " does not exist.");
                session.close(); // close the session
                IO.IOSystem(); // print the IO system
            } else {
                // Set a new question with the elements (question, category, answer, quizID used in Question class
                Question Q = new Question(question, category, answer, quizID);

                // Setting the question using the inputted elements
                Q.setQuestion(question);
                Q.setCategory(category);
                Q.setAnswer(answer);
                Q.setQuiz_id(quizID);

                // Save and commit message
                session.save(Q);
                session.getTransaction().commit();
                System.out.println("\nQuestion added successfully to quizID: " + quizID);
            }

            session.close();

        } catch (HibernateException e) {
            // if error roll back
            if (session != null) session.getTransaction().rollback(); // if the session is null then roll back
            e.printStackTrace(); // handles the exception and errors

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //Close session
            assert session != null; // verifies variable session is not null
            session.close(); // close session
        }
        Csv.save();
    }

    /**
     * Method for adding an MCQ to MCQ table
     * - Takes the user input's for the following elements:
     * - question, category, possible answer1, possible answer2, possible answer 3, actual answer
     * - These are then assigned to the selected quiz ID entered by the user
     */
    public void addMCQ() throws FileNotFoundException {

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

            System.out.println("Enter a wrong answer");
            String wrongAnswer1 = sc.nextLine();

            System.out.println("Enter the next wrong answer");
            String wrongAnswer2 = sc.nextLine();

            System.out.println("Enter the final wrong answer");
            String wrongAnswer3 = sc.nextLine();

            System.out.println("\nEnter the actual answer: ");
            String rightAnswer = sc.nextLine();


            rightAnswer = rightAnswer.toLowerCase(); // such that the answer entered converts to lower case characters

            System.out.println("\nEnter which of the following quizID's you would like to link this question with: ");

            // Get a list of existing quizIDs from the database
            TypedQuery<Object[]> query = session.createQuery("SELECT q.ID, q.name FROM Quiz q", Object[].class);
            List<Object[]> quizIDs = query.getResultList();


            // Print a list of existing quizIDs
            System.out.println("\nExisting quiz IDs:");
            System.out.println("\nExisting questions:");
            for (Object[] quiz : quizIDs) {
                System.out.println("ID: " + quiz[0] + ", name: " + quiz[1]);
            }


            // Asking the user to choose from the following quizID's
            int quizID = sc.nextInt();

            // Check if quizID exists in the database
            TypedQuery<Quiz> query1 = session.createQuery("FROM Quiz WHERE ID = :quiz_ID", Quiz.class);
            query1.setParameter("quiz_ID", quizID);
            List<Quiz> quizList = query1.getResultList();

            // If the quiz List is empty, then there should be a prompted error... that the quizID does not exist
            if (quizList.isEmpty()) {
                System.out.println("\nError: Quiz with quizID " + quizID + " does not exist.");
                session.close();
                io.IOSystem();

            } else {
                MCQ Q = new MCQ(question, category, wrongAnswer1, wrongAnswer2, wrongAnswer3, rightAnswer, quizID);

                // Using the setters from the MCQ class to set the MCQs into the database
                Q.setQuestion(question);
                Q.setCategory(category);
                Q.setAnswer1(wrongAnswer1);
                Q.setAnswer2(wrongAnswer2);
                Q.setAnswer3(wrongAnswer3);
                Q.setActualAnswer(rightAnswer);
                Q.setQuiz_id(quizID);

                // Saving session
                session.save(Q);
                session.getTransaction().commit();
                System.out.println("\nQuestion added successfully to quizID: " + quizID);
            }
            session.close();

        } catch (HibernateException e) {
            //if error roll back
            if (session != null) session.getTransaction().rollback(); // if the session is null then roll back
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //Close session
            assert session != null; // verifies variable session is not null
            session.close();
        }
        Csv.save();
    }

    /**
     * Method for deleting SAQs from the Question table:
     *  - Retrieve a list from the Question table for the QuestionID and question
     *  - User input for choosing the QuestionID
     *  - If the QuestionID is valid and within the database, then the program will delete the SAQ otherwise prompt an error
     */
    public void deleteSAQ() throws FileNotFoundException {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
            Question Qs = session.get(Question.class, question_ID);
            session.delete(Qs);

            // Commit the transaction and close the session
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            //if error roll back
            if (session != null) session.getTransaction().rollback(); // if the session is null then roll back
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //Close session
            assert session != null; // verifies variable session is not null
            System.out.println("The SAQ has been successfully deleted... ");
            session.close();

        }
        Csv.save();
    }

    /**
     * Method for deleting MCQ from the MCQ table:
     * - Retrieve a list from the Question table for the MCQ ID and question
     * - User input for choosing the MCQ ID
     * - If the MCQ ID is valid and within the database, then the program will delete the MCQ otherwise prompt an error
     */
    public void deleteMCQ() throws FileNotFoundException {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the question ID that you would like to delete (choose from the options provided below): ");

            // Get a list of existing MCQ IDs from the database (put in an object to read 2 entities from the database)
            TypedQuery<Object[]> query = session.createQuery("SELECT ID,question FROM MCQ ", Object[].class);
            List<Object[]> MCQs = query.getResultList();

            // Print a list of existing question ID and question (for question table)
            System.out.println("\nExisting MCQ ID and questions:");
            for (Object[] mcq : MCQs) {
                System.out.println(Arrays.toString(mcq));
            }

            System.out.println("Enter an appropriate MCQ ID that you would like to delete: ");
            int mcq_ID = sc.nextInt();

            // Check if the MCQ ID is in the list of questions
            boolean found = false;
            for (Object[] mcq : MCQs) {
                if ((int) mcq[0] == mcq_ID) {
                    found = true;
                    break;
                }
            }

            // If the MCQ ID is not found, prompt the user with an error message
            if (!found) {
                System.out.println("Error: There is no MCQ  with ID " + mcq_ID);
                session.close();
                io.IOSystem();
            }
            // Otherwise, delete the MCQ with the given ID
            MCQ mCq = session.get(MCQ.class, mcq_ID);
            session.delete(mCq);

            // Commit the transaction and close the session
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            //if error roll back
            if (session != null) session.getTransaction().rollback(); // if the session is null then roll back
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //Close session
            assert session != null; // verifies variable session is not null
            System.out.println("The MCQ has been successfully deleted... ");
            session.close();
        }
        Csv.save();
    }

    /**
     * Method for updating SAQ from Question table:
     *  - Retrieves list of ID and questions from the Question table (give options for user to choose from)
     *  - Asks user for which question ID they would like to edit
     *  - If all is correct, then program prints the entire row of the specified question ID
     *  - Then the user is asked what element they would like to update within the row
     *  - Then there are different cases and options for updating the different elements within the question table
     */
    public void updateSAQ() throws FileNotFoundException {
        IO io = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the question ID you would like to update from the following options:");

            // Get a list of existing questions from the database
            TypedQuery<Object[]> query = session.createQuery("SELECT q.ID, q.question FROM Question q", Object[].class);
            List<Object[]> questionIds = query.getResultList();

            // Print a list of existing question ID and question text
            System.out.println("\nExisting questions:");
            for (Object[] question : questionIds) {
                System.out.println("ID: " + question[0] + ", question: " + question[1]);
            }

            System.out.println("Please enter a valid question ID you would like to edit: ");
            int qID = sc.nextInt();

            // Check if the MCQ ID is in the list of questions
            boolean found = false;
            for (Object[] mcq : questionIds) {
                if ((int) mcq[0] == qID) {
                    found = true;
                    break;
                }
            }

            // If the MCQ ID is not found, prompt the user with an error message
            if (!found) {
                System.out.println("Error: There is no MCQ  with ID " + qID);
                session.close();
                io.IOSystem();
            }

            Question question = session.get(Question.class, qID);

            // Printing the inputted row
            System.out.println("This is the row of the question ID you wanted to update: ");
            System.out.printf("[%d, %s, %s, %s, %d]%n", question.getID(), question.getQuestion(), question.getCategory(), question.getAnswer(), question.getQuiz_id());

            int option;
            do {
                System.out.println("Choose an option on which element you would like to update from the above row displayed: \n"
                        + "1: Update Question\n"
                        + "2: Update Category\n"
                        + "3: Update Answer\n"
                        + "4: Update Quiz ID\n"
                        + "0: Exit update menu");

                while (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid option (1-5 or 0 to exit)");
                    sc.next();
                }
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Please enter a new question:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newQuestion = sc.nextLine();
                        question.setQuestion(newQuestion);
                        break;

                    case 2:
                        System.out.println("Please enter a new category:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newCategory = sc.nextLine();
                        question.setCategory(newCategory);
                        break;

                    case 3:
                        System.out.println("Please enter a new answer:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newAnswer = sc.nextLine();
                        question.setAnswer(newAnswer);
                        break;

                    case 4:
                        System.out.println("Please enter a new quiz ID:");
                        int newQuizID = sc.nextInt();
                        boolean quizExists = false;

                        TypedQuery<Quiz> query1 = session.createQuery("FROM Quiz WHERE ID = :id", Quiz.class);
                        query1.setParameter("id", newQuizID);
                        List<Quiz> results = query1.getResultList();
                        if (!results.isEmpty()) {
                            quizExists = true;
                        }
                        if (quizExists) {
                            question.setQuiz_id(newQuizID);
                        } else {
                            System.out.println("Invalid quiz ID");
                        }
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Please enter a valid option (1-5 or 0 to exit)");
                        break;
                }

                session.update(question);
                session.getTransaction().commit();

                io.IOSystem();

            } while (option != 0);

        } catch (HibernateException e) {
            //if error roll back
            session.getTransaction().rollback(); // if the session is null then roll back
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //Close session
            session.close();
        }
        Csv.save();
    }

    /**
     * Method for updating MCQ from Question table:
     *  - Retrieves list of ID and questions from the MCQ table (give options for user to choose from)
     *  - Asks user for which MCQ ID they would like to edit
     *  - If all is correct, then program prints the entire row of the specified MCQ ID
     *  - Then the user is asked what element they would like to update within the row
     *  - Then there are different cases and options for updating the different elements within the question table
     */
    public void updateMCQ() throws FileNotFoundException {
        IO IO = new IO();
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the question ID you would like to update from the following options:");

            // Get a list of existing questions from the database
            TypedQuery<Object[]> query = session.createQuery("SELECT m.ID, m.question FROM MCQ m", Object[].class);
            List<Object[]> MCQIds = query.getResultList();

            // Print a list of existing question ID and question text
            System.out.println("\nExisting questions:");
            for (Object[] question : MCQIds) {
                System.out.println("ID: " + question[0] + ", question: " + question[1]);
            }

            System.out.println("Please enter a valid question ID you would like to edit: ");
            int MCQid = sc.nextInt();

            // Check if the MCQ ID is in the list of questions
            boolean found = false;
            for (Object[] mcq : MCQIds) {
                if ((int) mcq[0] == MCQid) {
                    found = true;
                    break;
                }
            }

            // If the MCQ ID is not found, prompt the user with an error message
            if (!found) {
                System.out.println("Error: There is no MCQ  with ID " + MCQid);
                session.close();
                IO.IOSystem();
            }

            MCQ mcq = session.get(MCQ.class, MCQid);

            // Printing the inputted row
            System.out.println("This is the row of the MCQ ID you wanted to update: ");
//            System.out.print("[%d, %s, %s, %s, %s, %s, s%, %d]%n", mcq.getID(), mcq.getQuestion(), mcq.getCategory(), mcq.getAnswer1(), mcq.getAnswer2(), mcq.getAnswer3(), mcq.getActualAnswer(), mcq.getQuiz_id());

            int option;
            do {
                System.out.println("Choose an option on which element you would like to update from the above row displayed: \n"
                        + "1: Update Question\n"
                        + "2: Update Category\n"
                        + "3: Update Answer1\n"
                        + "4: Update Answer2\n"
                        + "5: Update Answer3\n"
                        + "6: Update Actual Answer\n"
                        + "7: Update Quiz ID\n"
                        + "0: Exit update menu");

                while (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid option (1-5 or 0 to exit)");
                    sc.next();
                }
                option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Please enter a new question:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newQuestion = sc.nextLine();
                        mcq.setQuestion(newQuestion);
                        break;

                    case 2:
                        System.out.println("Please enter a new category:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newCategory = sc.nextLine();
                        mcq.setCategory(newCategory);
                        break;

                    case 3:
                        System.out.println("Please enter a new answer1:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newAnswer1 = sc.nextLine();
                        mcq.setAnswer1(newAnswer1);
                        break;

                    case 4:
                        System.out.println("Please enter a new answer2:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newAnswer2 = sc.nextLine();
                        mcq.setAnswer2(newAnswer2);
                        break;

                    case 5:
                        System.out.println("Please enter a new answer3:");
                        sc.nextLine(); // consume the newline character left by nextInt
                        String newAnswer3 = sc.nextLine();
                        mcq.setAnswer3(newAnswer3);
                        break;

                    case 6:
                        System.out.println("Please enter a new actual answer: ");
                        sc.nextLine();
                        String newActualAnswer = sc.nextLine();
                        mcq.setActualAnswer(newActualAnswer);
                        break;

                    case 7:
                        System.out.println("Please enter a new quiz ID:");
                        int newQuizID = sc.nextInt();
                        boolean quizExists = false;

                        TypedQuery<Quiz> query1 = session.createQuery("FROM Quiz WHERE ID = :id", Quiz.class);
                        query1.setParameter("id", newQuizID);
                        List<Quiz> results = query1.getResultList();
                        if (!results.isEmpty()) {
                            quizExists = true;
                        }
                        if (quizExists) {
                            mcq.setQuiz_id(newQuizID);
                        } else {
                            System.out.println("Invalid quiz ID");
                        }
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Please enter a valid option (1-7 or 0 to exit)");
                        break;
                }

                session.update(mcq);
                session.getTransaction().commit();

                IO.IOSystem();

            } while (option != 0);

        } catch (HibernateException e) {
            //if error roll back
            session.getTransaction().rollback(); // if the session is null then roll back
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //Close session
            session.close();
        }
        Csv.save();
    }
}

