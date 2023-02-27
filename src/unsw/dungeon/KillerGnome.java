package unsw.dungeon;

import java.util.Random;

public class KillerGnome extends Enemy{
    
    private int fate;

    public KillerGnome(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.fate = 0;
    }

    //we override scared switch.
    //The Killer Gnome is afraid of nobody
    //he does not forgive. He does not forget.
    @Override
    public void getScared() {
    }

    @Override
    public void moveEnemy() {
        
        Random r = new Random();
        fate = r.nextInt(5);

        if ((fate & 1) == 0) {
            super.moveEnemy();
        }
    }
}