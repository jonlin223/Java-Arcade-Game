package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class DoorTest {
    
    @Test
    public void testPlayerDoor() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Door door = new Door(3, 2, 0);
        dungeon.addEntity(door);

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);

        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);

        door.openDoor();
        player.moveRight();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);

    }

    @Test
    public void testKey() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Key key = new Key(3, 2, 0);
        dungeon.addEntity(key);

        Door door = new Door(4, 2, 0);
        dungeon.addEntity(door);

        player.moveRight();
        player.moveRight();
        assertEquals(player.getX(), 4);
        assertEquals(player.getY(), 2);
        assertEquals(door.isOpen(), true);

    }

    @Test
    public void testWrongKey() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Key key = new Key(3, 2, 0);
        dungeon.addEntity(key);

        Door door = new Door(4, 2, 1);
        dungeon.addEntity(door);

        player.moveRight();
        player.moveRight();

        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);
        assertEquals(door.isOpen(), false);

    }

    /**
     * Test that player can only pick up one key
     */
    @Test
    public void testSeveralKeys() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);

        Key k1 = new Key(2, 2, 0);
        dungeon.addEntity(k1);

        Key k2 = new Key(3, 2, 1);
        dungeon.addEntity(k2);

        Door door = new Door(4, 2, 1);
        dungeon.addEntity(door);

        player.moveRight();
        player.moveRight();
        player.moveRight();

        // player should not be able to pick up second key and therefore cannot open the door
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);
        assertEquals(door.isOpen(), false);

    }

    @Test
    public void testEnemyDoor() {

        Dungeon dungeon = new Dungeon(5, 5);
        new Player(dungeon, 0, 0);

        Enemy enemy = new Enemy(dungeon, 2, 2);

        Key key = new Key(3, 2, 0);
        dungeon.addEntity(key);

        Door door = new Door(4, 2, 0);
        dungeon.addEntity(door);

        // Test that Enemy can step on Key but then cannot open door
        enemy.moveRight();
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 2);

        enemy.moveRight();
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 2);

        // If we open door, enemy should be able to go through
        door.openDoor();
        enemy.moveRight();
        assertEquals(enemy.getX(), 4);
        assertEquals(enemy.getY(), 2);

    }

}