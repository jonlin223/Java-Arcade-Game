package unsw.dungeon;

public interface Goal {
    
    public boolean isComplete(Dungeon dungeon);
    public String createGoalString();

}