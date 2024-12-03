import java.util.Random;
/**
 * Player Class
 * 
 * Creates players based on user input of player name, health, and max damage taken during battle.
 * Determines random amount of damage taken when attacked. 
 * Overwrites toSting() to print a custom message. 
 */
public class Player {
    
    String name;
    int health;
    int maxDamage;

    public Player(String inputName, int inputMaxDamage){ // constructor to set name and max damage
        name = inputName;
        maxDamage = inputMaxDamage;
        health = 100;
    }

    // setter methods

    public void setName(String inputName){ // set name
        name = inputName;
    }

    public void setHealth(int inputHealth){ // set health
        health = inputHealth;
    }

    public void setMaxDamage(int inputMaxDamage){ // set max damage
        maxDamage = inputMaxDamage;
    }


    // getter methods

    public String getName(){ // get name
        return name;
    }

    public int getHealth(){ // get name
        return health;
    }

    public int getMaxDamage(){ // get max damage
        return maxDamage;
    }

    // damage method
    public void dealDamage(){
        Random rand = new Random();
        int damageDone = rand.nextInt(maxDamage) + 1; // generate damage between 1 and max damage
        setHealth (getHealth() - damageDone); // update health based on damage done
    }

    // print method
    public String toString(){ // print name and health
        return getName() + ": HP " + getHealth();
    }
    

}
