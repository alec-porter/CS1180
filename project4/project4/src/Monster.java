import java.util.Random;

public class Monster {

    private String name = "Monster";
    private int health = 25;
    private int damage = 0;
    private boolean isDefeated = false;
    
    public void takeDamage(int input){
        health = health - input;
        if (health <= 0 ){
            isDefeated = true;
        }
    }

    public int dealDamage(){
        Random rand = new Random();
        damage = rand.nextInt(5)+1;
        return damage;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

    public boolean getIsDefeated(){
        return isDefeated;
    }

}
