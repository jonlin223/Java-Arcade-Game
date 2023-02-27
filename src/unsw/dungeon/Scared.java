package unsw.dungeon;

import java.lang.Math;


public class Scared implements Behaviour {

    private int playerX;
    private int playerY;

    public Scared(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    @Override
    public void moveEnemy(Enemy enemy, int newPlayerX, int newPlayerY) {
        //first step is x-axis alignment:
        //we establish whether the player is to the right or left of the enemy, and the horizontal distance to the player
        
        //we find difference between player and enemy
        int enemyX = enemy.getX();
        int xDiff = playerX - enemyX;



        //next step is y-axis alignment:
        //we establish whether the player is above or below the enemy, and the vertical distance to the player
        int enemyY = enemy.getY();
        int yDiff = playerY - enemyY;


        //We then establish the direction with the bigger distance

        //if y diff is larger, we move away from that direction
        if ( Math.abs(yDiff) >= Math.abs(xDiff)) {
            //if player is below enemy, we try to move up
            if (yDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY - 1)) {
                    enemy.moveUp();
                    return;
                }
            }
            //if player is above, we move down
            else {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY + 1)) {
                    enemy.moveDown();
                    return;
                }
            }

            if (xDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX - 1, enemyY)) {
                    enemy.moveLeft();
                    return;
                }
            }
            else {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX + 1, enemyY)) {
                    enemy.moveRight();
                    return;
                }
            }
        }
        //if x diff is greater, or we cannot move up/down, we move laterally towards the enemy
        if (Math.abs(xDiff) >= Math.abs(yDiff)) {
            if (xDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX - 1, enemyY)) {
                    enemy.moveLeft();
                    return;
                }
            }
            else {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX + 1, enemyY)) {
                    enemy.moveRight();
                    return;
                }
            }

            if (yDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY - 1)) {
                    enemy.moveUp();
                    return;
                }
            }
            //if player is above, we move down
            else {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY + 1)) {
                    enemy.moveDown();
                    return;
                }
            }
        }
    }

}