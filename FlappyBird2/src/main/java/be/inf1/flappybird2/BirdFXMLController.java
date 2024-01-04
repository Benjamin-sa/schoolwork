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
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.GameController;
import be.inf1.flappybird2.model.Bird;
import javafx.util.Duration;

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

    public BirdFXMLController() {
        System.out.println("in constructor" + vogel);
    }




    @FXML
    void initialize() {
        
        view = new ViewGame(paneel, this);
        System.out.println("Before initializing vogel: " + vogel);
        vogel = new Bird(50, 100, 7, paneel);
        System.out.println("After initializing vogel: " + vogel);
        paneel.getChildren().add(vogel.getVogel());
        paneel.setStyle("-fx-background-color: #00FFFF;");
        rectangle = new Pilaar(new Rectangle());
        gameController = new GameController(view,vogel,paneel, this, rectangle); 
        


        Platform.runLater(() -> {
            view.tekenPilaren();
            view.tekenBorders();
        });
        


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
                    if (!gameController.isGameGestart()) {
                        gameController.startGame();
                    } else {
                        System.out.println("In setOnKeyPressed block: " + vogel);
                        vogel.setyCoord(vogel.getCenterumy() - 75);
                    }
                    paneel.requestFocus();
                }
            }
        });
    
        paneel.setFocusTraversable(true);
    }

        



public void updateScore(int score) {
    this.score.setText("score" + score);
    //System.out.println(score);
}

public AnchorPane getPaneel() {
    return paneel;
        


}}