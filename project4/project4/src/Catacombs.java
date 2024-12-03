import java.util.ArrayList;
import java.util.Random;

/**
 * Class Catacombs
 * 
 * Sets the size of the catacombs, spawns monsters, sets monster location in the catacombs, and tracks number and location of defeated monsters.
 * 
 * @author Alec Porter
 */
public class Catacombs {
    private int catacombSize;
    private int numberOfMonsters;
    private final ArrayList<int[]> monsterLocations = new ArrayList<>();
    private final ArrayList<int[]> deadMonsters = new ArrayList<>();
    private int monstersDefeated;

    // set default catacomb size of 5x5 with 4 monsters
    public Catacombs(){
        catacombSize = 5;
        numberOfMonsters = 4;
        monstersDefeated = 0;
        
        Random rand = new Random();
        for (int i = 0; i < numberOfMonsters; i++){
            int randRow = rand.nextInt(catacombSize)+1;
            int randCol = rand.nextInt(catacombSize)+1;
            if (randRow == 1 && randCol == 1){ // if monster spawns at start coordinate, move it to empty coordinate
                randRow+=1;
                randCol+=1;
            }
            if (randRow == 5 && randCol == 5){  //if monster spawns at exit coordinate, move it to empty coordinate
                randRow-=1;
                randCol-=1;
            }
            int [] monsterLocation = {randRow, randCol};
            monsterLocations.add(monsterLocation);
        }
    }

    /**
     * Sets the size of the catacombs, determines the number of monsters in catacombs, and spawns monsters at random locations
     * @param inputSize size of catacomb
     */
    public void setCatacombs(int inputSize){
        if (inputSize < 5 || inputSize > 10){ // if input is outside catacomb size constraint, set size to 5x5
            catacombSize = 5;
        }
        else{
            monsterLocations.clear();
            catacombSize = inputSize;
            numberOfMonsters = inputSize*inputSize/6;  // number of monsters based on catacomb size
            Random rand = new Random();
            for (int i = 0; i < numberOfMonsters; i++){  // spawn monsters in catacombs
                int randRow = rand.nextInt(inputSize)+1;
                int randCol = rand.nextInt(inputSize)+1;
                if (randRow == 1 && randCol == 1){ // if monster spawns at start coordinate, move it to empty coordinate
                    randRow+=1;
                    randCol+=1;
                }
                if (randRow == inputSize && randCol == inputSize){  //if monster spawns at exit coordinate, move it to empty coordinate
                    randRow-=1;
                    randCol-=1;
                }
                int [] monsterLocation = {randRow, randCol};
                monsterLocations.add(monsterLocation);
            }
        }
    }

    // Return monster locations
    public ArrayList getMonsterLocations(){
        return monsterLocations;
    }
    
    // Return number of monsters in catacombs
    public int getNumberOfMonsters(){
        return numberOfMonsters;
    }

    // Return catacomb size
    public int getCatacombSize(){
        return catacombSize;
    }

    // Return number of monsters defeated
    public int getMonstersDefeated(){
        return monstersDefeated;
    }

    /**
     * Remove defeated monster
     * @param inputIndex index of defeated monster in monster location array
     */
    public void updateMonsterLocations(int inputIndex){
        monsterLocations.remove(inputIndex);
    }

    /**
     * Update total number of monsters defeated
     */
    public void updateMonstersDefeated(){
        monstersDefeated+=1;
    }

    /**
     * Update locations of defeated monsters
     * @param inputLocation row/col location of defeated monster
     */
    public void updateDeadMonsters(int[] inputLocation){
        deadMonsters.add(inputLocation);
    }

    // Return locations of defeated monsters
    public ArrayList getDeadMonsters(){
        return deadMonsters;
    }

}
