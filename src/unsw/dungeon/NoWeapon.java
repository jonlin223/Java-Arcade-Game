package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NoWeapon implements Weapon {
    
    private IntegerProperty durability;

    public NoWeapon() {
        this.durability = new SimpleIntegerProperty(1);
    }

    public IntegerProperty durability() {
        return durability;
    }

    /**
     * Doesn't have a durabiity so we just return a positive number
     * to indicate that it can still be used
     */
    @Override
    public int getDurability() {
        return durability.get();
    }

    @Override
    public void removeDurability() {}

}