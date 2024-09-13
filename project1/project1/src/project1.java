
import java.util.Scanner;

public class project1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter CS1180 Project 1");

        Scanner userInput = new Scanner(System.in);

        double countRightAns = 0;   // initialize correct answer counter
        double totalQuestions = 5;  // initialize total number of questions asked

        System.out.print("What is the speed of light in a vacuum in m/s (enter the value as a whole number): "); // Answer: 299792458
        int answer1 = userInput.nextInt();  // store user input as an integer
        userInput.nextLine();   // remove return character
        if (answer1 == 299792458){  // check answer against integer value
            System.out.println("That is Correct!");  // print message if answer is correct
            countRightAns+=1; // increment correct answer counter
        }
        else{
            System.out.println("That is Incorrect."); // print message if answer is wrong
        }

        System.out.print("What is the closest star to Earth other than the Sun: "); // Proxima Centauri
        String answer2 = userInput.nextLine().toLowerCase(); // store user input as a lowercase string
        if (answer2.equals("proxima centauri")){  // check answer against string value
            System.out.println("That is Correct!");  // print message if answer is correct
            countRightAns+=1; // increment correct answer counter
        }
        else{
            System.out.println("That is Incorrect."); // print message if answer is wrong
        }
                
        System.out.print("The James Webb Space Telecope was launched in December 2021 (enter true or false): "); // Answer: December 2021
        boolean answer3 = userInput.nextBoolean(); // store user input as a boolean
        if (answer3){ // check answer against boolen value
            System.out.println("That is Correct!");  // print message if answer is correct
            countRightAns+=1; // increment correct answer counter
        }
        else{
            System.out.println("That is Incorrect."); // print message if answer is wrong
        }

        System.out.print("How Large is the Hubble Space Telescope's mirror in meters (enter the value to one decimal place): "); // Answer: 2.4
        double answer4 = userInput.nextDouble(); // store user input as a double
        if (answer4 == 2.4){  // check answer against double value
            System.out.println("That is Correct!");  // print message if answer is correct
            countRightAns+=1; // increment correct answer counter
        }
        else{
            System.out.println("That is Incorrect."); // print message if answer is wrong
        }

        System.out.print("The speed of light is often characterized as what lowercase character: "); // Answer: c
        char answer5 = userInput.next().charAt(0);
        if (answer5 == 'c'){   // check answer against character value
            System.out.println("That is Correct!");  // print message if answer is correct
            countRightAns+=1; // increment correct answer counter            
        }
        else{
            System.out.println("That is Incorrect."); // print message if answer is wrong
        }
       
        // calculate and print number of correct answers
        System.out.printf("You got %.0f out of 5 right, for a score of %.0f%%\n", countRightAns, ((countRightAns / totalQuestions) * 100));

    }
}
