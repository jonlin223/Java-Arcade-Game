package unsw.dungeon;

public class BouldersGoal implements Goal {

    @Override
    public boolean isComplete(Dungeon dungeon) {
        return dungeon.areSwitchesActivated();
    }

    @Override
    public String createGoalString() {
        return "Push Boulders Onto Switches";
    }
    
}