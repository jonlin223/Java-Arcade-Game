package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EndController {

    @FXML
    private Button returnButton;

    @FXML
    private Button restartButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    private String dungeonName;
    private SelectionScreen selectionScreen;
    private DungeonScreen dungeonScreen;

    @FXML
    void HandleReturnButton(ActionEvent event) throws IOException {
        selectionScreen.start();
        dungeonScreen.reset(dungeonName);
    }

    @FXML
    void handleRestartButton(ActionEvent event) throws IOException {
        dungeonScreen.restart(dungeonName);
    }

    public void setSelectionScreen(SelectionScreen selectionScreen) {
        this.selectionScreen = selectionScreen;
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public void setLabel1(String string) {
        label1.setText(string);
    }

    public void setLabel2(String string) {
        label2.setText(string);
    }

}