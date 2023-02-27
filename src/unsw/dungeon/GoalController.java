package unsw.dungeon;

public class GoalController {
    
    private Dungeon dungeon;
    private Goal goal;

    public GoalController(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Goal getGoal() {
        return goal;
    }

    public boolean goalCompleted() {
        if (goal == null) return false;
        return goal.isComplete(dungeon);
    }

    public String createGoalString() {
        return goal.createGoalString();
    }

}