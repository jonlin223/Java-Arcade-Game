package unsw.dungeon;

public class Weak implements PlayerState {

    private Player player;

    public Weak(Player player) {
        this.player = player;
    }

    /**
     * Implementation of Player vs Enemy Interaction where Enemy kills Player on contact
     * Player has this state when they are not carrying a weapon
     * @param enemy The Enemy the Player is colliding with
     */
    @Override
    public void enemyInteraction(Enemy enemy) {

        player.removePlayer();

    }

}