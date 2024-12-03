//import java.io.*;
//import java.lang.reflect.Array;
import java.util.*;

/**
 * CS1180 Project 4 - Demonstrate object-oriented skills through appropriate use of classes and methods by implementing a game called Catacomb Crawler.
 * 
 * @author Alec Porter
 */
public class Project4 {
    public static void main(String[] args) throws Exception {

        // additional feature: implements a character map that displays the dungeon, player location, and defeated monsters to the command line
        // additional feature: defeated monsters are identified on the map
        // additional feature: for each monster defeated the player's potential max damage inflicted on monsters increased by one (i.e. they player attack levels up)

        // initialize variables
        Scanner userInput = new Scanner(System.in);
        Hero hero = new Hero();
        Catacombs dungeon = new Catacombs();
        int[] playerLocation = new int[2];
        int numMonsters = 0;
        int index =0;
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
        while (!validInput){  // ensure player enters an integer and valid catacomb size
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

        // print instructions for the player
        System.out.printf("\nYou are represented by the %c symbol on the map.\n", '\u2656');
        System.out.printf("Navigate through the catacomb by typing \"north\", \"east\", \"south\" or \"west\" when prompted.\n");
        System.out.printf("The catacombs slowly deplete your health so the more moves you take, the weaker you become.\n");
        System.out.printf("You can smell monsters if they are in adjacent rooms.\n");
        System.out.printf("If you enter a room with monsters you will automatically fight them.  If you survive you will be able to move on.\n");
        System.out.printf("Rooms where you have defeated a monster will be marked with the %c symbol on the map.\n", '\u2620');
        System.out.printf("For every monster you defeat your potential max damage increases by one.\n");
        System.out.printf("Navigate to the X on the map to claim the treasure and exit the catacombs.\n\n");
        System.out.printf("Type any character to start: ");
        

        // build catacomb, spawn monsters, and get monster locations
        dungeon.setCatacombs(catacombSize);
        monsterLocations = dungeon.getMonsterLocations();
        numMonsters = dungeon.getNumberOfMonsters();
        catacombSize = dungeon.getCatacombSize();
        char[][] catacomb = new char[catacombSize+2][catacombSize+2];

        // start game
        userInput.nextLine();
        boolean continueGame = true;
        while(continueGame){
            clearScreen();
            // generate, draw, and move player
            catacomb = buildCatacomb(catacombSize, playerLocation, dungeon);
            drawCatacomb(catacomb, hero.getHealth(), numMonsters, hero, dungeon, playerLocation);
            playerLocation = movePlayer(playerLocation, catacombSize, userInput, hero);

            // verify player health is positive while moving through catacombs
            if (hero.getIsDefeated()){ 
                clearScreen();
                System.out.println("You did not escape the catacombs in time!");
                continueGame = false;
            }

            // check to see if there are monsters to fight at player's current location
            int battle = monstersToFight(playerLocation, dungeon);
            if (battle > 0){  // if there are monsters to fight, start battle routine
                clearScreen();
                System.out.println("There are " + battle + " monsters to fight in this room.");
                System.out.println("Prepare to fight");
                pauseGame(2000);
                for (int i = 1; i <= battle; i++){  // fight each monster in the room
                    int monsterNumber = dungeon.getMonstersDefeated()+1;
                    clearScreen();
                    System.out.println("Fighting Monster " + monsterNumber);
                    Monster monster = new Monster();
                    while (!hero.getIsDefeated() && !monster.getIsDefeated()){  // if hero and monster have health remaining, continue battle 
                        System.out.printf("%s's Health: %d  |  Monster %s's Health: %d\n", hero.getName(), hero.getHealth(), monsterNumber, monster.getHealth());
                        int heroAttack = hero.dealDamage();  // amount of damage hero will deal
                        System.out.printf("%s attacks and deals %d damage.\n", hero.getName(), heroAttack);
                        int monsterAttack = monster.dealDamage();  // amount of damage moster will deal
                        System.out.printf("Monster %s attacks and deals %d damage.\n", monsterNumber, monsterAttack);
                        monster.takeDamage(heroAttack);  // monster takes the amount of damage dealt by hero and health updated
                        hero.takeDamage(monsterAttack);  // hero takes the amount of damage dealt by monster and health updated
                    }
                    if (!hero.getIsDefeated()){  // if monster has no health remaining, print victory message
                        System.out.println("You defeated Monster " + monsterNumber + " and have " + hero.getHealth() + " health remaining.");
                        dungeon.updateMonstersDefeated();

                        // remove monster and update catacomb info
                        int count = 0;
                        for (int[] j : monsterLocations){  // find index of current location in monster location array and store index
                            if (Arrays.equals(playerLocation, j)){
                                index = count; // set monster index to count
                                //System.out.println("Index: " + index);  // DEBUG print index of monster 
                            }
                            count+=1;
                        }
                        dungeon.updateMonsterLocations(index);
                        dungeon.updateDeadMonsters(playerLocation);
                        //System.out.println("Monster Locations: " + Arrays.deepToString(monsterLocations.toArray())); // DEBUG print monster locations

                        // level up hero damage and continue game
                        hero.setLevelUp();
                        System.out.println("Press any character to continue: ");
                        userInput.nextLine();
                    }
                    else{  // if hero has no health remaining, print defeat message
                        clearScreen();
                        System.out.println("You were defeated!");
                        continueGame = false;
                    }
                }
            }
            if ( playerLocation[0] == catacombSize && playerLocation[1] == catacombSize){  // end game if player reaches lower right corner of catacombs
                catacomb = buildCatacomb(catacombSize, playerLocation, dungeon);
                drawCatacomb(catacomb, hero.getHealth(), numMonsters, hero, dungeon, playerLocation);
                continueGame = false;
            }
        }

        if (!hero.getIsDefeated()){  // if the hero has health remaining when the game ends, print vicotry message
            clearScreen();
            System.out.println("Congratulations, you escaped the catacombs and claimed the treasure!");
        }

        userInput.close();
    }



// ----- Methods ----- //

/**
 * Determines number of monsters in a room for player to fight
 * @param playerLocation player location in catacombs
 * @param dungeon object instance of Catacombs class created
 * @return number of monsters in the same room as the player
 */ 
public static int monstersToFight(int[] playerLocation, Catacombs dungeon){
    int numMonstersToFight = 0;
    ArrayList<int[]> monsterLocations = dungeon.getMonsterLocations(); 
    for (int[] i : monsterLocations){  // loop through monsterlocations array and check if player location and monster location are equal
        if (Arrays.equals(playerLocation, i)){
            numMonstersToFight+=1;
        }
    }
    return numMonstersToFight;
}

/**
 * Checks if monsters are located adjacent to the player (north, east, south, or west of current player location)
 * @param playerLocation player location in the catacombs
 * @param dungeon object instance of Catacombs class created
 * @return total number of monsters adjacent to player location
 */
public static int nearbyMosters(int[] playerLocation, Catacombs dungeon){
    int totalMonsters = 0;
    ArrayList<int[]> monsterLocations = dungeon.getMonsterLocations();

    // create locations where player can detect monsters and check against monster locations from catacombs
    int[] potentialMonsterLocationN = {playerLocation[0]-1, playerLocation[1]};  
    int[] potentialMonsterLocationS = {playerLocation[0]+1, playerLocation[1]};
    int[] potentialMonsterLocationE = {playerLocation[0], playerLocation[1]+1};
    int[] potentialMonsterLocationW = {playerLocation[0], playerLocation[1]-1};
    for (int[] i : monsterLocations){ // increment counter if a monster is located north, east, south, or west of a player 
        if(Arrays.equals(potentialMonsterLocationN, i)){
            totalMonsters+=1;
        }
        if(Arrays.equals(potentialMonsterLocationE, i)){
            totalMonsters+=1;
        }
        if(Arrays.equals(potentialMonsterLocationS, i)){
            totalMonsters+=1;
        }
        if(Arrays.equals(potentialMonsterLocationW, i)){
            totalMonsters+=1;
        }
    }
    return totalMonsters;
}

/**
 * creates catacombs using unicode characters
 * @param catacombSize size of the catacomb
 * @param playerLocation player location in the catacombs
 * @param dungeon object instance of Catacombs class created
 * @return array of unicode characters representing catacomb border, exit, player location, and dead monster locations
 */
public static char[][] buildCatacomb(int catacombSize, int[] playerLocation, Catacombs dungeon){

    // initialize catacom size and catacomb, player, and monster characters
    char[][] catacomb = new char[catacombSize+2][catacombSize+2]; 
    char border = '\u2588';
    char emptyRoom = '\u0020';
    char player = '\u2656';
    char exit = '\u2716';
    char deadMonster = '\u2620';
    ArrayList<int[]> deadMonsters = new ArrayList<>();

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

    // draw dead monsters
    deadMonsters = dungeon.getDeadMonsters();
    for (int[] location : deadMonsters){
        int monsterRow = location[0];
        int monsterCol = location[1];
        catacomb[monsterRow][monsterCol] = deadMonster;
    }

    // draw player
    int playerRow = playerLocation[0];
    int playerColumn = playerLocation[1];
    catacomb[playerRow][playerColumn] = player;

    // draw exit
    catacomb[catacombSize][catacombSize] = exit;    

    return catacomb;
    
}

/**
 * draw catacomb and player stats on screen
 * @param catacomb 2D array to store characters
 * @param heroHealth current player heath
 * @param numMonsters number of monsters adjacent to player
 * @param hero object instance of Hero class created
 * @param dungeon object instance of Catacombs class created
 * @param playerLocation player location in the catacombs
 */
public static void drawCatacomb(char[][] catacomb, int heroHealth, int numMonsters, Hero hero, Catacombs dungeon, int[] playerLocation){

    ArrayList<int[]> monsterLocations = dungeon.getMonsterLocations();

    // print catacomb to screen
    for (int i = 0; i < catacomb.length; i++){
        for (int j = 0; j < catacomb.length; j++){
            System.out.print(catacomb[i][j]);
        }
        System.out.println();
    }

    // print player stats to screen
    numMonsters = nearbyMosters(playerLocation, dungeon);
    System.out.printf("%s's Health: %s and Location: %s\n", hero.getName(), hero.getHealth(), Arrays.toString(playerLocation));
    System.out.printf("You smell %d monsters nearby.\n", numMonsters);
    //System.out.println("Moster Locations: " + Arrays.deepToString(monsterLocations.toArray())); // DEBUG display monster locations
}

/**
 * Displays available directions player can move based on current location in catacombs and moves player to selected location
 * @param playerLocation  player location in the catacombs
 * @param catacombSize  catacomb size
 * @param userInput  user input direction of 'north', 'east', 'south', or 'west'
 * @param hero object instance of Hero class created
 * @return new grid location of the player
 */
public static int[] movePlayer(int[] playerLocation, int catacombSize, Scanner userInput, Hero hero){
    // initialize variables
    int[] movePlayer = new int[2];
    int playerRow = playerLocation[0];
    int playerColumn = playerLocation[1];
    boolean validInput = false;
    String moveDirection;

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

/**
 * Moves player 1 space to the left, right, up, or down based on player input
 * @param direction  player input direction of 'north', 'east', 'south', or 'west'
 * @param currentPlayerLocation  player location in catacombs
 * @return new player locaiton in the catacomb
 */
public static int[] newPlayerLocation(String direction, int[] currentPlayerLocation){
    int[] newPlayerLocation = new int[2];
    switch (direction){
        case "north" -> {
            // move player location one row up
            newPlayerLocation[0] = currentPlayerLocation[0] - 1;
            newPlayerLocation[1] = currentPlayerLocation[1];
            }
        case "south" -> {
            // move player location one row down
            newPlayerLocation[0] = currentPlayerLocation[0] + 1;
            newPlayerLocation[1] = currentPlayerLocation[1];
            }
        case "east" -> {
            // move player location one column righ
            newPlayerLocation[0] = currentPlayerLocation[0];
            newPlayerLocation[1] = currentPlayerLocation[1] + 1;
            }
        case "west" -> {
            // move player location one column left
            newPlayerLocation[0] = currentPlayerLocation[0];
            newPlayerLocation[1] = currentPlayerLocation[1] - 1;
            }
    }
    return newPlayerLocation;
}

/**
 * Clears the screen
 */
public static void clearScreen(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

/**
 * Pause game for specificed period of time
 * @param time time to pause game in milliseconds
 */
public static void pauseGame(int time) {
    try {
        Thread.sleep(time);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}


}