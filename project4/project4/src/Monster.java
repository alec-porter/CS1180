import java.util.Random;

public class Monster {

    private String name = "Monster";
    private int health = 25;
    private int [] position = new int [2];
    private int damage = 0;
    private boolean isDefeated = false;
    private int monsterNumber = 1;

    public Monster(int[] location, int monsterNumber){
        setName(monsterNumber);
        setLocation(location);
        }

    public int attackDamage(){
        Random rand = new Random();
        damage = rand.nextInt(4) + 1;
        return damage;
    }

    public void takeDamage(){
        Random rand = new Random();
        health = health - rand.nextInt(9) + 1;
    }

    public void setLocation(int[] input){
        position = input;
    }

    public void setName(int input){
        name = "Monster " + input;
    }

    public int[] getLocation(){
        return position;
    }

    public String getName(){
        return name;
    }

    public int getHealth(){
        return health;
    }

}
