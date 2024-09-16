import java.util.Scanner;

public class lab01 {
    public static void main(String[] args) throws Exception {
        
        // request user input
        Scanner userInput = new Scanner(System.in);
        System.out.print("When were you born? (use the form 1 January 1970): ");
        String userBirthday = userInput.nextLine();
        
        // assign user input to string
        String[] birthdate = userBirthday.split(" ");
        
        // print individual components to terminal
        System.out.println("Day: " + birthdate[0]);
        System.out.println("Month: " + birthdate[1]);
        System.out.println("Year: " + birthdate[2]);
    }
}
