package be.inf1.flappybird2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

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
import be.inf1.flappybird2.view.ViewGame;
import be.inf1.flappybird2.model.Bird;
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.GameController;
import javafx.util.Duration;

public class BirdFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label highScore;

    @FXML
    private Circle Vogel;

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

    private Bird bird;
    private GameController gameController;
    private ViewGame view;
    public Timeline gameLoop;
    private Pilaar rectangle;
    private Rectangle boven;
    private Rectangle onder;




    @FXML
    void initialize() {
        paneel.setStyle("-fx-background-color: #00FFFF;");
        view = new ViewGame(paneel);
        rectangle = new Pilaar(20, 100, 100, 100);
        gameController = new GameController(view, paneel, getVogel(), this, rectangle );


        startKnop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameController.startGame();
                paneel.requestFocus();
            }
        });

        paneel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    Vogel.setCenterY(Vogel.getCenterY() - 50);
                }
            }
        });
    
        paneel.setFocusTraversable(true);
    }

        

public Circle getVogel() {
    return Vogel;
        


}}