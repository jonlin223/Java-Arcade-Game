package unsw.dungeon;

public interface Collision {

    /**
     * Checks if the tile given by the coordinates can be moved onto. If the tile is taken
     * up by an entity, the function also processes the interaction.
     * @return A boolean representing if the player can move onto the tile or not.
     */
    public boolean processMove(Dungeon d, Entity e, int x, int y);

}