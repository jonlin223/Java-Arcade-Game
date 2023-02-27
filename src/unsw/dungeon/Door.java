package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
    
    private int id;
    private BooleanProperty open;

    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        open = new SimpleBooleanProperty(false);
    }

    public int getId() {
        return id;
    }

    public BooleanProperty open() {
        return open;
    }

    public boolean isOpen() {
        return open.getValue();
    }

    public void openDoor() {
        open.setValue(true);
    }

}