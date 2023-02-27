package unsw.dungeon;

public class FloorSwitch extends Entity {

    private boolean activated;

    public FloorSwitch(int x, int y) {
        super(x, y);
    }
    
    public boolean isActivated() {
        return activated;
    }

    public void activateSwitch() {
        activated = true;
    }

    public void deactivateSwitch() {
        activated = false;
    }

}