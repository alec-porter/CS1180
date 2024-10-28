import java.util.*;
import java.io.*;

public class lab08 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 8\n\n");

        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter a filename: ");  // ask user for filename
        String fileName = userInput.nextLine();

        System.out.print("Enter a string: ");  // string to write to file
        String fileString = userInput.nextLine();

        File thisRandomFile = writeText(fileName, fileString); // method call
        int numberOfWords = countWords(thisRandomFile);  // method call
        System.out.println("The file " + fileName + " contains " + numberOfWords + " words.");
    }


// ---------- METHODS --------- //

/**
 * Counts the number of words in a file
 * @param readFile name of the file object
 * @return the number of words in the file object
 * @throws Exception
 */
public static int countWords(File readFile) throws Exception{

    Scanner inputFile = new Scanner(readFile);  // read from file
    int count = 0;
    while(inputFile.hasNext()){  // loop while there is a next word
        count++; // increment counter
        inputFile.next();  // read in next word
    }
    inputFile.close();
    return count;    
}


/**
 * creats a file, writes provided string to file, rturns File object
 * @param filename name of the file from user
 * @param userInput string from the user
 * @return file object
 * @throws Exception
 */
public static File writeText(String filename, String userInput) throws Exception{

    File writeFile = new File(filename);  // create file
    FileWriter fwriter = new FileWriter(filename, false); // overwrite file if it exists
    PrintWriter outputFile = new PrintWriter(fwriter);  
    outputFile.println(userInput);  // write user input to file
    outputFile.close(); 

    return writeFile;
}


}


