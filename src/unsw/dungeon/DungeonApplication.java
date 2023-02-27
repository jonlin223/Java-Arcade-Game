package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        DungeonScreen advancedScreen = new DungeonScreen(primaryStage, "advanced.json");
        DungeonScreen bouldersScreen = new DungeonScreen(primaryStage, "boulders.json");
        DungeonScreen mazeScreen = new DungeonScreen(primaryStage, "maze.json");
        DungeonScreen portalsScreen = new DungeonScreen(primaryStage, "portals.json");

        SelectionScreen selectionScreen = new SelectionScreen(primaryStage);
        EndScreen endScreen = new EndScreen(primaryStage);
        PauseScreen pauseScreen = new PauseScreen(primaryStage);
        InformationScreen informationScreen = new InformationScreen(primaryStage);

        advancedScreen.getController().setEndScreen(endScreen);
        bouldersScreen.getController().setEndScreen(endScreen);
        mazeScreen.getController().setEndScreen(endScreen);
        portalsScreen.getController().setEndScreen(endScreen);

        advancedScreen.getController().setPauseScreen(pauseScreen);
        bouldersScreen.getController().setPauseScreen(pauseScreen);
        mazeScreen.getController().setPauseScreen(pauseScreen);
        portalsScreen.getController().setPauseScreen(pauseScreen);

        advancedScreen.getController().setDungeonScreen(advancedScreen);
        bouldersScreen.getController().setDungeonScreen(bouldersScreen);
        mazeScreen.getController().setDungeonScreen(mazeScreen);
        portalsScreen.getController().setDungeonScreen(portalsScreen);

        endScreen.getController().setSelectionScreen(selectionScreen);
        pauseScreen.getController().setSelectionScreen(selectionScreen);

        selectionScreen.getController().setAdvancedScreen(advancedScreen);
        selectionScreen.getController().setBouldersScreen(bouldersScreen);
        selectionScreen.getController().setMazeScreen(mazeScreen);
        selectionScreen.getController().setPortalsScreen(portalsScreen);
        selectionScreen.getController().setInformationScreen(informationScreen);

        informationScreen.getController().setSelectionScreen(selectionScreen);

        selectionScreen.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
