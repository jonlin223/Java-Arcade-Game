package unsw.dungeon;

/**
 * When Player is invisible, Enemy does not move as they cannot find the player
 */
public class InvisibilityPotion extends Entity implements Potion {

    int duration;

    public InvisibilityPotion(int x, int y) {
        super(x, y);
        duration = 11;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void lowerDuration() {
        duration--;
    }

    @Override
    public void addEffect(Player player) {
        player.setTrackable(false);

    }

    @Override
    public void removeEffect(Player player) {
        player.setTrackable(true);
    }

}