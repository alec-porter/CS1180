import java.util.Scanner;


public class lab04 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter CS1180 Lab 4");

        Scanner userInput = new Scanner(System.in);

        // initialize variables
        double total = 0;
        double inputValue;
        boolean continueLoop = true;
        int count = 0;

        
/*         // Part A
        while (continueLoop){
            System.out.print("Enter a number: ");   // ask for user input
            inputValue = userInput.nextDouble();    // store input as double
            total += inputValue;                    // increment total 
            if (total > 100){                       // exit loop when total is greater than 100
                continueLoop = false;
            } */

        // Part B
        while (continueLoop){
            System.out.print("Enter a number: ");   // ask for user input
            inputValue = userInput.nextDouble();    // store input as double
            total += inputValue;                    // increment total 
            count += 1;                             // increment count
            if (total >= 100){                       // exit loop when total is greater than or equal to 100
                continueLoop = false;
            }
        }

        System.out.printf("You entered %d values before the total was greater than or equal to 100.\n", (count-1));  // output count from user input prior to final input
        System.out.printf("You exceeded 100 by %.0f.\n", (total-100));

    }
}
