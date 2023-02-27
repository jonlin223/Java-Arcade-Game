package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unsw.dungeon.AndComposite;
import unsw.dungeon.Boulder;
import unsw.dungeon.BouldersGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.EnemyGoal;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.TreasureGoal;
import unsw.dungeon.Entity;
import unsw.dungeon.Exit;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Goal;
import unsw.dungeon.GoalController;
import unsw.dungeon.OrComposite;

public class GoalsTest {
    
    @Test
    public void playerExitSuccess() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);
        
        Exit exit = new Exit(2, 2);
        dungeon.addEntity(exit);
        ExitGoal exitGoal = new ExitGoal();
        GoalController goal = dungeon.getGoalController();
        goal.setGoal(exitGoal);
        //System.out.println(goal.getGoal());
        
        assertEquals(dungeon.isPlayerOnExit(), false);
        assertEquals(dungeon.isGoalCompleted(), false);
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        assertEquals(exit.getX(), 2);
        assertEquals(exit.getY(), 2);
        assertEquals(dungeon.getEntities().size(), 1);
        assertEquals(dungeon.isPlayerOnExit(), true);
        assertEquals(dungeon.isGoalCompleted(), true);
    }

    @Test
    public void playerKillAllSuccess() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 2);
        
        new Enemy(dungeon, 3, 2);
        new Enemy(dungeon, 4, 2);

        Sword sword = new Sword(1,2);
        dungeon.addEntity(sword);
        EnemyGoal enemyGoal = new EnemyGoal();
        GoalController goal = dungeon.getGoalController();
        goal.setGoal(enemyGoal);

        player.moveRight();
        player.moveRight();
        player.moveRight();

        assertEquals(dungeon.isGoalCompleted(), true);

    }

    @Test
    public void playerPushRockOnSwitchSuccess() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 2);

        //Boulder boulder = 
        new Boulder(dungeon, 2, 2);
        //Boulder boulder2 = 
        new Boulder(dungeon, 2, 3);
        
        FloorSwitch floorSwitch = new FloorSwitch(3,2);
        dungeon.addEntity(floorSwitch);
        FloorSwitch floorSwitch2 = new FloorSwitch(2, 4);
        dungeon.addEntity(floorSwitch2);

        BouldersGoal bouldersGoal = new BouldersGoal();
        GoalController goal = dungeon.getGoalController();
        goal.setGoal(bouldersGoal);

        player.moveRight();
        assertEquals(dungeon.isGoalCompleted(), false);
        player.moveRight();
        player.moveRight();
        assertEquals(dungeon.isGoalCompleted(), false);
        player.moveDown();

        assertEquals(floorSwitch.isActivated(), true);
        assertEquals(floorSwitch2.isActivated(), true);

        assertEquals(dungeon.isGoalCompleted(), true);
    }

    @Test
    public void treasureCollect() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);

        Treasure treasure1 = new Treasure(2, 2);
        dungeon.addEntity(treasure1);
        Treasure treasrue2 = new Treasure(3, 3);
        dungeon.addEntity(treasrue2);

        player.moveRight();
        player.moveDown();
        player.moveRight();

        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 3);
        ArrayList<Entity> collectibles = player.getCollectibles();
        assertEquals(collectibles.size(), 2);
        ArrayList<Entity> entities = dungeon.getEntities();
        assertEquals(entities.isEmpty(), true);

    }

    @Test
    public void playerCollectTreasureSuccess() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 1, 2);
        
        Treasure treasure1 = new Treasure(3, 2);
        dungeon.addEntity(treasure1);

        TreasureGoal treasureGoal = new TreasureGoal();
        GoalController goal = dungeon.getGoalController();
        goal.setGoal(treasureGoal);

        player.moveRight();
        player.moveRight();

        assertEquals(dungeon.isGoalCompleted(), true);

    }

    @Test
    public void testAndComposite() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        Exit exit = new Exit(1, 2);
        dungeon.addEntity(exit);

        new Boulder(dungeon, 3, 2);
        FloorSwitch floorSwitch = new FloorSwitch(4, 2);
        dungeon.addEntity(floorSwitch);

        AndComposite compositeGoal = new AndComposite();
        Goal exitGoal = new ExitGoal();
        Goal boulderGoal = new BouldersGoal();
        compositeGoal.addSubGoal(exitGoal);
        compositeGoal.addSubGoal(boulderGoal);

        GoalController goalController = new GoalController(dungeon);
        goalController.setGoal(compositeGoal);
        dungeon.setGoalController(goalController);

        player.moveRight();
        assertEquals(floorSwitch.isActivated(), true);
        assertEquals(dungeon.isGoalCompleted(), false);

        player.moveLeft();
        assertEquals(dungeon.isGoalCompleted(), true);

    }

    @Test
    public void testOrComposite() {

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);

        new Enemy(dungeon, 3, 2);
        Treasure treasure = new Treasure(1, 2);
        dungeon.addEntity(treasure);

        OrComposite compositeGoal = new OrComposite();
        Goal enemyGoal = new EnemyGoal();
        Goal treasureGoal = new TreasureGoal();
        compositeGoal.addSubGoal(enemyGoal);
        compositeGoal.addSubGoal(treasureGoal);
        GoalController goalController = new GoalController(dungeon);
        goalController.setGoal(compositeGoal);
        dungeon.setGoalController(goalController);

        player.moveLeft();
        assertEquals(dungeon.isGoalCompleted(), true);

    }

}