package cz.vse.matejka;

import cz.vse.matejka.model.IGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

    public MenuItem endButton;
    public TextArea textOutput;
    public TextField textInput;
    private IGame game;

    public void init(IGame game) {
        this.game = game;
        endGame();
    }

    public void endGame() {
        endButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
