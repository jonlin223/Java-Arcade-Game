package unsw.dungeon;

public class InvincibilityPotion extends Entity implements Potion {

    int duration;

    public InvincibilityPotion(int x, int y) {
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
        player.setPlayerState(player.getStrongState());
        player.scareEnemies();
    }

    @Override
    public void removeEffect(Player player) {
        if (!player.hasWeapon()) {
            player.setPlayerState(player.getWeakState());
        }
        player.angerEnemies();
    }

}