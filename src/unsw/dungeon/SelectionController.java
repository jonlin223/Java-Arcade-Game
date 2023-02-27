package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SelectionController {

    @FXML
    private Button selectAdvanced;

    @FXML
    private Button selectBoulders;

    @FXML
    private Button selectMaze;

    @FXML
    private Button selectPortals;

    @FXML
    private Button selectInfo;

    @FXML
    private Button exitButton;

    @FXML
    private Label title;

    @FXML
    private Label title1;

    @FXML
    private Pane background;

    private DungeonScreen advancedScreen;
    private DungeonScreen bouldersScreen;
    private DungeonScreen mazeScreen;
    private DungeonScreen portalsScreen;
    private InformationScreen informationScreen;

    private Timeline timeline;

    public SelectionController() {

        EventHandler<ActionEvent> turnGreen = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t) {
                title.setTextFill(Color.web("#6af620"));
                title1.setTextFill(Color.web("#6af620"));
            }
        };
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100), turnGreen);

        EventHandler<ActionEvent> turnPink = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t) {
                title.setTextFill(Color.web("#ff00ab"));
                title1.setTextFill(Color.web("#ff00ab"));

            }
        };
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), turnPink);

        this.timeline = new Timeline(keyFrame1, keyFrame2);
        timeline.setCycleCount(-1);

    }

    @FXML
    public void handleSelectAdvanced(ActionEvent event) throws IOException {
        advancedScreen.start();
    }

    @FXML
    public void handleSelectBoulders(ActionEvent event) throws IOException {
        bouldersScreen.start();
    }

    @FXML
    public void handleSelectMaze(ActionEvent event) throws IOException {
        mazeScreen.start();
    }

    @FXML
    public void handleSelectPortals(ActionEvent event) throws IOException {
        portalsScreen.start();
    }

    @FXML
    public void handleSelectInfo(ActionEvent event) throws IOException {
        informationScreen.start();
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        mazeScreen.getStage().close();
    }

    @FXML
    public void initialize() {

        Image playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        ImageView player = new ImageView(playerImage);
        player.setFitWidth(61);
        player.setFitHeight(61);
        player.setX(72);
        player.setY(90);

        Image enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        ImageView enemy = new ImageView(enemyImage);
        enemy.setFitWidth(61);
        enemy.setFitHeight(61);
        enemy.setX(369);
        enemy.setY(90);

        Image turtleImage = new Image((new File("images/MineTurtle.png")).toURI().toString());
        ImageView turtle = new ImageView(turtleImage);
        turtle.setFitWidth(96);
        turtle.setFitHeight(54);
        turtle.setX(200);
        turtle.setY(90);

        background.getChildren().addAll(player, enemy, turtle);

    }

    public Timeline getTimeline() {
        return timeline;
    }

    public DungeonScreen getBoulderScreen() {
        return bouldersScreen;
    }

    public DungeonScreen getAdvancedScreen() {
        return advancedScreen;
    }

    public DungeonScreen getMazeScreen() {
        return mazeScreen;
    }

    public DungeonScreen getPortalsScreen() {
        return portalsScreen;
    }

    public void setAdvancedScreen(DungeonScreen dungeonScreen) {
        this.advancedScreen = dungeonScreen;
    }

    public void setBouldersScreen(DungeonScreen dungeonScreen) {
        this.bouldersScreen = dungeonScreen;
    }

    public void setMazeScreen(DungeonScreen dungeonScreen) {
        this.mazeScreen = dungeonScreen;
    }

    public void setPortalsScreen(DungeonScreen portalsScreen) {
        this.portalsScreen = portalsScreen;
    }

    public void setInformationScreen(InformationScreen informationScreen) {
        this.informationScreen = informationScreen;
    }

    public void playTimeline() {
        timeline.play();
    }

}
