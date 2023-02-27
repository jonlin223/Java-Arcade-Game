package unsw.dungeon;

import java.util.ArrayList;

public class AndComposite implements Goal {

    private ArrayList<Goal> subGoals;

    public AndComposite() {
        this.subGoals = new ArrayList<>();
    }

    @Override
    public boolean isComplete(Dungeon dungeon) {
        
        // Return false if any subGoal is incomplete
        for (Goal goal: subGoals) {
            if (!goal.isComplete(dungeon)) return false;
        }

        // If subGoals complete, return true
        return true;

    }

    @Override
    public String createGoalString() {
        String goalString = "";
        for (Goal goal : subGoals) {
            goalString += goal.createGoalString();
            if (subGoals.indexOf(goal) != subGoals.size() - 1) {
                goalString += " AND ";
            }
        }
        return goalString;
    }

    public void addSubGoal(Goal goal) {
        subGoals.add(goal);
    }

}