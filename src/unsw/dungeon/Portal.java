package unsw.dungeon;

public class Portal extends Entity {
    
    private int id;
    private Portal pair;

    public Portal(Dungeon dungeon, int x, int y, int id) {
        super(x, y);
        this.id = id;
        dungeon.setPair(this);
    }

    public int getId() {
        return id;
    }

    public void setPair(Portal pair) {
        this.pair = pair;
    }

    public int getPairX() {
        return pair.getX();
    }

    public int getPairY() {
        return pair.getY();
    }

}