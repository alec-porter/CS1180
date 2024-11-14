import java.util.Random;

public class Hero {

    private String name = "Hero";
    private int health = 100;
    private int [] position = new int [2];
    private int damage = 0;
    private boolean isDefeated = false;

    public void takeDamage(){
        Random rand = new Random();
        health = health - rand.nextInt(9) + 1;
    }

    public void movementDamage(){
        health = health - 2;
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


}
