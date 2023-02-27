package unsw.dungeon;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InventoryManager {

    private DungeonController dungeonController;
    private Image swordImage;
    private Image invincibilityImage;
    private Image invisibilityImage;
    private Image keyImage;

    public InventoryManager(DungeonController dungeonController) {
        this.dungeonController = dungeonController;
        this.swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        this.invincibilityImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        this.invisibilityImage = new Image((new File("images/bubbly.png")).toURI().toString());
        this.keyImage = new Image((new File("images/key.png")).toURI().toString());
        initialiseInventory();
    }

    private void initialiseInventory() {

        ImageView weaponView = new ImageView(swordImage);
        ImageView potionView = new ImageView(invincibilityImage);
        ImageView keyView = new ImageView(keyImage);

        weaponView.setVisible(false);
        potionView.setVisible(false);
        keyView.setVisible(false);

        dungeonController.getInventory().add(weaponView, 0, 0);
        dungeonController.getInventory().add(potionView, 1, 0);
        dungeonController.getInventory().add(keyView, 2, 0);

        setSwordListener(dungeonController.getPlayer(), weaponView);
        setKeyListener(dungeonController.getPlayer(), keyView);
        setPotionListener(dungeonController.getPlayer(), potionView);

    }

    private void setSwordListener(Player player, ImageView weaponView) {
        player.weapon().addListener(new ChangeListener<Weapon>() {
            @Override
            public void changed(ObservableValue<? extends Weapon> observable, Weapon oldWeapon, Weapon newWeapon) {
                if (newWeapon.getClass().equals(NoWeapon.class)) {
                    weaponView.setVisible(false);
                    dungeonController.getWeaponInfo().setVisible(false);
                }
                if (newWeapon.getClass().equals(Sword.class)) {
                    weaponView.setImage(swordImage);
                    weaponView.setVisible(true);
                    dungeonController.getWeaponInfo().setVisible(true);
                }
            }
        });

        player.getInfo().weaponDurability().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                dungeonController.getWeaponInfo().setText(newValue.toString());
            }
        });
    }

    private void setKeyListener(Player player, ImageView keyView) {
        player.key().addListener(new ChangeListener<Key>() {
            @Override
            public void changed(ObservableValue<? extends Key> observable, Key oldKey, Key newKey) {
                if (newKey == null) {
                    keyView.setVisible(false);
                    dungeonController.getKeyInfo().setVisible(false);
                } else {
                    dungeonController.getKeyInfo().setText("Id: " + newKey.getId());
                    dungeonController.getKeyInfo().setVisible(true);
                    keyView.setVisible(true);
                }
            }
        });
    }

    private void setPotionListener(Player player, ImageView potionView) {
        player.potion().addListener(new ChangeListener<Potion>() {
            @Override
            public void changed(ObservableValue<? extends Potion> observable, Potion oldPotion, Potion newPotion) {
                if (newPotion.getClass().equals(NoPotion.class)) {
                    potionView.setVisible(false);
                    dungeonController.getPotionInfo().setVisible(false);
                }
                if (newPotion.getClass().equals(InvincibilityPotion.class)) {
                    potionView.setImage(invincibilityImage);
                    potionView.setVisible(true);
                    dungeonController.getPotionInfo().setVisible(true);
                }
                if (newPotion.getClass().equals(InvisibilityPotion.class)) {
                    potionView.setImage(invisibilityImage);
                    potionView.setVisible(true);
                    dungeonController.getPotionInfo().setVisible(true);
                }
            }
        });

        player.getInfo().potionDuration().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                dungeonController.getPotionInfo().setText(newValue.toString());
            }  
        });
    }

}