package unsw.dungeon;

public class Boulder extends Entity {
    
    private Dungeon dungeon;
    private Collision collision;
    private FloorSwitch floorSwitch;

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.collision = new BoulderCollision();
        this.floorSwitch = null;
        dungeon.addMoveableEntity(this);
    }

    public void setFloorSwitch(FloorSwitch floorSwitch) {
        this.floorSwitch = floorSwitch;
        if (floorSwitch != null) {
            floorSwitch.activateSwitch();
        }
    }

    public void removeFloorSwitch() {
        if (floorSwitch != null) {
            floorSwitch.deactivateSwitch();
            floorSwitch = null;
        }
    }

    /**
     * Checks if Boulder can move onto the given tile.
     * Manages any interactions between Boulder and any Entity on that tile.
     * For example, activates Floor Switch.
     * @param x The x coordinate we are looking at
     * @param y The y coordinate we are looking at
     * @return Boolean representing whether Boulder can move onto the tile
     */
    public boolean processMove(int x, int y) {
        return collision.processMove(dungeon, this, x, y);
    }

    public void moveUp() {
        if (getY() > 0) {
            y().set(getY() - 1);
            postMove();
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            y().set(getY() + 1);
            postMove();
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            x().set(getX() - 1);
            postMove();
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            x().set(getX() + 1);
            postMove();
        }
    }

    public void postMove() {
        FloorSwitch fSwitch = dungeon.findFloorSwitch(getX(), getY());
        removeFloorSwitch();
        setFloorSwitch(fSwitch);
    }

}