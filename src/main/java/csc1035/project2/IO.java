package csc1035.project2;

import java.util.Scanner;

public class IO {


    public static void main(String[] args) {
        new IO().IOSystem();

    }

        public void IOSystem(){
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
                    "9: Update an SAQ\n");

            while (!sc.hasNextInt()){
                System.out.println("Only enter a number");
                sc.next();
            }

            option = sc.nextInt();
            switch(option){
                case 1 -> {
                    quizStore.addQuiz();
                }
                case 2 -> {
                    quizStore.deleteQuiz();
                }
                case 3 -> {
                    questionStore.addSAQ();
                }
                case 4 -> {
                    questionStore.deleteSAQ();
                }
                case 5 -> {
                    questionStore.addMCQ();
                }
                case 6 -> questionStore.deleteMCQ();



                case 7 -> quizStore.updateQuiz();

                case 8 -> answer.answerSAQ();
                case 9 -> questionStore.updateSAQ();
                default -> option = 0;
            }
            IOSystem();

        } while (option !=0);
    }
}
