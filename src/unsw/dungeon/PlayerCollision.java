package unsw.dungeon;

public class PlayerCollision implements Collision {

    public PlayerCollision() {
    }

    /**
     * 
     * @param d The dungeon in which we are processing the move
     * @param e The entity whose move we are processing
     * @param x The x coordinate of the tile we are checking
     * @param y The y coordinate of the tile we are checking
    **/
    @Override
    public boolean processMove(Dungeon d, Entity e, int x, int y) {

        // Cast Entity as a Player
        Player player = (Player) e;

        // Check if there are any entities on the tile
        Entity entity = d.checkTile(x, y);
        Entity moveableEntity = d.checkTileMoveable(x, y); 

        // If tile taken by Enemy
        // Don't worry about this situation, dealt with in Player
        // Remember to account for null

        // If tile taken by Boulder
        if (moveableEntity != null && moveableEntity.getClass().equals(Boulder.class)) {
            return boulderInteraction(player, moveableEntity);
        }

        // If tile is empty
        if (entity == null) return true;

        // If tile taken by Wall
        if (entity.getClass().equals(Wall.class)) {
            return false;
        }

        // If tile taken by Door
        if (entity.getClass().equals(Door.class)) {
            Door door = (Door) entity;
            return doorInteraction(player, door);
        }

        // If tile taken by Key
        if (entity.getClass().equals(Key.class)) {
            if (!player.hasKey()) {
                d.removeEntity(entity);
                player.addKey((Key) entity);
            }
            return true;
        }

        //if tile taken by a collectible
        if (entity instanceof Collectible) {
            d.removeEntity(entity);
            player.addCollectible(entity);
            return true;
        }

        // If tile taken by Weapon
        if (entity instanceof Weapon) {
            if (!player.hasWeapon()) {
                d.removeEntity(entity);
                player.addWeapon((Sword) entity);
                player.setPlayerState(player.getStrongState());
            }
            return true;
        }

        //if tile is taken by Exit
        if (entity.getClass().equals(Exit.class)) {
            return true;
        }

        //if tile is taken by Explosives
        if (entity instanceof Explosive) {
            return true;
        }

        // If tile taken by Portal
        // We don't need to check the other side since only Players and Enemies can go on Portals
        // Enemies already dealt with in Player + playerState
        if (entity.getClass().equals(Portal.class)) {
            return true;
        }

        // If tile taken by Potion
        if (entity instanceof Potion) {
            if (!player.hasPotion()) {
                d.removeEntity(entity);
                player.setPotion((Potion) entity);
            }
            return true;
        }

        // If tile taken by Floor Switch
        if (entity.getClass().equals(FloorSwitch.class)) {
            return true;
        }

        return false;

    }

    /**
     * Manages the interaction between a Player and a Door
     * @param player The Player we are looking at
     * @param door The Door we are looking at
     * @return A boolean representing whether Player can move onto Door
     */
    public boolean doorInteraction(Player player, Door door) {

        // If Door is open
        if (door.isOpen()) return true;

        // If Door closed but Player has Key
        if (player.hasKey() && player.getKeyId() == door.getId()) {
            door.openDoor();
            player.removeKey();
            return true;
        }

        // If Door closed and Player doesn't have key
        return false;

    }

    /**
     * Manages the interaction between a Player and a Boulder
     * @param player The Player we are looking at
     * @param entity The Boulder we are looking at
     * @return A Boolean representing if move can be made
     */
    public boolean boulderInteraction(Player player, Entity entity) {

        Boulder boulder = (Boulder) entity;

        // Check if Boulder can be moved to next position
        // Find where Boulder is moving to

        // If we are pushing up (Player below Boulder)
        if (player.getX() == boulder.getX() && player.getY() > boulder.getY()) {
            if (boulder.processMove(boulder.getX(), boulder.getY() - 1)) {
                boulder.moveUp();
            }
        }

        // If we are pushing down (Player above Boulder)
        if (player.getX() == boulder.getX() && player.getY() < boulder.getY()) {
            if (boulder.processMove(boulder.getX(), boulder.getY() + 1)) {
                boulder.moveDown();
            }
        }

        // If we are pushing left (Player to right of Boulder)
        if (player.getX() > boulder.getX() && player.getY() == boulder.getY()) {
            if (boulder.processMove(boulder.getX() - 1, boulder.getY())) {
                boulder.moveLeft();
            }
        }

        // If we are pushing right (Player to left of boulder)
        if (player.getX() < boulder.getX() && player.getY() == boulder.getY()) {
            if (boulder.processMove(boulder.getX() + 1, boulder.getY())) {
                boulder.moveRight();
            }
        }

        return false;

    }

}