package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private VBox background;

    @FXML
    private GridPane squares;

    @FXML
    private GridPane inventory;

    @FXML
    private Label weaponInfo;

    @FXML
    private Label potionInfo;

    @FXML
    private Label keyInfo;

    @FXML
    private Label goalString;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private String dungeonName;

    private DungeonScreen dungeonScreen;
    private EndScreen endScreen;
    private PauseScreen pauseScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        new DungeonListener(this);
    }

    public GridPane getSquares() {
        return squares;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Player getPlayer() {
        return dungeon.getPlayer();
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public DungeonScreen getDungeonScreen() {
        return dungeonScreen;
    }

    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeonScreen = dungeonScreen;
    }

    public EndScreen getEndScreen() {
        return endScreen;
    }

    public void setEndScreen(EndScreen endScreen) {
        this.endScreen = endScreen;
    }

    public PauseScreen getPauseScreen() {
        return pauseScreen;
    }

    public void setPauseScreen(PauseScreen pauseScreen) {
        this.pauseScreen = pauseScreen;
    }

    public GridPane getInventory() {
        return inventory;
    }

    public Label getWeaponInfo() {
        return weaponInfo;
    }

    public Label getPotionInfo() {
        return potionInfo;
    }

    public Label getKeyInfo() {
        return keyInfo;
    }

    @FXML
    public void initialize() {

        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        background.setPrefWidth(dungeon.getWidth() * 32);
        background.setPrefHeight((dungeon.getHeight() + 3) * 32);

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                ImageView view = new ImageView(ground);
                squares.add(view, x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        new InventoryManager(this);
        new ExplosionAnimation(this);

        goalString.setText(dungeon.createGoalString());
        goalString.setAlignment(Pos.CENTER);
        goalString.setPrefWidth(dungeon.getWidth() * 32);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            case ESCAPE:
                startPauseScreen();
            default:
                break;
        }
    }

    public void startVictoryScreen() {
        endScreen.getController().setDungeonName(dungeonName);
        endScreen.getController().setDungeonScreen(dungeonScreen);
        endScreen.getController().setLabel1("Congratulations");
        endScreen.getController().setLabel2("You're Winner!");
        endScreen.start(dungeonName);
    }

    public void startDefeatScreen() {
        endScreen.getController().setDungeonName(dungeonName);
        endScreen.getController().setDungeonScreen(dungeonScreen);
        endScreen.getController().setLabel1("You Lost.");
        endScreen.getController().setLabel2("Snake? Snake?!? SNAKE!!! :(");
        endScreen.start(dungeonName);
    }

    public void startPauseScreen() {
        pauseScreen.getController().setDungeonName(dungeonName);
        pauseScreen.getController().setDungeonScreen(dungeonScreen);
        pauseScreen.start(dungeonName);
    }

}

