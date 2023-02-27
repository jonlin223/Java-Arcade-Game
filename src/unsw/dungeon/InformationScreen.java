package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InformationScreen {
    
    private InformationController controller;
    private Scene scene;
    private Stage stage;
    private String title;

    public InformationScreen(Stage stage) throws IOException {

        this.stage = stage;
        this.title = "Information Screen";

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InformationView.fxml"));
        this.controller = new InformationController();
        loader.setController(controller);

        Parent root = loader.load();
        this.scene = new Scene(root);

    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public InformationController getController() {
        return controller;
    }

}