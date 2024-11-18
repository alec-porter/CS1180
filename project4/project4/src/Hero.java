import java.util.Random;

public class Hero {

    private String name = "Hero";
    private int health = 100;
    private int damage = 0;
    private boolean isDefeated = false;
    private int levelUp = 1;

    public void takeDamage(int input){
        health = health - input;
        if (health <= 0 ){
            isDefeated = true;
        }
    }

    public int dealDamage(){
        Random rand = new Random();
        damage = rand.nextInt(9)+levelUp;
        return damage;
    }

    public void movementDamage(){
        health = health - 2;
        if (health <= 0){
            isDefeated = true;
        }
    }

    public void setName(String input){
        name = input;
    }

    public boolean getIsDefeated(){
        return isDefeated;
    }

    public int getHealth(){
        return health;
    }

    public String getName(){
        return name;
    }

    public void setLevelUp(){
        levelUp += 1;
    }


}
