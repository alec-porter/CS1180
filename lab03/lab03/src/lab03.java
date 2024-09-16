import java.util.Scanner; 

public class lab03 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter CS1180 Lab03");

        Scanner userInput = new Scanner(System.in);

        int numDaysLate;    // initialize variabe for number of days late
        double lateFee;     // initialize variable for late fee
        String vipMember;   // initialize varibale for vip member

        // request the number of days late from user and if they are a vip member
        System.out.printf("Enter Number of Days Late: ");
        numDaysLate = userInput.nextInt();
        userInput.nextLine();   // remove return character
        System.out.printf("Are you a library VIP (yes or no): ");
        vipMember = userInput.nextLine().toLowerCase();   // store user input in lowercase


        if (numDaysLate <= 5){
            lateFee = 1;
        }
        else if ((numDaysLate >= 6) && (numDaysLate <= 10)){
            lateFee = 5;
        }
        else{
            lateFee = 10;
        }

        if (vipMember.equals("yes")){
            System.out.printf("Late fine is $%.2f for %d days late.\n", (lateFee * 0.5), numDaysLate );
        }
        else{
            System.out.printf("Late fine is $%.2f for %d days late.\n", lateFee, numDaysLate );
        }
        





    }
}
