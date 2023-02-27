package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class MineTurtle extends Entity implements Explosive {
    
    private int blastRadius;
    private BooleanProperty detonated;

    public MineTurtle(int x, int y) {
        super(x,y);
        this.blastRadius = 3;
        this.detonated = new SimpleBooleanProperty(false);
    }

    @Override
    public int getRadius() {
        return blastRadius;
    }

    @Override
    public void detonate(Dungeon dungeon) {
        // Find dungeon bounds
        int leftBound = getX() - blastRadius;
        if (leftBound < 0) {
            leftBound = 0;
        }
        int rightBound = getX() + blastRadius;
        if (rightBound > dungeon.getWidth() - 1) {
            rightBound = dungeon.getWidth() - 1;
        }
        int upperBound = getY() - blastRadius;
        if (upperBound < 0) {
            upperBound = 0;
        }
        int lowerBound = getY() + blastRadius;
        if (lowerBound > dungeon.getHeight() - 1) {
            lowerBound = dungeon.getHeight() - 1;
        }

        // Remove this entity from the dungeon
        dungeon.removeEntity(this);
        setDetonated(true);

        // Check tiles and detonate them
        Player player = dungeon.getPlayer();
        for (int i = leftBound; i <= rightBound; i++) {
            for (int j = upperBound; j <= lowerBound; j++) {
                if (player != null && player.getX() == i && player.getY() == j) {
                    if (!(player.getPotion() instanceof InvincibilityPotion)) {
                        dungeon.removePlayer();
                    }
                    return;
                }
                Enemy stooge = dungeon.findEnemy(i,j);
                if (stooge != null) {
                    dungeon.killEnemy(stooge);
                }
            }
        }

    }

    @Override
    public BooleanProperty detonated() {
        return detonated;
    }

    public void setDetonated(boolean detonated) {
        this.detonated.set(detonated);
    }

}