package unsw.dungeon;

import java.lang.Math;

public class Aggressive implements Behaviour {

    private int playerX;
    private int playerY;

    public Aggressive(int playerX, int playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    //This is where our observers terminate for our observer pattern (???)
    //the subject (player) moves
    //this notifies our observer (dungeon)
    //dungeon passes the notification on to each enemy by telling them to move the enemy, according to the new information given
    @Override
    public void moveEnemy(Enemy enemy, int newPlayerX, int newPlayerY) {

        this.playerX = newPlayerX;
        this.playerY = newPlayerY;

        //plan for AI

            //first stage is basic AI: follow the leader AI

            //the way this works is that we have a two step AI logic:

            //1: we move towards the x co-ordinate of the player

            //2: OR we move towards the y co-ordinate of the player

            //we decide which one to do based on:
                //which one is possible (up against a wall)
                //which one is further from plater (if our horizontal distance to player is greater than vertical distance, and both directions are possible to move in, we choose horizontal)



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

        //if y diff is larger, we move in that direction
        if ( Math.abs(yDiff) >= Math.abs(xDiff)) {
            //if player is above enemy, we try to move up
            if (yDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY + 1)) {
                    enemy.moveDown();
                    return;
                }
            }
            //if player is below, we move down
            else {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY - 1)) {
                    enemy.moveUp();
                    return;
                }
            }
            
            if (xDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX + 1, enemyY)) {
                    enemy.moveRight();
                    return;
                }
            }
            else {
                if (processMove(enemy.getDungeon(), enemy, enemyX - 1, enemyY)) {
                    enemy.moveLeft();
                    return;
                }
            }
        }
        //if x diff is greater, or we cannot move up/down, we move laterally towards the enemy
        if (Math.abs(xDiff) >= Math.abs(yDiff)) {
            if (xDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX + 1, enemyY)) {
                    enemy.moveRight();
                    return;
                }
            }
            else {
                if (processMove(enemy.getDungeon(), enemy, enemyX - 1, enemyY)) {
                    enemy.moveLeft();
                    return;
                }
            }

            if (yDiff > 0) {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY + 1)) {
                    enemy.moveDown();
                    return;
                }
            }
            //if player is below, we move down
            else {
                if (enemy.processMove(enemy.getDungeon(), enemy, enemyX, enemyY - 1)) {
                    enemy.moveUp();
                    return;
                }
            }
        }
        //if they are the same difference, we attempt to move laterally first, then vertically.

        //if both fail, we sit in shame and do nothing



        //Second stage is more advanced, 'pathfinding AI'
        //implemented in milestone 3 maybe?
        //keeps a track of where the enemy has been, and moves in order to find a new path to the player
    }

    public boolean processMove(Dungeon dungeon, Enemy enemy, int x, int y) {
        return enemy.processMove(dungeon, enemy, x, y);
    }

}