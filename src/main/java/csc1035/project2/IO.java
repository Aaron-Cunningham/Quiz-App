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
                    "2: Add a SAQ\n" +
                    "3: Delete a SAQ\n" +
                    "4: Add a MCQ\n" +
                    "5: Delete a MCQ\n");

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
                    QS.addSAQ();
                }
                case 3 -> {
                    QS.deleteSAQ();
                }
                case 4 -> {
                    QS.addMCQ();
                }
                case 5 -> QS.deleteMCQ();
                default -> option = 0;
            }
            IOSystem();

        } while (option !=0);
    }
}
