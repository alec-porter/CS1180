import java.util.ArrayList;
import java.util.Random;


public class Catacombs {
    private int catacombSize = 5;
    private int numberOfMonsters = 4;
    private ArrayList<int[]> monsterLocations = new ArrayList<>();
    private ArrayList<int[]> deadMonsters = new ArrayList<>();
    private int monstersDefeated = 0;

    public Catacombs(){
        catacombSize = 5;
        numberOfMonsters = 4;
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

    public void setCatacombs(int inputSize){
        if (inputSize < 5 || inputSize > 10){
            catacombSize = 5;
        }
        else{
            monsterLocations.clear();
            catacombSize = inputSize;
            numberOfMonsters = inputSize*inputSize/6;
            Random rand = new Random();
            for (int i = 0; i < numberOfMonsters; i++){
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

    public ArrayList getMonsterLocations(){
        return monsterLocations;
    }
    
    public int getNumberOfMonsters(){
        return numberOfMonsters;
    }

    public int getCatacombSize(){
        return catacombSize;
    }

    public int getMonstersDefeated(){
        return monstersDefeated;
    }

    public void updateMonsterLocations(int input){
        monsterLocations.remove(input);
    }

    public void updateMonstersDefeated(){
        monstersDefeated+=1;
    }

    public void updateDeadMonsters(int[] input){
        deadMonsters.add(input);
    }

    public ArrayList getDeadMonsters(){
        return deadMonsters;
    }

}
