package cz.vse.matejka;

import cz.vse.matejka.model.*;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.util.Collection;

public class MainController {

    public MenuItem endButton;
    public TextArea textOutput;
    public TextField textInput;

    public BorderPane background;
    public Label areaName;
    public Label areaDescription;
    public VBox exits;
    public VBox persons;
    public VBox items;
    public VBox inventory;
    public VBox equip;

    private IGame game;

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
        String name = getArea().getName();
        areaName.setText(getAreaNameProperly(name));
        String description = getArea().getDescription();
        areaDescription.setText(description);

        updateExits();
    }

    private void updateExits() {
        Collection<Area> exitAreas = getArea().getExits();
        exits.getChildren().clear();
        for (Area area : exitAreas) {
            Label element = createObject(area.getName());
            element.setOnMouseClicked(event -> {
                executeCommand("jdi " + element.getText());
            });
            String style = new String("-fx-background-image: url" + "('" + getArea().getName() + ".jpg')");
            background.setStyle(style);

            exits.getChildren().add(element);
        }
    }

    private Label createObject(String name) {
        Label label = new Label(name);
        label.setCursor(Cursor.HAND);

        InputStream stream = getClass().getClassLoader().getResourceAsStream(name+ ".jpg");
        Image img = new Image(stream);
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(100);
        imageView.setFitHeight(60);
        label.setGraphic(imageView);
        label.setStyle("-fx-padding: 0 0 5 5");
        return label;
    }

    private void executeCommand(String command) {
        String result = game.processCommand(command);
        textOutput.appendText(result + "\n\n");
        update();
    }

    public void onInputKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            executeCommand(textInput.getText());
            textInput.setText("");
        }
    }

    private Area getArea() {
        return game.getGamePlan().getCurrentArea();
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
