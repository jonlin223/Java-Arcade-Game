/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements PlayerMovementSubject{

    private int width, height;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> moveableEntities;
    private ArrayList<PlayerMovementObserver> playerObserverList;
    private Player player;
    private GoalController goalController;
    private BooleanProperty goalCompleted;
    private BooleanProperty playerAlive;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.moveableEntities = new ArrayList<>();
        this.playerObserverList = new ArrayList<>();
        this.player = null;
        this.goalController = new GoalController(this);
        this.goalCompleted = new SimpleBooleanProperty(false);
        this.playerAlive = new SimpleBooleanProperty(true);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Entity> getMoveableEntities() {
        return moveableEntities;
    }

    public ArrayList<PlayerMovementObserver> getPlayerObserverList() {
        return playerObserverList;
    }

    public BooleanProperty goalCompleted() {
        return goalCompleted;
    }

    public BooleanProperty playerAlive() {
        return playerAlive;
    }

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }

    public boolean isPlayerTrackable() {
        return player.isTrackable();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void removePlayer() {
        player = null;
        playerAlive.setValue(false);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        entity.hideEntity();
    }

    public void addMoveableEntity(Entity entity) {
        moveableEntities.add(entity);
    }

    public void removeMoveableEntity(Entity entity) {
        moveableEntities.remove(entity);
        entity.hideEntity();
    }

    public void killEnemy(Enemy enemy) {
        if (!(enemy instanceof KillerGnome)) {
            removeMoveableEntity(enemy);
            detach(enemy);
        }
    }

    /**
     * Check if the tile given is obstructed by a wall
     * @param x The x axis of the tile we are checking
     * @param y The y axis if the tile we are checking
     * @return Boolean representing if tile is obstructed by wall
     */
    public Entity checkTile(int x, int y) {
        for (Entity e: entities) {
            if (e.getX() == x && e.getY() == y) return e;
        }
        return null;
    }

    public Entity checkTileMoveable(int x, int y) {
        for (Entity e: moveableEntities) {
            if (e.getX() == x && e.getY() == y) return e;
        }
        return null;
    }
    
    //explaination as to why subject is implemented in dungeon, not in player:

        //We add run the attach method within enemy (in the constructor when it is created)
        //if we were to have our subject (and observer list) in player, this would require a call such as player.attach(this) in enemy
        //since there is no relationship between player and enemy, this would result in coupling
        //instead, we place the observer within dungeon
        //since there is a has-a relationship between player and dungeon, we can call the notifyObserver method from player, in dungeon, every time we want to notify the observers
        //as such, the subject is the dungeon, however that is specifically the player that is related to the dungeon, and the notification is sent out whenever they move
        //dungeon is a controller for our different entities (player, enemy)

    public void attach(PlayerMovementObserver o) {
        if(!playerObserverList.contains(o)) {
            playerObserverList.add(o);
        }
    }

	public void detach(PlayerMovementObserver o) {
        playerObserverList.remove(o);
    }

    public void notifyObservers() {
        if (player == null) return;
        ArrayList <PlayerMovementObserver> newList = new ArrayList<PlayerMovementObserver>(playerObserverList);
        int curr = 0;
        for (PlayerMovementObserver curr_observing: newList) {
            if (curr >= playerObserverList.size()) return;
            PlayerMovementObserver change = playerObserverList.get(curr);
            change.update(this);
            curr++;
        }
    }

    /**
     * Find a Portal with the given coordinates in the list of Entities
     * @param x The x coordinate we are checking
     * @param y The y coordinate we are checking
     * @return The Portal (null if could not be found)
     */
    public Portal findPortal(int x, int y) {
        for (Entity e: entities) {
            if (e.getClass().equals(Portal.class) && e.getX() == x && e.getY() == y) {
                return (Portal) e;
            }
        }
        return null;
    }

    /**
     * Find the portal with paired id in the list of entities and set it as the pair to the
     * given portal. Similarly, set the pair of the pair as the given Portal. This method is
     * run whenever a Portal constructor is called, if no pair is found, nothing happens.
     * No error should occur as all portals should have a pair input
     * @param portal
     */
    public void setPair(Portal portal) {
        for (Entity e: entities) {
            if (e.getClass().equals(Portal.class)) {
                Portal pair = (Portal) e;
                if (portal.getId() == pair.getId()) {
                    portal.setPair(pair);
                    pair.setPair(portal);
                }
            }
        }
    }

    public Enemy findEnemy(int x, int y) {
        for (Entity e: moveableEntities) {
            if (e instanceof Enemy && x == e.getX() && y == e.getY()) {
                return (Enemy) e;
            }
        }
        return null;
    }

    public void enemyInteraction(Enemy enemy) {
        player.enemyInteraction(enemy);
    }

    public void angerEnemies() {
        for (Entity entity: moveableEntities) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                enemy.getAngry();
            }
        }
    }

    public void scareEnemies() {
        for (Entity entity: moveableEntities) {
            if (entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                enemy.getScared();
            }
        }
    }

    public FloorSwitch findFloorSwitch(int x, int y) {
        for (Entity entity: entities) {
            if (entity.getClass().equals(FloorSwitch.class) && entity.getX() == x && entity.getY() == y) {
                return (FloorSwitch) entity;
            }
        }
        return null;
    }
    
    ///////////////////////////
    ////// GOAL CHECKERS //////
    ///////////////////////////

    public GoalController getGoalController() {
        return goalController;
    }

    public void setGoalController(GoalController goalController) {
        this.goalController = goalController;
    }

    /**
     * Check that if the Player is standing on an exit
     * @return Boolean representing if Player is on Exit
     */
    public boolean isPlayerOnExit() {

        for (Entity entity: entities) {
            if (entity.getClass().equals(Exit.class) && player.getX() == entity.getX()
            && player.getY() == entity.getY()) {
                return true;
            } 
        }

        return false;

    }

    /**
     * Check if all Enemies have been defeated
     * @return Boolean representing if all Enemies are dead
     */
    public boolean areEnemiesDead() {
        // All Enemies are not dead if any exist in moveableEntities
        for (Entity entity: moveableEntities) {
            if (entity instanceof Enemy && !(entity instanceof KillerGnome)) return false;
        }
        return true;
    }

    public boolean areSwitchesActivated() {
        // All floorSwitches are not activated if any floorSwitch is not activated
        for (Entity entity: entities) {
            if (entity.getClass().equals(FloorSwitch.class)) {
                FloorSwitch floorSwitch = (FloorSwitch) entity;
                if (!floorSwitch.isActivated()) {
                    return false;
                } 
            }
        }
        return true;
    }

    public boolean isTreasureCollected() {
        // All treasure has not been collected if any still lying around
        for (Entity entity: entities) {
            if (entity.getClass().equals(Treasure.class)) return false;
        }
        return true;
    }

    public boolean isGoalCompleted() {
        return goalController.goalCompleted();
    }

    public void goalIsCompleted() {
        goalCompleted.setValue(true);
    }

    public String createGoalString() {
        return goalController.createGoalString();
    }

}
