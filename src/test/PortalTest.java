package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;

public class PortalTest {
    
    @Test
    public void testBasicPortal() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Portal p1 = new Portal(dungeon, 3, 2, 0);
        dungeon.addEntity(p1);

        Portal p2 = new Portal(dungeon, 1, 2, 0);
        dungeon.addEntity(p2);

        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);

        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);

    }

    @Test
    public void testEnemyPortal() {

        Dungeon dungeon = new Dungeon(5, 5);
        new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(dungeon, 2, 2);

        Portal p1 = new Portal(dungeon, 3, 2, 0);
        dungeon.addEntity(p1);

        Portal p2 = new Portal(dungeon, 1, 2, 0);
        dungeon.addEntity(p2);

        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 2);

        enemy.moveRight();
        assertEquals(enemy.getX(), 1);
        assertEquals(enemy.getY(), 2);

    }

    @Test
    public void testPortalKill() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);
        Enemy enemy = new Enemy(dungeon, 2, 2);

        Portal p1 = new Portal(dungeon, 3, 2, 0);
        dungeon.addEntity(p1);

        Portal p2 = new Portal(dungeon, 1, 2, 0);
        dungeon.addEntity(p2);

        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);

        enemy.moveRight();
        assertEquals(dungeon.getPlayer(), null);

    }

}