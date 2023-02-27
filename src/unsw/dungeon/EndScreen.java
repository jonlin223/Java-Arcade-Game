package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EndScreen {

    private EndController controller;
    private Scene scene;
    private Stage stage;
    private String title;
    
    public EndScreen(Stage stage) throws IOException {

        this.stage = stage;
        this.title = "End Screen";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EndView.fxml"));
        this.controller = new EndController();
        loader.setController(controller);

        Parent root = loader.load();
        this.scene = new Scene(root);

    }

    public EndController getController() {
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