package csc1035.project2;
import java.io.File;
import java.util.Scanner;
public class Testing {
    public static void main(String[] args) {
        try {
            File quizzes = new File("questions.csv");
            Scanner scan = new Scanner(quizzes);
            System.out.println("done");
            System.out.println(scan);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
