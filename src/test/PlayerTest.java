package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class PlayerTest {
    
    @Test
    public void testMovement() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 3);

        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);

        player.moveUp();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);

        player.moveDown();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);

        player.moveRight();
        assertEquals(player.getX(), 4);
        assertEquals(player.getY(), 3);

        player.moveLeft();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);

    }

    @Test
    public void testBlockedbyMapEdge() {

        Dungeon dungeon = new Dungeon(1, 1);
        Player player = new Player(dungeon, 0, 0);

        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        player.moveUp();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        player.moveDown();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        player.moveLeft();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

        player.moveRight();
        assertEquals(player.getX(), 0);
        assertEquals(player.getY(), 0);

    }

    @Test
    public void testBlockedByWall() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 3);

        Wall w1 = new Wall(4, 3);
        Wall w2 = new Wall(3, 4);
        Wall w3 = new Wall(2, 3);
        Wall w4 = new Wall(3, 2);

        dungeon.addEntity(w1);
        dungeon.addEntity(w2);
        dungeon.addEntity(w3);
        dungeon.addEntity(w4);

        player.moveUp();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);

        player.moveDown();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);

        player.moveRight();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);

        player.moveLeft();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);

    }

}