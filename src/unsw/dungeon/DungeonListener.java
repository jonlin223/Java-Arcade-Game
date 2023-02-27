package unsw.dungeon;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DungeonListener {

    private DungeonController dungeonController;

    public DungeonListener(DungeonController dungeonController) {
        this.dungeonController = dungeonController;
        setGoalListener(this.dungeonController.getDungeon());
        setPlayerListener(dungeonController.getDungeon());
    }

    private void setGoalListener(Dungeon dungeon) {
        dungeon.goalCompleted().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue() == false) return;
                dungeonController.startVictoryScreen();
            }
        });
    }

    private void setPlayerListener(Dungeon dungeon) {
        dungeon.playerAlive().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue.booleanValue() == true) return;
                dungeonController.startDefeatScreen();
            }
            
        });
    }

}