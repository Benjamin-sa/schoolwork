package be.inf1.flappybird2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import be.inf1.flappybird2.view.ViewGame;
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.GameController;
import be.inf1.flappybird2.model.Bird;

public class BirdFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label highScore;

    @FXML
    private Label highscoreLabel;

    @FXML
    private Button knop;

    @FXML
    private AnchorPane paneel;

    @FXML
    private Label score;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button startKnop;

    private GameController gameController;
    private ViewGame view;
    public Timeline gameLoop;
    private Pilaar rectangle;
    private Bird vogel;
    private double Xpos = 200;

    public BirdFXMLController() {
    }

    @FXML
    void initialize() {
                       
        view = new ViewGame(paneel, this, highScore);
        view.tekenVogel();
        paneel.setStyle("-fx-background-color: #00FFFF;");
        gameController = new GameController(view, vogel, paneel, rectangle);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    view.tekenPilaren();
                    view.tekenBorders();
                });
            }
        },0 ); 

        startKnop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!gameController.isGameGestart()) {
                    gameController.startGame();
                }
                paneel.requestFocus();
            }
        });

        paneel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    if (!gameController.isGameGestart()) {
                        gameController.startGame();
                    } else {
                        vogel.flap();
                    }
                    paneel.requestFocus();
                }
            }
        });

        paneel.setFocusTraversable(true);
    }


    public AnchorPane getPaneel() {
        return paneel;
    }
}
