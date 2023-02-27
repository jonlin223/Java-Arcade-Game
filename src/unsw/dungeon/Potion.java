package unsw.dungeon;

public interface Potion {
    
    public int getDuration();
    public void lowerDuration();
    public void addEffect(Player player);
    public void removeEffect(Player player);

}