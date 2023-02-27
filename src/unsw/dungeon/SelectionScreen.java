package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SelectionScreen {
    
    private SelectionController controller;
    private Scene scene;
    private Stage stage;
    private String title;
    private String title1;

    public SelectionScreen(Stage stage) throws IOException {

        this.stage = stage;
        this.title = "Selection Screen";
        this.title1 = "Level Select";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectionView.fxml"));
        this.controller = new SelectionController();
        loader.setController(controller);

        Pane root = loader.load();
        this.scene = new Scene(root);

    }

    public void start() {
        stage.setTitle(title);
        stage.setTitle(title1);
        stage.setScene(scene);
        //stage.setMaximized(false);
        //stage.setMaximized(true);
        controller.playTimeline();
        stage.show();
    }

    public SelectionController getController() {
        return controller;
    }

}