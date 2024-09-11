import java.util.Scanner; 

public class lab2 {
    public static void main(String[] args) throws Exception {
        // Alec Porter Lab 2
        Scanner input = new Scanner(System.in);
        System.out.print("How tall are you? (use the form 'feet' 'inches'):  "); // asked user to input height in feed and inches
        double heightFeet = input.nextDouble();  // store height in feet as a double in heightFeet
        double heightInches = input.nextDouble();  // store height in inches as a double in heightInches

        //System.out.println(heightFeet);
        //System.out.println(heightInches);

        // calculate height in inches and print result
        double totalHeightInches = heightFeet * 12 + heightInches;
        System.out.printf("You are %.0f inches tall.\n", totalHeightInches);

        // Part B
        double avgGiraffeHeight = 192;
        // calculate relative height compared to giraffe
        System.out.printf("The average giraffe is %.1f times taller than you.\n", (avgGiraffeHeight / totalHeightInches));

    }
}
