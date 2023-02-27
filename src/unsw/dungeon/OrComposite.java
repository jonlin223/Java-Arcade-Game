package unsw.dungeon;

import java.util.ArrayList;

public class OrComposite implements Goal {

    private ArrayList<Goal> subGoals;

    public OrComposite() {
        this.subGoals = new ArrayList<>();
    }

    @Override
    public boolean isComplete(Dungeon dungeon) {
        
        // Return true if any subGoal is complete
        for (Goal goal: subGoals) {
            if (goal.isComplete(dungeon)) return true;
        }

        // Otherwise return false
        return false;

    }

    @Override
    public String createGoalString() {
        String goalString = "";
        for (Goal goal : subGoals) {
            goalString += goal.createGoalString();
            if (subGoals.indexOf(goal) != subGoals.size() - 1) {
                goalString += " OR ";
            }
        }
        return goalString;
    }

    public void addSubGoal(Goal goal) {
        subGoals.add(goal);
    }
    
}