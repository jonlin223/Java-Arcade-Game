package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;

public class EnemyTest {
    
    @Test
    public void testPlayerCollision() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Enemy enemy = new Enemy(dungeon, 3, 2);

        player.moveRight();
        assertEquals(dungeon.getPlayer(), null);
        assertEquals(enemy.getX(), 3);

    }

    @Test
    public void testEnemyCollision() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 2);

        Enemy enemy = new Enemy(dungeon, 3, 2);
        Enemy enemy2 = new Enemy(dungeon, 4, 2);

        Wall wall1 = new Wall(1, 0);
        dungeon.addEntity(wall1);
        Wall wall2 = new Wall(1, 1);
        dungeon.addEntity(wall2);
        Wall wall3 = new Wall(1, 2);
        dungeon.addEntity(wall3);
        Wall wall4 = new Wall(1, 3);
        dungeon.addEntity(wall4);
        Wall wall5 = new Wall(1, 4);
        dungeon.addEntity(wall5);

        player.moveLeft();
        player.moveLeft();

        assertEquals(enemy.getX(), 2);
        assertEquals(enemy2.getX(), 3);


    }

    @Test
    public void testEnemyMisc() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 2);

        FloorSwitch floorSwitch = new FloorSwitch(3, 2);
        dungeon.addEntity(floorSwitch);
        Treasure treasure = new Treasure(2, 2);
        dungeon.addEntity(treasure);
        new Boulder(dungeon, 1, 2);

        Enemy enemy = new Enemy(dungeon, 4, 2);

        player.moveLeft();
        player.moveLeft();
        player.moveLeft();

        assertEquals(enemy.getX(), 2);
        

    }
    @Test

    public void testEnemyMoveRight() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 2);

        Enemy enemy = new Enemy(dungeon, 1, 2);

        player.moveLeft();
        assertEquals(enemy.getX(), 2);
        assertEquals(dungeon.getPlayer(), null);
    }

    @Test

    public void testEnemyMoveLeft() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);

        Enemy enemy = new Enemy(dungeon, 3, 2);

        player.moveRight();
        assertEquals(enemy.getX(), 2);
        assertEquals(dungeon.getPlayer(), null);
    }

    @Test

    public void testEnemyMoveDown() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 4);

        Enemy enemy = new Enemy(dungeon, 2, 2);

        player.moveUp();
        assertEquals(enemy.getY(), 3);
        assertEquals(dungeon.getPlayer(), null);
    }

    @Test

    public void testEnemyMoveUp() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Enemy enemy = new Enemy(dungeon, 2, 4);

        player.moveDown();
        assertEquals(enemy.getY(), 3);
        assertEquals(dungeon.getPlayer(), null);
    }

    @Test

    public void testEnemyMoveDiagonal() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 0);

        Enemy enemy = new Enemy(dungeon, 4,4);

        for (int i = 0; i < 8; i++) {
            player.moveUp();
        }

        assertEquals(enemy.getY(), 0);
        assertEquals(enemy.getX(), 0);
        assertEquals(dungeon.getPlayer(), null);

    }

    @Test

    public void testEnemyFleeRight() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 2);

        Enemy enemy = new Enemy(dungeon, 2, 2);
        enemy.getScared();
        player.moveRight();
        player.moveLeft();

        assertEquals(enemy.getX(), 4);
        assertEquals(enemy.getY(), 2);
    }

    @Test
    public void testEnemyFleeLeft() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 4, 2);

        Enemy enemy = new Enemy(dungeon, 2, 2);
        enemy.getScared();
        player.moveLeft();
        player.moveRight();

        assertEquals(enemy.getX(), 0);
        assertEquals(enemy.getY(), 2);
    }

    @Test
    public void testEnemyFleeUp() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 4);

        Enemy enemy = new Enemy(dungeon, 2, 2);
        enemy.getScared();
        player.moveUp();
        player.moveDown();

        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 0);
    }

    @Test
    public void testEnemyFleeDown() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 0);

        Enemy enemy = new Enemy(dungeon, 2, 2);
        enemy.getScared();
        player.moveDown();
        player.moveUp();

        assertEquals(enemy.getX(), 2);
        assertEquals(enemy.getY(), 4);
    }

    @Test
    public void testEnemyBlocked() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 0);

        Enemy enemy = new Enemy(dungeon, 3, 3);

        Wall wall1 = new Wall(3,2);
        Wall wall2 = new Wall(2,3);
        Wall wall3 = new Wall(2,2);
        dungeon.addEntity(wall1);
        dungeon.addEntity(wall2);
        dungeon.addEntity(wall3);

        player.moveDown();
        player.moveRight();
        player.moveUp();

        assertEquals(enemy.getX(), 3);
        assertEquals(enemy.getY(), 3);


    }
}