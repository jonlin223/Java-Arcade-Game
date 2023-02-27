package unsw.dungeon;

public class EnemyCollision implements Collision {

    public EnemyCollision() {}

    /**
     * 
     * @param d The dungeon in which we are processing the move
     * @param e The entity whose move we are processing
     * @param x The x coordinate of the tile we are checking
     * @param y The y coordinate of the tile we are checking
    **/
    @Override
    public boolean processMove(Dungeon d, Entity e, int x, int y) {
        
        // Cast Entity as an Enemy

        // Check if there are any entities on the tile
        Entity entity = d.checkTile(x, y);
        Entity moveableEntity = d.checkTileMoveable(x, y);

        // If tile taken by Enemy
        if (moveableEntity != null && moveableEntity instanceof Enemy) {
            return false;
        }

        // If tile taken by Boulder
        if (moveableEntity != null && moveableEntity.getClass().equals(Boulder.class)) {
            return false;
        }

        // If tile taken by Player
        if (moveableEntity != null && moveableEntity.getClass().equals(Player.class)) {
            return true;
        }

        // If tile is empty
        if (entity == null) return true;

        // If tile taken by Wall
        if (entity.getClass().equals(Wall.class)) {
            return false;
        }

        // If tile taken by Door
        if (entity.getClass().equals(Door.class)) {
            return doorInteraction(entity);
        }

        // If tile taken by Key
        if (entity.getClass().equals(Key.class)) {
            return true;
        }

        // If tile taken by Treasure
        if (entity.getClass().equals(Treasure.class)) {
            return true;
        }

        // If tile taken by Sword
        if (entity.getClass().equals(Sword.class)) {
            return true;
        }

        // If tile taken by Portal
        if (entity.getClass().equals(Portal.class)) {
            return true;
        }

        // If tile taken by Potion
        if (entity instanceof Potion) {
            return true;
        }

        //If tile taken by Explosive (mine turtle)
        if (entity instanceof Explosive) {
            return true;
        }

        // If tile taken by FloorSwitch
        if (entity.getClass().equals(FloorSwitch.class)) {
            return true;
        }

        return false;

    }

    public boolean doorInteraction(Entity e) {
        Door door = (Door) e;
        if (door.isOpen()) return true;
        return false;
    }

}