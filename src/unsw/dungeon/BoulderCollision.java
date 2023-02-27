package unsw.dungeon;

public class BoulderCollision implements Collision {

    @Override
    public boolean processMove(Dungeon d, Entity e, int x, int y) {

        Entity entity = d.checkTile(x, y);
        Entity moveableEntity = d.checkTileMoveable(x, y);

        // Cannot move onto tile taken by moveable entity
        if (moveableEntity != null) return false;

        // Can only move onto Floor Switch/Open Door/Empty Tile
        
        // If tile is empty
        if (entity == null) return true;

        // If tile taken by Floor Switch
        if (entity.getClass().equals(FloorSwitch.class)) {
            return true;
        }

        // If tile taken by Door
        if (entity.getClass().equals(Door.class)) {
            Door door = (Door) entity;
            if (door.isOpen()) return true;
            return false;
        }

        // Else
        return false;

    }

}