package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sword extends Entity implements Weapon {
    
    IntegerProperty durability;

    public Sword(int x, int y) {
        super(x, y);
        durability = new SimpleIntegerProperty(5);
    }

    @Override
    public int getDurability() {
        return durability.get();
    }

    @Override
    public void removeDurability() {
        durability.set(durability.get() - 1);
    }

}