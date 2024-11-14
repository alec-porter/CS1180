import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class project4 {
    public static void main(String[] args) throws Exception {

        // initialize variables
        Scanner userInput = new Scanner(System.in);
        Hero hero = new Hero();
        Catacombs dungeon = new Catacombs();
        int[] playerLocation = new int[2];
        int numMonsters = 0;
        int catacombSize = 0;
        ArrayList<int[]> monsterLocations = new ArrayList<>();
        playerLocation[0] = 1;
        playerLocation[1] = 1;

        // set up game
        clearScreen();
        System.out.print("What is your name, heroic adventurer? ");
        String heroName = userInput.nextLine();
        hero.setName(heroName);
        boolean validInput = false;
        while (!validInput){
            System.out.print("How large of a catacomb do you want to face? (enter a number from 5 to 10) ");
            if (userInput.hasNextInt()){
                catacombSize = userInput.nextInt();
                userInput.nextLine();
                if(catacombSize >= 5 && catacombSize <= 10){
                    validInput = true;
                }
                else{
                    System.out.println("That is not a valid catacomb size.");
                }
            }
            else{
                System.out.println("That is not a valid catacomb size.");
                userInput.next();
            }
            
        }
        System.out.printf("You are represented by the %c symbol on the map.\n", '\u2656');
        System.out.printf("Navigate through the catacomb by typing \"north\", \"east\", \"south\" or \"west\" when prompted.\n");
        System.out.printf("The catacombs slowly deplete your health so the more moves you take, the weaker you become.\n");
        System.out.printf("You can smell monsters if they are in adjacent rooms.\n");
        System.out.printf("If you enter a room with monsters you will automatically fight them.  If you survive you will be able to move on.\n");
        System.out.printf("Navigate to the X on the map to claim the treasure and exit the catacombs.\n\n");
        System.out.printf("Type any character to start.\n");
        //userInput.nextLine();
        

        // spawn monsters in catacombs
        dungeon.setCatacombs(catacombSize);
        monsterLocations = dungeon.getMonsterLocations();
        numMonsters = dungeon.getNumberOfMonsters();
        catacombSize = dungeon.getCatacombSize();
        char[][] catacomb = new char[catacombSize+2][catacombSize+2];


        

        // DEBUG
        // Monster monster = new Monster(monsterLocations.get(0), 5);
        // System.out.println(monster.getName());
        // System.out.println(Arrays.toString(monster.getLocation()));

        //int[] monster1Location = monsterLocations.get(0);
        //int monster1LocationRow = monster1Location[0];
        //System.out.println(monster1LocationRow);
        //System.out.println(Arrays.deepToString(monsterLocations.toArray()));
        //System.out.println(Arrays.toString(monsterLocations.get(3)));
        //System.out.println(Arrays.deepToString(catacomb));

        userInput.nextLine();
        boolean continueGame = true;
        while(continueGame){
            clearScreen();
            catacomb = buildCatacomb(catacombSize, playerLocation);
            drawCatacomb(catacomb, hero.getHealth(), numMonsters, hero);
            playerLocation = movePlayer(playerLocation, catacombSize, userInput, hero);
            if ( playerLocation[0] == catacombSize && playerLocation[1] == catacombSize){
                clearScreen();
                catacomb = buildCatacomb(catacombSize, playerLocation);
                drawCatacomb(catacomb, hero.getHealth(), numMonsters, hero);
                continueGame = false;
            }
            System.out.println("Congratulations, You Escaped the Catacomb!");

        }

        userInput.close();
    }


// ----- Methods ----- //

public static char[][] buildCatacomb(int catacombSize, int[] playerLocation){

    char[][] catacomb = new char[catacombSize+2][catacombSize+2];  // set catacomb size based on user input

    char border = '\u2588';
    char emptyRoom = '\u0020';
    char player = '\u2656';
    char exit = '\u2716';

    // draw catacomb border
    for (int i = 0; i < catacombSize+2; i++){
        for (int j = 0; j < catacombSize+2; j++){
            if (i==0){
                catacomb[i][j] = border;
            }
            else if (i==catacombSize+1){
                catacomb[i][j] = border;
            }
            else if (j==0){
                catacomb[i][0] = border;
            }
            else if (j==catacombSize+1){
                catacomb[i][catacombSize+1] = border;
            }
        }
    }

    // draw empty catacomb interier
    for (int i = 1; i < catacomb.length-1; i++){
        for (int j = 1; j < catacomb.length-1; j++){
            catacomb[i][j] = emptyRoom;
        }
    }

    // draw player
    int playerRow = playerLocation[0];
    int playerColumn = playerLocation[1];
    catacomb[playerRow][playerColumn] = player;

    // draw exit
    catacomb[catacombSize][catacombSize] = exit;    

    return catacomb;
}


public static void drawCatacomb(char[][] catacomb, int heroHealth, int numMonsters, Hero hero){

    // print catacomb to screen
    for (int i = 0; i < catacomb.length; i++){
        for (int j = 0; j < catacomb.length; j++){
            System.out.print(catacomb[i][j]);
        }
        System.out.println();
    }
    System.out.printf("%s's Health: %s\n", hero.getName(), hero.getHealth());
    System.out.printf("You smell %d monsters nearby.\n", numMonsters);
}


public static int[] movePlayer(int[] playerLocation, int catacombSize, Scanner userInput, Hero hero){
    // initialize variables
    int[] movePlayer = new int[2];
    int playerRow = playerLocation[0];
    int playerColumn = playerLocation[1];
    boolean validInput = false;
    String moveDirection = "";

    if (playerRow == 1 && playerColumn == 1){  // movement options for player located in top left corner
        while (!validInput){
            System.out.print("Move East or South: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("east") || moveDirection.equals("south")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }
    }
    else if (playerRow == 1 && playerColumn == catacombSize){  // movement options for player located in top right corner
        while (!validInput){  
            System.out.print("Move West or South: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("west") || moveDirection.equals("south")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }
    }
    else if (playerRow == 1 && (playerColumn > 1 && playerColumn < catacombSize)){  // movement options for player located next to top boundary
        while (!validInput){
            System.out.print("Move East, South, or West: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("east") || moveDirection.equals("south") || moveDirection.equals("west")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }
    }
    else if (playerRow == catacombSize && (playerColumn > 1 && playerColumn < catacombSize)){  // movement options for player located next to bottom boundary
        while (!validInput){
            System.out.print("Move North, East or West: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("east") || moveDirection.equals("north") || moveDirection.equals("west")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }
    }
    else if (playerRow == catacombSize && playerColumn == 1){  // movement options for player located in bottom left corner
        while (!validInput){
            System.out.print("Move North or East: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("east") || moveDirection.equals("north")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }
    }
    else if ((playerRow > 1 && playerRow < catacombSize) && playerColumn == 1){  // movement options for player located next to left boundary
        while (!validInput){
            System.out.print("Move North, East or South: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("east") || moveDirection.equals("south") || moveDirection.equals("north")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }
    }
    else if ((playerRow > 1 && playerRow < catacombSize) && playerColumn == catacombSize){  // movement options for player located next to right boundary
            while (!validInput){
            System.out.print("Move North, South or West: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("west") || moveDirection.equals("south") || moveDirection.equals("north")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }
    }
    //else if (playerRow == catacombSize && playerRow == catacombSize){  // player located at the exit
    //    System.out.println("Exit");
    //}
    else {  // player located in the middle of the catacombs
        while (!validInput){
            System.out.print("Move North, East, South or West: ");
            moveDirection = userInput.nextLine().toLowerCase();
            if (moveDirection.equals("east") || moveDirection.equals("south") || moveDirection.equals("north") || moveDirection.equals("west")){
                movePlayer = newPlayerLocation(moveDirection, playerLocation);
                hero.movementDamage();
                validInput = true;
            }
            else{
                System.out.println("That was not a volid direction.");
            }
        }

    }
    return movePlayer;
}


public static int[] newPlayerLocation(String direction, int[] currentPlayerLocation){
    int[] newPlayerLocation = new int[2];
    switch (direction){
        case "north":  // move player location one row up
            newPlayerLocation[0] = currentPlayerLocation[0] - 1;
            newPlayerLocation[1] = currentPlayerLocation[1];
            break;
        case "south":  // move player location one row down
            newPlayerLocation[0] = currentPlayerLocation[0] + 1;
            newPlayerLocation[1] = currentPlayerLocation[1];
            break;
        case "east":  // move player location one column righ
            newPlayerLocation[0] = currentPlayerLocation[0];
            newPlayerLocation[1] = currentPlayerLocation[1] + 1;
            break;
        case "west":  // move player location one column left
            newPlayerLocation[0] = currentPlayerLocation[0];
            newPlayerLocation[1] = currentPlayerLocation[1] - 1;
            break;
    }
    return newPlayerLocation;
}

public static void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

}