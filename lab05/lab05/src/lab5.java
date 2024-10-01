import java.util.Scanner;

public class lab5 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 5");

        Scanner userInput = new Scanner(System.in);
        int value, xValue, yValue;  // initialize user input

        System.out.print("What is the value of n: ");
        value = userInput.nextInt();  // ask user for number

        if (value < 0){ // check if user value is negative
            System.out.print("Error, n cannot be negative.\n");
            System.exit(0);  // end program
        }

        System.out.print("Where do you want to put the X? (enter in format X Y):");  // read in coordinates
        xValue = userInput.nextInt();  
        yValue = userInput.nextInt();

        if (xValue < 1 || yValue < 1 || xValue > (value - 2) || yValue > (value - 2)){  // check if coordinate is inside square
            System.out.print("Error! That coorinate is not inside the square.\n");
            System.exit(0);  // end program
        }

        for (int i=0; i < value; i++){
            for (int j=0; j < value; j++){
                if (i==0){  // if it is the first row *
                    System.out.print("*");
                }
                else if (j==0){     // if it is the first column print *
                    System.out.print("*");
                }
                else if (i==(value-1)){     // if it is the last row print *
                    System.out.print("*");
                }
                else if (j <= (value-2)){     
                    
                    //if (i==(value-yValue) && j==(xValue-1)){  //translate to standard coordinate system
                    //    System.out.print("X");  // place X on user coordinate
                    //}

                    if (i==yValue && j==xValue){
                        System.out.print("X");  // place an X at the user defined coordinate
                    }

                    else{
                        System.out.print(" ");  // if it is any value not on first/last row, first/last column, or user defined coordinate print a blank
                    }
                }
                else if (j== (value-1)){  // if it is the last column print *
                    System.out.print("*");
                }
            }
            System.out.print("\n");  // retrun at end of row
        }
        System.out.print("\n");  




    }
}