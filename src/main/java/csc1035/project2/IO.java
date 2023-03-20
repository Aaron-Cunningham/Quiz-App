package csc1035.project2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class IO {


    public static void main(String[] args) throws FileNotFoundException {
        Csv.load();
        new IO().IOSystem();

    }

        public void IOSystem() throws FileNotFoundException {



        Scanner sc = new Scanner(System.in);
        QuizStore quizStore = new QuizStore();
        Answer answer = new Answer();
        QuestionStore questionStore = new QuestionStore();

        int option;
        do{
            System.out.println("Choose an option: \n" +
                    "1: Add a Quiz\n" +
                    "2: Delete a Quiz\n" +
                    "3: Add a SAQ\n" +
                    "4: Delete a SAQ\n" +
                    "5: Add a MCQ\n" +
                    "6: Delete a MCQ\n" +
                    "7: Update a Quiz\n" +
                    "8: Answer a SAQ\n" +
                    "9: Answer a MCQ\n" +
                    "10: Update an SAQ\n" +
                    "11: Update an MCQ\n" );

            while (!sc.hasNextInt()){
                System.out.println("Only enter a number");
                sc.next();
            }

            option = sc.nextInt();
            switch(option){
                case 1 -> quizStore.addQuiz();
                case 2 -> quizStore.deleteQuiz();
                case 3 -> questionStore.addSAQ();
                case 4 -> questionStore.deleteSAQ();
                case 5 -> questionStore.addMCQ();
                case 6 -> questionStore.deleteMCQ();
                case 7 -> quizStore.updateQuiz();
                case 8 -> answer.answerSAQ();
                case 9 -> answer.answerMCQ();
                case 10 -> questionStore.updateSAQ();
                case 11 -> questionStore.updateMCQ();
                default -> option = 0;
            }
            IOSystem();

        } while (option !=0);
    }
}
