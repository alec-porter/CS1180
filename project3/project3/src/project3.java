import java.io.*;
import java.util.*;


public class project3 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter CS1180 Project 3\n\n");

        ArrayList<String> gameWords = sevenUniqueLetterWord();  // use method to create unique 7 letter word arraylist for the game
        int totalWords = gameWords.size(); // determine how many words are in the game word list
        Scanner userInput = new Scanner(System.in);
        
        // select a random word from the word list
        Random rand = new Random();
        int randWord = rand.nextInt(totalWords-1);
        String wordForGame = gameWords.get(randWord);

        // initialiaze variables
        boolean continueGame = true;
        String gameInput;
        boolean validUserInput = false;
        boolean validUserWord = false;
        int score = 0;
        ArrayList<String> validUserWordList = new ArrayList<>();

        System.out.println("Spelling Bee: ");
        displayWord(wordForGame);

        while (continueGame){  // loop until user enters "bye"

            gameInput = userInput.nextLine().toLowerCase();  // user input
            
            switch(gameInput){
                case "bye": // if user enters "bye", exit game
                    System.out.println("Thank you for playing!");
                    continueGame = false;
                    break;

                case "mix": // if user enters "mix", mix the letters of the word
                    displayWord(wordForGame);
                    break;

                case "ls":  // lists the user's world list and scoer
                    System.out.println("Your words: ");
                    for (int i = 0; i < validUserWordList.size(); i++){
                        System.out.println("   " + validUserWordList.get(i));
                    }
                    System.out.println("Your current score is " + score + " points.");
                    break;

                default: // check user input and score based on valid word and length
                    validUserInput = validStringInput(gameInput, wordForGame); // check if the user input is valid
                    if (validUserInput){
                        validUserWord = validWord(gameInput); // check if the user input is a word in the word list
                    }
                    else{ // if user inputs letters not in the game word output message to user
                        System.out.println("You entered letters not in the scambled word.");
                    }
                    if (validUserInput && validUserWord){ // checks if the user input is valid and it is in the word list
                        if (!validUserWordList.contains(gameInput)){ // if the user input isn't already in the user's word list, add it and increase score
                            validUserWordList.add(gameInput);
                            
                            if (gameInput.length() == 4){
                                score+=1;
                            }
                            if (gameInput.length() == 5){
                                score+=5;
                            }
                            if (gameInput.length() == 6){
                                score+=6;
                            }
                            if (gameInput.length() == 7){
                                score+=7;
                            }
                            System.out.println("Your score is now " + score + " points.");
                        }
                        else{ // if the user's word list alerady contains the word output message to user
                            System.out.println("You already used that word.");
                        }
                    }
                    if (validUserInput && !validUserWord){  // if the word isn't worth any point output message to user
                        System.out.println("Good Try but that input is not worth any points.");
                    }
                    
            }
        }
    }





// ----- METHODS ----- //

/**
 * Checks if all of the characters in user input string are in the game word
 * @param userInput user string to check
 * @param gameInput game word to check against
 * @return true or false
 */
public static boolean validStringInput(String userInput, String gameInput){

    char[] userChar = userInput.toCharArray(); // create character array from user string
    String gameWord = gameInput;
    boolean value = true;

    for (char character : userChar){ // loop through characters in array
        if (!gameWord.contains(Character.toString(character))){  // convert character to sting and see if string is not in gameword
            value = false;  // if a character in the array is not in the game word change boolean to false
        }
    }
    return value;
}

/**
 * Checks if input string is in words.txt
 * @param input string
 * @return true if word is in words.txt or false if word is not in words.txt
 * @throws Exception read from file
 */
public static boolean validWord(String input) throws Exception{

    File fileHandle = new File("words.txt");  // open file
    Scanner inputFile = new Scanner(fileHandle);  
    boolean value = false;
    while (inputFile.hasNextLine()){ // loop through word.txt
        String dictionaryWord = inputFile.nextLine();
        if (dictionaryWord.equals(input)){ // check if input string matches word in word.txt
            value = true;
        }
    }
    return value;
}

/**
 * Prints the characters of the input string in a random order with five spaces between characters
 * @param input string
 */
public static void displayWord(String input){

    ArrayList<Character> mixedChar = new ArrayList<>(); // create character arraylist
    char[] stringChars = input.toCharArray(); // convert stirng input to characters
    for (char character : stringChars){
        mixedChar.add(character);  // add string characters to arraylist
    }
    Collections.shuffle(mixedChar); // shuffle arraylist
    System.out.println(mixedChar.get(0) + "     " + mixedChar.get(1) + "     " + mixedChar.get(2) + "     " + mixedChar.get(3) + 
    "     " + mixedChar.get(4) + "     " + mixedChar.get(5) + "     " + mixedChar.get(6));
}

/**
 * Return an ArrayList of words with 7 unique characters from word.txt
 * @return ArrayList of words
 * @throws Exception read from file
 */
public static ArrayList sevenUniqueLetterWord() throws Exception{

    File fileHandle = new File("words.txt");  // open file
    Scanner inputFile = new Scanner(fileHandle);  

    ArrayList<String> gameWords = new ArrayList<>();  // create empty array for game words

    while(inputFile.hasNextLine()){
        String potentialWord = "";
        potentialWord = inputFile.nextLine(); // read in word from dictionary
        
        if (potentialWord.length()==7){

            // sort 7 letter stings to identify letter repeats
            char[] stringChars = potentialWord.toCharArray(); // convert to char array to sort characters
            Arrays.sort(stringChars);  // sort characters alphabetically

            boolean repeatChar = false;
            for (int i = 0; i < stringChars.length - 1; i++){
                if(stringChars[i] == stringChars[i+1]){ // check for non repeaing characters
                    repeatChar = true;
                }
            }

            if (repeatChar == false){  // if no characters repeat then add word to word list
                potentialWord = new String(stringChars);
                gameWords.add(potentialWord);
            }
        }
    }
    inputFile.close();
    return gameWords;
}

}
