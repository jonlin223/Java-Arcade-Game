package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Player;

public class FloorSwitchTest {
    
    @Test
    public void testPlayerEnemy() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(dungeon, 0, 4);

        FloorSwitch f1 = new FloorSwitch(1, 0);
        FloorSwitch f2 = new FloorSwitch(1, 4);

        enemy.moveRight();
        assertEquals(enemy.getX(), f2.getX());
        assertEquals(enemy.getY(), f2.getY());
        assertEquals(f2.isActivated(), false);

        player.moveRight();
        assertEquals(player.getX(), f1.getX());
        assertEquals(player.getY(), f1.getY());
        assertEquals(f1.isActivated(), false);

    }

    @Test
    public void testSwitch() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 2);

        Boulder boulder = new Boulder(dungeon, 1, 2);

        FloorSwitch f1 = new FloorSwitch(2, 2);
        dungeon.addEntity(f1);
        FloorSwitch f2 = new FloorSwitch(3, 2);
        dungeon.addEntity(f2);

        // Case where we move from non-switch to switch
        player.moveRight();
        assertEquals(boulder.getX(), f1.getX());
        assertEquals(boulder.getY(), f1.getY());
        assertEquals(f1.isActivated(), true);

        // Case where we move from switch to switch
        player.moveRight();
        player.moveRight();
        assertEquals(boulder.getX(), f2.getX());
        assertEquals(boulder.getY(), f2.getY());
        assertEquals(f1.isActivated(), false);
        assertEquals(f2.isActivated(), true);

        // Case where we move switch to non-switch
        player.moveRight();
        player.moveRight();
        assertEquals(boulder.getX(), 4);
        assertEquals(boulder.getY(), 2);
        assertEquals(f1.isActivated(), false);
        assertEquals(f2.isActivated(), false);
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);

    }

}