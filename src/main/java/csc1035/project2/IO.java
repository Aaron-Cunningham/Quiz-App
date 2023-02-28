package csc1035.project2;

import java.util.Scanner;

public class IO {
    public static void main(String[] args) {
        new IO().IOSystem();
    }


        public void IOSystem(){
        Scanner sc = new Scanner(System.in);

        int option;
        do{
            System.out.println("Choose an option: \n" +
                    "1: \n" +
                    "2: \n" +
                    "3: \n" +
                    "4: \n" +
                    "5: ");

            while (!sc.hasNextInt()){
                System.out.println("Only enter a number");
                sc.next();
            }

            option = sc.nextInt();
            switch(option){
                case 1 -> {
                    System.out.println(" ");
                }
                case 2 -> {
                    System.out.println("  ");
                }
                case 3 -> {
                    System.out.println("   ");
                }
                case 4 -> {
                    System.out.println("    ");
                }
                case 5 -> {
                    System.out.println("     ");
                }
                default -> option = 0;
            }
            IOSystem();

        } while (option !=0);
    }
}
