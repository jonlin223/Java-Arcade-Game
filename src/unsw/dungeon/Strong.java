package unsw.dungeon;

public class Strong implements PlayerState {

    private Player player;

    public Strong(Player player) {
        this.player = player;
    }

    /**
     * Implementation of Player vs Enemy Interaction where Player kills Enemy on contact
     * Player has this state when they are carrying a weapon or invincible
     * @param enemy The Enemy the Player is colliding with
     */
    @Override
    public void enemyInteraction(Enemy enemy) {
        
        if (enemy instanceof KillerGnome) {
            player.removePlayer();
            return;
        }
        
        // Kill the enemy
        player.killEnemy(enemy);

        // Remove durability from the weapon
        // Prioritise Invincibility Potion over Weapon
        if (!getPotion().getClass().equals(InvincibilityPotion.class)) {
            player.removeWeaponDurability();
        }

        // If durability gets to 0, remove the weapon
        // Also change the player's state
        if (player.getWeaponDurability() == 0) {
            player.removeWeapon();
            player.setPlayerState(player.getWeakState());
        }

    }

    public Potion getPotion() {
        return player.getPotion();
    }

}