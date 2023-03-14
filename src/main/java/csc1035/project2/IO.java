package csc1035.project2;

import java.util.Scanner;

public class IO {


    public static void main(String[] args) {
        new IO().IOSystem();

    }

        public void IOSystem(){
        Scanner sc = new Scanner(System.in);
        QuizStore qs = new QuizStore();
        QuestionStore QS = new QuestionStore();

        int option;
        do{
            System.out.println("Choose an option: \n" +
                    "1: Add a Quiz\n" +
                    "2: Delete a Quiz\n" +
                    "3: Add a SAQ\n" +
                    "4: Delete a SAQ\n" +
                    "5: Add a MCQ\n" +
                    "6: Delete a MCQ\n" +
                    "7: Update a Quiz\n");

            while (!sc.hasNextInt()){
                System.out.println("Only enter a number");
                sc.next();
            }

            option = sc.nextInt();
            switch(option){
                case 1 -> {
                    qs.addQuiz();
                }
                case 2 -> {
                    qs.deleteQuiz();
                }
                case 3 -> {
                    QS.addSAQ();
                }
                case 4 -> {
                    QS.deleteSAQ();
                }
                case 5 -> {
                    QS.addMCQ();
                }
                case 6 -> QS.deleteMCQ();
                default -> option = 0;

                case 7 -> qs.updateQuiz();
            }
            IOSystem();

        } while (option !=0);
    }
}
