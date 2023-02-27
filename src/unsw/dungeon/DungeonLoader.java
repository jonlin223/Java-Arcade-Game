package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        JSONObject goals = json.getJSONObject("goal-condition");
        Goal goal = loadGoals(goals);
        dungeon.getGoalController().setGoal(goal);

        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            onLoad(player);
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            dungeon.addEntity(wall);
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            dungeon.addEntity(exit);
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            dungeon.addEntity(treasure);
            break;
        case "door":
            int doorId = json.getInt("id");
            Door door = new Door(x, y, doorId);
            onLoad(door);
            dungeon.addEntity(door);
            break;
        case "key":
            int keyId = json.getInt("id");
            Key key = new Key(x, y, keyId);
            onLoad(key);
            dungeon.addEntity(key);
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            break;
        case "switch":
            FloorSwitch floorSwitch = new FloorSwitch(x, y);
            onLoad(floorSwitch);
            dungeon.addEntity(floorSwitch);
            break;
        case "portal":
            int portalId = json.getInt("id");
            Portal portal = new Portal(dungeon, x, y, portalId);
            onLoad(portal);
            dungeon.addEntity(portal);
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            break;
        case "hound":
            Hound hound = new Hound(dungeon, x, y);
            onLoad(hound);
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            dungeon.addEntity(sword);
            break;
        case "invincibility":
            InvincibilityPotion invincibilityPotion = new InvincibilityPotion(x, y);
            onLoad(invincibilityPotion);
            dungeon.addEntity(invincibilityPotion);
            break;
        case "invisibility":
            InvisibilityPotion invisibilityPotion = new InvisibilityPotion(x, y);
            onLoad(invisibilityPotion);
            dungeon.addEntity(invisibilityPotion);
            break;
        case "mineTurtle":
            MineTurtle mineTurtle = new MineTurtle(x, y);
            onLoad(mineTurtle);
            dungeon.addEntity(mineTurtle);
            break;
        case "killerGnome":
            KillerGnome killerGnome = new KillerGnome(dungeon, x, y);
            onLoad(killerGnome);
            dungeon.addEntity(killerGnome);
            break;
        }
    }

    private Goal loadGoals(JSONObject json) {

        String goal = json.getString("goal");
        JSONArray subgoals;

        switch(goal) {
        case "AND":
            AndComposite and = new AndComposite();
            subgoals = json.getJSONArray("subgoals");
            for (int i = 0; i < subgoals.length(); i++) {
                and.addSubGoal(loadGoals(subgoals.getJSONObject(i)));
            }
            return and;
        case "OR":
            OrComposite or = new OrComposite();
            subgoals = json.getJSONArray("subgoals");
            for (int i = 0; i < subgoals.length(); i++) {
                or.addSubGoal(loadGoals(subgoals.getJSONObject(i)));
            }
            return or;
        case "exit":
            ExitGoal exit = new ExitGoal();
            return exit;
        case "enemies":
            EnemyGoal enemy = new EnemyGoal();
            return enemy;
        case "boulders":
            BouldersGoal boulders = new BouldersGoal();
            return boulders;
        case "treasure":
            TreasureGoal treasure = new TreasureGoal();
            return treasure;
        }
        return null;
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Hound hound);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(InvincibilityPotion invincibilityPotion);

    public abstract void onLoad(InvisibilityPotion invisibilityPotion);

    public abstract void onLoad(MineTurtle mineTurtle);

    public abstract void onLoad(KillerGnome killergnome);

}
