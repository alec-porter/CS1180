import java.util.Random;

public class Monster {

    private final String name;
    private int health;
    private int damage;
    private boolean isDefeated;

    public Monster(){
        name = "Monster";
        health = 25;
        damage = 0;
        isDefeated = false;
    }
    
    /**
     * Monster takes damage based on the input
     * @param input amount of damage taken
     */
    public void takeDamage(int input){
        health = health - input;
        if (health <= 0 ){
            isDefeated = true;
        }
    }

    /**
     * Amount of damage dealt by the monster
     * @return amount of damage dealt
     */
    public int dealDamage(){
        Random rand = new Random();
        damage = rand.nextInt(5)+1;
        return damage;
    }

    // Return monster name
    public String getName(){
        return name;
    }

    // Return monster health
    public int getHealth(){
        return health;
    }

    // Return if the monster has been defeated
    public boolean getIsDefeated(){
        return isDefeated;
    }

}
