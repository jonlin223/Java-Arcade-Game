package unsw.dungeon;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ExplosionAnimation {
    
    private DungeonController dungeonController;

    public ExplosionAnimation(DungeonController dungeonController) {
        this.dungeonController = dungeonController;
        Dungeon dungeon = dungeonController.getDungeon();
        for (Entity entity : dungeon.getEntities()) {
            if (entity instanceof Explosive) {
                setExplosiveListener(entity);
            }
        }
    }

    private void setExplosiveListener(Entity entity) {
        Explosive explosive = (Explosive) entity;
        explosive.detonated().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue()) {
                    detonateTiles(entity.getX(), entity.getY(), explosive.getRadius());
                }
            }  
        });
    }

    private void detonateTiles(int x, int y, int radius) {

        int leftBound = x - radius;
        int rightBound = x + radius;
        int upperBound = y - radius;
        int lowerBound = y + radius;

        Dungeon dungeon = dungeonController.getDungeon();

        for (int i = upperBound; i <= lowerBound; i++) {
            for (int j = leftBound; j <= rightBound; j++) {
                if (i >= 0 && i < dungeon.getHeight() && j >= 0 && j < dungeon.getWidth()) {
                    playExplosionAnimation(j, i);
                }
            }
        }

    }

    private void playExplosionAnimation(int x, int y) {

        Image explosionImage = new Image((new File("images/Bakuretsa.png")).toURI().toString());
        ImageView view = new ImageView(explosionImage);

        EventHandler<ActionEvent> showExplosion = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                dungeonController.getSquares().add(view, x, y);
            }
        };
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), showExplosion);

        EventHandler<ActionEvent> hideExplosion = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                view.setVisible(false);
            }
        };
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.3), hideExplosion);

        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        timeline.setCycleCount(1);
        timeline.play();

    }

}