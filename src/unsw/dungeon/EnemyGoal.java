package unsw.dungeon;

public class EnemyGoal implements Goal {

    @Override
    public boolean isComplete(Dungeon dungeon) {
        return dungeon.areEnemiesDead();
    }

    @Override
    public String createGoalString() {
        return "Kill All Enemies";
    }
    
}