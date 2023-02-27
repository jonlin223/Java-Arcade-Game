package unsw.dungeon;

public class TreasureGoal implements Goal {

    @Override
    public boolean isComplete(Dungeon dungeon) {
        return dungeon.isTreasureCollected();
    }

    @Override
    public String createGoalString() {
        return "Collect The Treasure";
    }
    
}