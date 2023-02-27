package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private ObjectProperty<Key> key;
    private ArrayList<Entity> collectibles; 
    private Collision collision;
    private ObjectProperty<Weapon> weapon;
    private ObjectProperty<Potion> potion;
    private InventoryInfo info;

    private PlayerState strongState;
    private PlayerState weakState;
    private PlayerState playerState;

    private BooleanProperty trackable;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.collision = new PlayerCollision();
        this.key = new SimpleObjectProperty<Key>(null);
        this.weapon = new SimpleObjectProperty<Weapon>(new NoWeapon());
        this.potion = new SimpleObjectProperty<Potion>(new NoPotion());
        this.info = new InventoryInfo();
        this.strongState = new Strong(this);
        this.weakState = new Weak(this);
        this.playerState = weakState;
        this.trackable = new SimpleBooleanProperty(true);
        dungeon.setPlayer(this);
        dungeon.addMoveableEntity(this);
        collectibles = new ArrayList<>();
    }

    public int getKeyId() {
        return key.get().getId();
    }

    public Potion getPotion() {
        return potion.get();
    }

    public InventoryInfo getInfo() {
        return info;
    }

    public ObjectProperty<Weapon> weapon() {
        return weapon;
    }

    public ObjectProperty<Potion> potion() {
        return potion;
    }

    public ObjectProperty<Key> key() {
        return key;
    }

    public void moveUp() {
        if (getY() > 0 && collision.processMove(dungeon, this, getX(), getY() - 1)) {
            y().set(getY() - 1);
        }
        postMovement();
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && collision.processMove(dungeon, this, getX(), getY() + 1)) {
            y().set(getY() + 1);
        }
        postMovement();
    }

    public void moveLeft() {
        if (getX() > 0 && collision.processMove(dungeon, this, getX() - 1, getY())) {
            x().set(getX() - 1);
        }
        postMovement();
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && collision.processMove(dungeon, this, getX() + 1, getY())) {
            x().set(getX() + 1);
        }
        postMovement();
    }

    public void postMovement() {
        checkEnemy(getX(), getY());
        if (dungeon.isGoalCompleted()) {
            dungeon.goalIsCompleted();
        }
        findPortal(getX(), getY());
        checkIfDetonated(getX(), getY());
        managePotion();
        dungeon.notifyObservers();
    }

    /**
     * Teleport a Player through a Portal
     * @param portal The Portal we are teleporting the Player through
     */
    public void teleportPlayer(Portal portal) {
        x().set(portal.getPairX());
        y().set(portal.getPairY());
        checkEnemy(getX(), getY());
    }

    /**
     * Check if Enemy is occupying tile.
     * Use playerState to process interaction.
     * THIS IS OUR STATE PATTERN YAY!
     * @param x The x coordinate of the tile we are looking at
     * @param y The y coordinate of the tile we are looking at
     */
    public void checkEnemy(int x, int y) {
        Enemy enemy = dungeon.findEnemy(x, y);
        if (enemy != null) {
            playerState.enemyInteraction(enemy);
        }
    }

    /**
     * Check if there is a Portal on the given tile.
     * Teleport the Player if there is.
     * @param x The x coordinate of the tile we are checking
     * @param y The y coordinate of the tile we are checking
     */
    public void findPortal(int x, int y) {
        Portal p = dungeon.findPortal(x, y);
        if (p != null) {
            teleportPlayer(p);
        }
    }

    public void checkIfDetonated(int x, int y) {
        Entity curr = dungeon.checkTile(x, y);
        if (curr instanceof Explosive) {
            Explosive bomb = (Explosive) curr;
            bomb.detonate(dungeon);
        }
    }

    public void addKey(Key key) {
        this.key.set(key);
    }

    public void removeKey() {
        this.key.set(null);
    }

    public boolean hasKey() {
        return (key.get() != null);
    }

    public void addCollectible(Entity collectible) {
        collectibles.add(collectible);
    }
    
    public void removeCollectible(Entity unwanted) {
        collectibles.remove(unwanted);
    }
    
    public ArrayList<Entity> getCollectibles() {
        return collectibles;
    }

    public void addWeapon(Weapon weapon) {
        this.weapon.set(weapon);
        info.setWeaponDurability(this.weapon.get().getDurability());
    }

    public void removeWeapon() {
        this.weapon.set(new NoWeapon());
        info.setWeaponDurability(0);
    }

    public boolean hasWeapon() {
        if (weapon.get().getClass().equals(NoWeapon.class)) return false;
        return true;
    }

    public int getWeaponDurability() {
        return weapon.get().getDurability();
    }

    public void removeWeaponDurability() {
        weapon.get().removeDurability();
        info.setWeaponDurability(weapon.get().getDurability());
    }

    public PlayerState getStrongState() {
        return strongState;
    }

    public PlayerState getWeakState() {
        return weakState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public void enemyInteraction(Enemy enemy) {
        playerState.enemyInteraction(enemy);
    }

    public void killEnemy(Enemy enemy) {
        dungeon.killEnemy(enemy);
    }

    public void removePlayer() {
        dungeon.removePlayer();
    }

    public void setPotion(Potion potion) {
        this.potion.set(potion);
        info.setPotionDuration(this.potion.get().getDuration());
        potion.addEffect(this);
    }

    public boolean hasPotion() {
        if (potion.get().getClass().equals(NoPotion.class)) return false;
        return true;
    }

    public int getPotionDuration() {
        return potion.get().getDuration();
    }

    public void managePotion() {
        potion.get().lowerDuration();
        info.setPotionDuration(potion.get().getDuration());
        if (potion.get().getDuration() == 0) {
            potion.get().removeEffect(this);
            potion.set(new NoPotion());
            info.setPotionDuration(0);
        }
    }

    public void angerEnemies() {
        dungeon.angerEnemies();
    }

    public void scareEnemies() {
        dungeon.scareEnemies();
    }

    public BooleanProperty trackable() {
        return trackable;
    }

    public boolean isTrackable() {
        return trackable.get();
    }

    public void setTrackable(boolean trackable) {
        this.trackable.set(trackable);
    }

}
