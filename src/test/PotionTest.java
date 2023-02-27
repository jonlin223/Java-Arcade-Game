package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Behaviour;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.InvisibilityPotion;
import unsw.dungeon.Player;
import unsw.dungeon.Scared;

public class PotionTest {
    
    @Test
    public void testPickup() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        InvincibilityPotion potion1 = new InvincibilityPotion(3, 2);
        dungeon.addEntity(potion1);
        assertEquals(dungeon.getEntities().size(), 1);

        player.moveRight();
        assertEquals(player.hasPotion(), true);
        assertEquals(dungeon.getEntities().size(), 0);
        assertEquals(player.getPotionDuration(), 10);

        InvincibilityPotion potion2 = new InvincibilityPotion(4, 2);
        dungeon.addEntity(potion2);
        assertEquals(dungeon.getEntities().size(), 1);

        player.moveRight();
        assertEquals(player.hasPotion(), true);
        assertEquals(dungeon.getEntities().size(), 1);
        assertEquals(player.getPotionDuration(), 9);

    }

    @Test
    public void testPickupEnemy() {

        Dungeon dungeon = new Dungeon(5, 5);
        new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(dungeon, 2, 2);

        InvincibilityPotion potion = new InvincibilityPotion(3, 2);
        dungeon.addEntity(potion);
        assertEquals(dungeon.getEntities().size(), 1);

        enemy.moveRight();
        assertEquals(dungeon.getEntities().size(), 1);

    }

    @Test
    public void testInvincibility() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);

        InvincibilityPotion potion = new InvincibilityPotion(2, 2);
        dungeon.addEntity(potion);

        player.moveRight();

        new Enemy(dungeon, 3, 2);

        player.moveRight();
        assertEquals(dungeon.getMoveableEntities().size(), 1);
        assertEquals(dungeon.getPlayer(), player);

    }

    @Test
    public void testDuration() {

        Dungeon dungeon = new Dungeon(20, 20);
        Player player = new Player(dungeon, 1, 0);

        InvincibilityPotion potion = new InvincibilityPotion(0, 0);
        dungeon.addEntity(potion);
        
        new Enemy(dungeon, 18, 18);

        player.moveLeft();
        assertEquals(player.hasPotion(), true);

        for (int i = 0; i <= 10; i++) {
            player.moveLeft();
        }

        assertEquals(player.hasPotion(), false);

    }

    @Test
    public void testSubmissiveEnemy() {
        Dungeon dungeon = new Dungeon(10,10);
        Player player = new Player(dungeon, 0, 2);

        Enemy enemy = new Enemy(dungeon, 4, 2);
        
        InvincibilityPotion potion = new InvincibilityPotion(2,2);
        dungeon.addEntity(potion);
        
        player.moveRight();

        assertEquals(enemy.getX(), 3);
        player.moveRight();
        assertEquals(player.hasPotion(), true);
        Behaviour state = enemy.getBehaviour();
        assert(state.getClass().equals(Scared.class));

        assertEquals(enemy.getX(), 4);
        player.moveDown();
        assertEquals(enemy.getX(), 5);
    }

    @Test
    public void testInvisibility() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Enemy enemy = new Enemy(dungeon, 3, 2);
        InvisibilityPotion potion = new InvisibilityPotion(1, 2);
        dungeon.addEntity(potion);

        player.moveLeft();
        assertEquals(player.getPotion().getClass(), InvisibilityPotion.class);

        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 2);

        player.moveRight();
        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 2);
        assertEquals(player.getPotionDuration(), 9);

        for (int i = 0; i < 9; i++) {
            player.moveLeft();
        }

        assertEquals(player.hasPotion(), false);
        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 2);

    }

}