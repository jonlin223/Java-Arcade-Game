package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseController {
 
    @FXML
    private Button resumeButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button restartButton;

    private String dungeonName;
    private SelectionScreen selectionScreen;
    private DungeonScreen dungeonScreen;

    @FXML
    void handleRestartButton(ActionEvent event) throws IOException {
        dungeonScreen.restart(dungeonName);
    }

    @FXML
    void handleResumeButton(ActionEvent event) {
        dungeonScreen.start();
    }

    @FXML
    void handleReturnButton(ActionEvent event) {
        selectionScreen.start();
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public void setSelectionScreen(SelectionScreen selectionScreen) {
        this.selectionScreen = selectionScreen;
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

}