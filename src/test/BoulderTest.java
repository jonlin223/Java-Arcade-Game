package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;

//Testing for boulders
public class BoulderTest {
    
    @Test
    public void testBoulderPush() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Boulder b1 = new Boulder(dungeon, 2, 1);
        Boulder b2 = new Boulder(dungeon, 2, 3);
        Boulder b3 = new Boulder(dungeon, 1, 2);
        Boulder b4 = new Boulder(dungeon, 3, 2);

        player.moveUp();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        assertEquals(b1.getX(), 2);
        assertEquals(b1.getY(), 0);

        player.moveDown();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        assertEquals(b2.getX(), 2);
        assertEquals(b2.getY(), 4);

        player.moveLeft();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        assertEquals(b3.getX(), 0);
        assertEquals(b3.getY(), 2);

        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        assertEquals(b4.getX(), 4);
        assertEquals(b4.getY(), 2);

    }

    @Test
    public void testEnemyBoulder() {

        Dungeon dungeon = new Dungeon(5, 5);
        new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(dungeon, 2, 2);

        new Boulder(dungeon, 3, 2);
        enemy.moveRight();

        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 2);

    }

    @Test
    public void testBoulderDoor() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);

        Boulder boulder = new Boulder(dungeon, 2, 2);
        Door door = new Door(3, 2, 0);
        dungeon.addEntity(door);

        // Boulder can't move because door is closed
        player.moveRight();
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 2);

        // Boulder can move because door is opened
        door.openDoor();
        player.moveRight();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 2);

    }

}