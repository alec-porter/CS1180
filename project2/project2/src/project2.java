import java.util.Random;
import java.util.Scanner;

public class project2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Project 2\n\n");

        System.out.println("Let's Play Simon Says!");

        Scanner userInput = new Scanner(System.in);

        boolean tryAgain = true;
        String anotherGame;
        String gameType = "";

        while (tryAgain){

            // start menu to select game type
            boolean isValid = false; // initialize menu error check loop
            while (!isValid) {
                System.out.print("Select Difficulty (easy or hard): ");
                gameType = userInput.nextLine().toLowerCase();
                if (gameType.equals("easy")) { // check if "easy" was ipnut and set game to easy mode
                    System.out.println("Easy Mode - Colors");
                    isValid = true;
                } 
                else if (gameType.equals("hard")) { // check if "hard" was input and set game to hard mode
                    System.out.println("Hard Mode - Numbers");
                    isValid = true;
                } 
                else { // error for invalid input and restart check
                    System.out.println("Invalid Difficulty");
                    isValid = false;
                }
            }
            // end menu to select game type


            // prep display for game
            pauseGame();
            clearScreen();
            

            // start game code
            boolean continueGame = true;
            int count = 0;
            while(continueGame){
                String gameValue;
                String userValue;
                if (gameType.equals("easy")) { // easy game mode
                    gameValue = randomColors(count+1); // generate random color string based on count
                    System.out.println("Simon Says: " + gameValue); // print colors to screen
                    gameValue = gameValue.toLowerCase().replaceAll("\\s", ""); // make lowercase remove white space from string
                    pauseGame();    
                    clearScreen();  
                    System.out.print("Player Repeats: ");
                    userValue = userInput.nextLine().toLowerCase().replaceAll("\\s",""); // make lowercase and remove white space
                    if (userValue.equals(gameValue)){ // check if player input correct colors and increment score
                        count+=1;
                        System.out.printf("Congrats! Current Player Score: %d\n", count);
                        continueGame=true;
                    }
                    else{   // display final score and end game
                        System.out.printf("Sorry! Final Player Score: %d\n", count);
                        continueGame=false;
                    }
                }
                else if (gameType.equals("hard")) { // hard game mode
                    gameValue = randomNumbers(count+1); // generate random number string based on count
                    System.out.println("Simon Says: " + gameValue); // print numbers to screen
                    gameValue = gameValue.toLowerCase().replaceAll("\\s", ""); // make lowercase remove white space from string
                    pauseGame();    
                    clearScreen();  
                    System.out.print("Player Repeats: ");
                    userValue = userInput.nextLine().toLowerCase().replaceAll("\\s",""); // make lowercase and remove white space
                    if (userValue.equals(gameValue)){ // check if player input correct numbers and increment score
                        count+=1;
                        System.out.printf("Congrats! Current Player Score: %d\n", count);
                        continueGame=true;
                    }
                    else{   // display final score and end game
                        System.out.printf("Sorry! Final Player Score: %d\n", count);
                        continueGame=false;
                    }
                }
            }
            // end game code


            // start replay game code
            isValid = false;  // initialize replay game error check
            while (!isValid){
                System.out.print("Would you like to play another round? (yes or no) ");
                anotherGame = userInput.nextLine().toLowerCase();
                if (anotherGame.equals("yes")){  // if user inputs "yes" end check and start new game
                    isValid = true;
                    tryAgain = true;
                }
                else if (anotherGame.equals("no")){     // if user inputs "no" end check and end game
                    System.out.println("Thank you!");
                    isValid = true;
                    tryAgain = false;
                }
                else{   // error for invalid input and restart check
                    System.out.println("You did not enter a valid input.");
                    isValid = false;
                }
            }
            // end replay game code


        }
        userInput.close();
    }


    /* ---------- Methods ---------- */

    public static String randomNumbers(int numberCount) {
        Random rand = new Random();
        /**
         * Return a random string of numbers
         * 
         * @param numberCount the number of numbers in the range 0-9 to return
         * @return a string of random numbers
         */
        String randomNumbers = "";
        int randValue;
        for (int i=0; i<numberCount; i++){
            randValue = rand.nextInt(9);
            randomNumbers += (Integer.toString(randValue) + " ");
        }
        return randomNumbers;
    }

    public static String randomColors(int colorCount) {
        /**
         * Return a random string of colors
         * 
         * @param colorCount the number of colors to return
         * @return a string of random colors
         */
        Random rand = new Random();
        String randomColors = "";
        int randValue;  // initialize random number
        for (int i=0; i<colorCount; i++) {  // loop through inout number of iterations
            randValue = rand.nextInt(4);
            switch (randValue) {    // assign color based on random number
                case 0:
                    randomColors += "Red ";  // concatenate Red to the string
                    break;
                case 1:
                    randomColors += "Yellow ";  // concatenate Yellow to the string
                    break;
                case 2:
                    randomColors += "Green ";   // concatenate Green to the string
                    break;
                case 3:
                    randomColors += "Blue ";    // concatenate Blue to the string
                    break;
            }
        }
        return randomColors;
    }

    public static void clearScreen() {
        /**
         * Clears the screen
         */
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pauseGame() {
        /**
         * Pause program for 3 seconds
         */
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
