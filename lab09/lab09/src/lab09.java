public class lab09 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 9\n\n");

        char[][] treasureMap = {  // define treasure map
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '+', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'} };

        getCoordinates(treasureMap);
        digTreasure(treasureMap);
        getCoordinates(treasureMap);

    }



// ---------- Methods ---------- //

/**
 * Prints a message that the treasure is being dug up and removes treasure coordinate from map
 * @param map map to find treasure coordinate
 */
public static void digTreasure(char[][] map){
    for (int i = 0; i < map.length; i++){ // loop through rows
        for (int j = 0; j < map[i].length; j++){  // loop through columns
            if (map[i][j] == '+'){  // find and print coordinates at char '+'
                System.out.println("Digging Treasure at Coordinate: Row " + i + ", Column " + j);
                map[i][j] = '-'; // replace '+' with '-' to indicate treasure found
            }
        }
    }
}

/**
 * Prints the coordinates of the character '+' in the provided map
 * @param map map to find treasure coordinate
 */
public static void getCoordinates(char[][] map){
    boolean foundTreasure = false;
    for (int i = 0; i < map.length; i++){ // loop through rows
        for (int j = 0; j < map[i].length; j++){  // loop through columns
            if (map[i][j] == '+'){  // find and print coordinates at char '+'
                System.out.println("Treasure at Coordinate: Row " + i + ", Column " + j);
                foundTreasure = true; // if treasure found change boolean value
            }
        }
    }
    if (foundTreasure == false){  // print message if no treasure found in array
        System.out.println("No Treasure Found!");
    }
}


}
