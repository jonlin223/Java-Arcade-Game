package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InventoryInfo {
    
    private IntegerProperty weaponDurability;
    private IntegerProperty potionDuration;

    public InventoryInfo() {
        this.weaponDurability = new SimpleIntegerProperty(0);
        this.potionDuration = new SimpleIntegerProperty(0);
    }

    public IntegerProperty weaponDurability() {
        return weaponDurability;
    }

    public int getWeaponDurability() {
        return weaponDurability.get();
    }

    public void setWeaponDurability(int durability) {
        weaponDurability.set(durability);
    }

    public IntegerProperty potionDuration() {
        return potionDuration;
    }

    public int getPotionDuration() {
        return potionDuration.get();
    }

    public void setPotionDuration(int duration) {
        potionDuration.set(duration);
    }

}