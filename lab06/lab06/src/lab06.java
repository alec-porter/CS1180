import java.util.Scanner;

public class lab06 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 06\n\n");

        // initialize
        Scanner userInput = new Scanner(System.in);
        int squareSize;
        char squareBorder;

        // request user input
        System.out.print("Enter Height: ");
        squareSize = userInput.nextInt();
        System.out.print("Enter Characeter: ");
        squareBorder = userInput.next().charAt(0);
        System.out.println();

        // Print the square
        printSquare(squareSize, squareBorder);
    }


    
    // ---------- Methods ----------//

    /**
    * Return a square of size squareSize by squareSize
    * 
    * @param squareSize the size of the squre to be printed
    * @param squareBorder the character to be used as the square border
    * 
    */
    public static void printSquare(int squareSize, char squareBorder) {

        if (squareSize < 0){ // check if user value is negative
            System.out.print("Error, height cannot be negative.\n");
            System.exit(0);  // end program
        }

        for (int i=0; i < squareSize; i++){
            for (int j=0; j < squareSize; j++){
                if (i==0){  // if it is the first row *
                    System.out.print(squareBorder);
                }
                else if (j==0){     // if it is the first column print *
                    System.out.print(squareBorder);
                }
                else if (i==(squareSize-1)){     // if it is the last row print *
                    System.out.print(squareBorder);
                }
                else if (j <= (squareSize-2)){     
                    System.out.print(" ");  // if it is any value not on first/last row, first/last column, or user defined coordinate print a blank
                }
                else if (j== (squareSize-1)){  // if it is the last column print *
                    System.out.print(squareBorder);
                }
            }
            System.out.print("\n");  // retrun at end of row
        }
        System.out.print("\n");  

    }

}
