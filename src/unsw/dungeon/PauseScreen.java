package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PauseScreen {

    private PauseController controller;
    private Scene scene;
    private Stage stage;
    private String title;

    public PauseScreen(Stage stage) throws IOException {

        this.stage = stage;
        this.title = "Pause Screen";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseView.fxml"));
        this.controller = new PauseController();
        loader.setController(controller);

        Parent root = loader.load();
        this.scene = new Scene(root);

    }

    public PauseController getController() {
        return controller;
    }

    public void start(String dungeonName) {
        controller.setDungeonName(dungeonName);
        stage.setTitle(title);
        stage.setScene(scene);
        //stage.setMaximized(false);
        //stage.setMaximized(true);
        stage.show();
    }

}