public class lab11 {
    public static void main(String[] args) throws Exception {
        System.out.println("Alec Porter Lab 11\n\n");

        Player player1 = new Player("Bob", 25);  // create player1 object
        Player player2 = new Player("Ralph", 15);  // create player2 object

        // if both players still have health, print status and deal damage
        while (player1.getHealth() > 0 && player2.getHealth() > 0){
            System.out.println(player1 + "  |  " + player2);
            player1.dealDamage();
            player2.dealDamage();
        }

        // print winner and their remaming health
        if (player1.getHealth() > 0){
            System.out.println("Winner is " + player1.getName() + ": HP " + player1.getHealth());
        }
        else{
            System.out.println("Winner is " + player2.getName() + ": HP " + player2.getHealth());
        }


    }
}
