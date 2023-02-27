package unsw.dungeon;

public class ExitGoal implements Goal {

    @Override
    public boolean isComplete(Dungeon dungeon) {
        return dungeon.isPlayerOnExit();
    }

    @Override
    public String createGoalString() {
        return "Reach The Exit";
    }
    
}