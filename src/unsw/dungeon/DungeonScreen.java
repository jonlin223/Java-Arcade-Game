package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
    
    private DungeonController controller;
    private Scene scene;
    private Stage stage;
    private String title;

    public DungeonScreen(Stage stage, String filename) throws IOException {

        this.stage = stage;
        this.title = "Dungeon Screen";

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(filename);
        this.controller = dungeonLoader.loadController();
        this.controller.setDungeonName(filename);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        this.scene = new Scene(root);
        root.requestFocus();

    }

    public DungeonController getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }

    public void start() {

        stage.setTitle(title);
        stage.setScene(scene);
        //stage.setMaximized(false);
        //stage.setMaximized(true);
        stage.show();

    }

    public void reset(String filename) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(filename);
        DungeonController newController = dungeonLoader.loadController();
        newController.setEndScreen(controller.getEndScreen());
        newController.setPauseScreen(controller.getPauseScreen());
        newController.setDungeonScreen(this);
        this.controller = newController;
        this.controller.setDungeonName(filename);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        this.scene = new Scene(root);
        root.requestFocus();
    }

    public void restart(String filename) throws IOException {
        reset(filename);
        start();
    }

}