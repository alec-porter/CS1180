import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Scanner;

public class lab07 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 7\n\n");

        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter how many days of data: ");
        int numDays = userInput.nextInt();
        double[] morningTempArray = new double[numDays]; // array for morning temp
        double[] afternoonTempArray = new double[numDays]; // array for afternoon temp
        double[] eveningTempArray = new double[numDays]; // array for evening temp
        
        // loop temperature inputs based on number of days input and store in array
        for (int i=0; i<numDays; i++){
            int day = i+1;
            System.out.print("\nEnter the morning temperature for day number " + day +": ");
            double morningTemp = userInput.nextDouble();
            morningTempArray[i]=morningTemp;
            System.out.print("Enter the afternoon temperature for day number " + day +": ");
            double afternoonTemp = userInput.nextDouble();
            afternoonTempArray[i] = afternoonTemp;
            System.out.print("Enter the evening temperature for day number " + day +": ");
            double eveningTemp = userInput.nextDouble();
            eveningTempArray[i] = eveningTemp;
        }

        double avgMorningTemp = findAverage(morningTempArray);  // function to calculate averge temperature
        System.out.println("\nThe average morning temperature is " + avgMorningTemp + " degrees.");

        double avgAfternoonTemp = findAverage(afternoonTempArray);  // function to calculate averge temperature
        System.out.println("The average afternoon temperature is " + avgAfternoonTemp + " degrees.");

        double avgEveningTemp = findAverage(eveningTempArray);  // function to calculate averge temperature
        System.out.println("The average evening temperature is " + avgEveningTemp + " degrees.");
        
        // loop through each day to calculate average day's temperature
        for (int i=0; i<numDays; i++) {
            int day = i+1;
            double avgDayTemp = findDayAverage(morningTempArray, afternoonTempArray, eveningTempArray, i); // function to calculate a day's avgerage temperature
            System.out.println("The average temperature for day " + day + " is " + avgDayTemp + " degrees.");
        }

        System.out.println();

    }



/*----------Methods----------*/

/**
* Averages the numbers in an array
* @param inputArray an array of temperatures provided by the user 
* @return the average temperature
*/
public static double findAverage(double[] inputArray){
    double avgTemp;
    //avgTemp = Arrays.stream(inputArray).sum()/inputArray.length;  // calculates avg temp using sum and array length
    avgTemp = Arrays.stream(inputArray).average().getAsDouble();  // calculates avg temp using average and getting the value from the OptionalDouble
    return avgTemp;
}

/**
 * Averages the numbers across three arrays at a specified index
 * @param inputArray1 first array to use
 * @param inputArray2 second array to use
 * @param inputArray3 third array to use
 * @param index index of the arrays to use for the calculation
 * @return the average temperature at the specified index of the three provided arrays
 */
public static double findDayAverage (double[] inputArray1, double[] inputArray2, double[] inputArray3, int index){
    double avgTemp;
    avgTemp = (inputArray1[index] + inputArray2[index] + inputArray3[index])/3.0; // calculates the average at the specified index
    return avgTemp;
}

}
