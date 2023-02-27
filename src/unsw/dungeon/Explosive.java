package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

public interface Explosive {
    public int getRadius();
    public void detonate(Dungeon dungeon);
    public BooleanProperty detonated();
}