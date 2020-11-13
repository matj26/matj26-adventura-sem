package cz.vse.matejka;

import cz.vse.matejka.model.IGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

    public MenuItem endButton;
    public TextArea textOutput;
    public TextField textInput;

    public Label areaName;
    public Label areaDescription;
    private IGame game;
    private KeyEvent keyEvent;

    public void init(IGame game) {
        this.game = game;
        endGame();
        update();
    }

    public void endGame() {
        endButton.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void update() {
        String name = game.getGamePlan().getCurrentArea().getName();
        areaName.setText(getAreaNameProperly(name));
        String description = game.getGamePlan().getCurrentArea().getDescription();
        areaDescription.setText(description);

    }

    private void executeCommand(String command) {
        String result = game.processCommand(command);
        textOutput.appendText(result + "\n\n");
    }

    public void onInputKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            executeCommand(textInput.getText());
            textInput.setText("");
        }
    }

    private String getAreaNameProperly(String areaName) {
        String name = "";
        switch(areaName) {
            case "hostinec":
                name = "Hostinec";
                break;
            case "stratholme":
                name = "Náměstí Stratholme";
                break;
            case "roklina":
                name = "Roklina";
                break;
            case "jeskyne":
                name = "Jeskyně";
                break;
            case "les":
                name = "Les";
                break;
            case "polni_cesta":
                name = "Polní cesta";
                break;
            case "vetesnictvi":
                name = "Vetešnictví";
                break;
            case "lektvary":
                name = "Obchod s lektvary";
                break;
            case "hospoda":
                name = "Hospoda";
                break;
            case "ketes":
                name = "Královské město Ketes";
                break;
            default:
                name = "";
                break;
        }
        return name;
    }
}
