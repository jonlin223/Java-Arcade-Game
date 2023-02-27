package unsw.dungeon;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class InformationController {
    
    private SelectionScreen selectionScreen;

    @FXML
    private Button returnButton;

    @FXML
    private GridPane table;

    @FXML
    public void handleReturnButton(ActionEvent event) {
        selectionScreen.start();
    }

    @FXML
    public void initialize() {

        Image enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        ImageView enemyView = new ImageView(enemyImage);
        table.add(enemyView, 0, 0);

        Image gnomeImage = new Image((new File("images/gnome.png")).toURI().toString());
        ImageView gnomeView = new ImageView(gnomeImage);
        table.add(gnomeView, 0, 1);

        Image houndImage = new Image((new File("images/hound.png")).toURI().toString());
        ImageView houndView = new ImageView(houndImage);
        table.add(houndView, 0, 2);

        Image invincibilityImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        ImageView invinsibilityView = new ImageView(invincibilityImage);
        table.add(invinsibilityView, 0, 3);

        Image invisibilityImage = new Image((new File("images/bubbly.png")).toURI().toString());
        ImageView invisibilityView = new ImageView(invisibilityImage);
        table.add(invisibilityView, 0, 4);

    }

    public void setSelectionScreen(SelectionScreen selectionScreen) {
        this.selectionScreen = selectionScreen;
    }

}