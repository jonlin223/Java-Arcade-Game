package unsw.dungeon;

public class Enemy extends Entity implements PlayerMovementObserver {

    private Dungeon dungeon;
    private Behaviour behaviour;
    private Collision collision;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        dungeon.attach(this);
        dungeon.addMoveableEntity(this);;
        this.behaviour = new Aggressive(getPlayerX(), getPlayerY());
        collision = new EnemyCollision();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    // processMove kept for testing
    public void moveUp() {
        if (getY() > 0 && collision.processMove(dungeon, this, getX(), getY() - 1)) {
            y().set(getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && collision.processMove(dungeon, this, getX(), getY() + 1)) {
            y().set(getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() > 0 && collision.processMove(dungeon, this, getX() - 1, getY())) {
            x().set(getX() - 1);
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && collision.processMove(dungeon, this, getX() + 1, getY())) {
            x().set(getX() + 1);
        }
    }


    public void getScared() {
        Behaviour submissive = new Scared(getPlayerX(), getPlayerY());
        this.behaviour = submissive;
    }

    public void getAngry() {
        Behaviour badass = new Aggressive(getPlayerX(), getPlayerY());
        this.behaviour = badass;
    }

    public void checkConsequences() {
        findPortal();
        checkExplosion(getX(), getY());
    }

    public void checkExplosion(int x, int y) {
        Entity curr = dungeon.checkTile(x, y);
        if (curr instanceof Explosive) {
            Explosive bomb = (Explosive) curr;
            bomb.detonate(dungeon);
        }
    }

    public void findPortal() {
        Portal p = dungeon.findPortal(getX(), getY());
        if (p != null) {
            teleportEnemy(p);
        }
    }

    public void teleportEnemy(Portal portal) {
        x().set(portal.getPairX());
        y().set(portal.getPairY());
    }

    public void moveEnemy() {
        
        //check before the enemy moves that we have not obliterated the player (or if our player dies in the middle of a turn)
        if (dungeon.getPlayer() == null) return;
        behaviour.moveEnemy(this, getPlayerX(), getPlayerY());

        checkConsequences();
        
        //check if an enemy has killed the player when it has moved, after the move. 
        if (dungeon.getPlayer() == null) return;
        //check if player and enemy on the same tile after
        
        if (CheckPlayerCollision()) {
            dungeon.enemyInteraction(this);
        }
    }

    /**
     * Check if Enemy Entity is colliding with Enemy
     * @return Boolean representing if Enemy is colliding with Player
     */
    public boolean CheckPlayerCollision() {
        int playerX = dungeon.getPlayerX();
        int playerY = dungeon.getPlayerY();
        if (getX() == playerX && getY() == playerY) return true;
        else return false;
    }

    public void update(PlayerMovementSubject notification) {
        moveEnemy();
    }

    public int getPlayerX() {
        return dungeon.getPlayerX();
    }

    public int getPlayerY() {
        return dungeon.getPlayerY();
    }

    public boolean processMove(Dungeon dungeon, Entity entity, int x, int y) {
        if (!dungeon.isPlayerTrackable()) return false;
        return collision.processMove(dungeon, entity, x, y);
    }

}