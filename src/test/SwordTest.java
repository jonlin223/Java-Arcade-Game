package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

public class SwordTest {
    
    @Test
    public void testPickUpSword() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Sword sword = new Sword(3, 2);
        dungeon.addEntity(sword);
        assertEquals(dungeon.getEntities().size(), 1);

        assertEquals(player.hasWeapon(), false);

        // Check that the Sword has been picked up
        player.moveRight();
        assertEquals(player.hasWeapon(), true);
        assertEquals(dungeon.getEntities().size(), 0);

    }

    @Test
    public void testKillEnemy() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        new Enemy(dungeon, 3, 2);
        assertEquals(dungeon.getMoveableEntities().size(), 2);
        assertEquals(dungeon.getPlayerObserverList().size(), 1);

        Sword sword = new Sword(0, 0);
        dungeon.addEntity(sword);

        player.addWeapon(sword);
        player.setPlayerState(player.getStrongState());

        // Check player can move onto enemy
        player.moveRight();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);

        // Check that there are now no enemies
        assertEquals(dungeon.getMoveableEntities().size(), 1);
        assertEquals(dungeon.getPlayerObserverList().size(), 0);

        // Check that sword lost durability
        assertEquals(player.getWeaponDurability(), 4);
        
    }

    @Test
    public void testKill5Enemies() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 1);

        Sword sword = new Sword(2, 1);
        dungeon.addEntity(sword);

        player.moveRight();

        new Enemy(dungeon, 3, 1);
        assertEquals(dungeon.getMoveableEntities().size(), 2);
        assertEquals(dungeon.getPlayerObserverList().size(), 1);

        player.moveRight();
        assertEquals(dungeon.getMoveableEntities().size(), 1);
        assertEquals(dungeon.getPlayerObserverList().size(), 0);

        new Enemy(dungeon, 4, 1);
        assertEquals(dungeon.getMoveableEntities().size(), 2);
        assertEquals(dungeon.getPlayerObserverList().size(), 1);

        player.moveRight();
        assertEquals(dungeon.getMoveableEntities().size(), 1);
        assertEquals(dungeon.getPlayerObserverList().size(), 0);

        new Enemy(dungeon, 4, 2);
        assertEquals(dungeon.getMoveableEntities().size(), 2);
        assertEquals(dungeon.getPlayerObserverList().size(), 1);

        player.moveDown();
        assertEquals(dungeon.getMoveableEntities().size(), 1);
        assertEquals(dungeon.getPlayerObserverList().size(), 0);

        new Enemy(dungeon, 4, 3);
        assertEquals(dungeon.getMoveableEntities().size(), 2);
        assertEquals(dungeon.getPlayerObserverList().size(), 1);

        player.moveDown();
        assertEquals(dungeon.getMoveableEntities().size(), 1);
        assertEquals(dungeon.getPlayerObserverList().size(), 0);

        assertEquals(player.hasWeapon(), true);

        new Enemy(dungeon, 4, 4);
        assertEquals(dungeon.getMoveableEntities().size(), 2);
        assertEquals(dungeon.getPlayerObserverList().size(), 1);

        player.moveDown();
        assertEquals(dungeon.getMoveableEntities().size(), 1);
        assertEquals(dungeon.getPlayerObserverList().size(), 0);

        // Check that the sword is now broken
        assertEquals(player.hasWeapon(), false);

    }

    @Test
    public void testEnemySword() {

        Dungeon dungeon = new Dungeon(5, 5);
        new Player(dungeon, 0, 0);

        Enemy enemy = new Enemy(dungeon, 2, 2);

        Sword sword = new Sword(3, 2);
        dungeon.addEntity(sword);
        assertEquals(dungeon.getEntities().size(), 1);

        enemy.moveRight();
        assertEquals(dungeon.getEntities().size(), 1);

    }

}