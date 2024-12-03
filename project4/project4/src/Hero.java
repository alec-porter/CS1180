import java.util.Random;

/**
 * Hero Class
 * 
 * Sets the name, health, attack damage, and status of the user player.
 * Tracks player health as they navigate through the catacombs and fight monsters.
 * Detemines the amount of damage the player does during monster fights.
 * 
 * @author Alec Porter
 */
public class Hero {

    private String name;
    private int health;
    private int damage;
    private boolean isDefeated;
    private int levelUp;

    public Hero(){
        name = "Hero";
        health = 100;
        damage = 0;
        isDefeated = false;
        levelUp = 1;
    }

    /**
     * Hero takes damage based on input
     * @param input amount of damage done
     */
    public void takeDamage(int input){
        health = health - input;
        if (health <= 0 ){
            isDefeated = true;
        }
    }

    /**
     * Amount of damage dealt by the Hero
     * Max damage increases based on current level
     * @return amount of damaged dealt
     */
    public int dealDamage(){
        Random rand = new Random();
        damage = rand.nextInt(9)+levelUp;
        return damage;
    }

    /**
     * Hero takes 2 damage every time the player moves
     */
    public void movementDamage(){
        health = health - 2;
        if (health <= 0){
            isDefeated = true;
        }
    }

    /**
     * Set the Hero's name
     * @param input player input name for Hero
     */
    public void setName(String input){
        name = input;
    }

    //Retruns if the player been defeated
    public boolean getIsDefeated(){
        return isDefeated;
    }

    // Returns player health
    public int getHealth(){
        return health;
    }

    // Returns player name
    public String getName(){
        return name;
    }

    /**
     * Increase player damage level
     */
    public void setLevelUp(){
        levelUp += 1;
    }


}
