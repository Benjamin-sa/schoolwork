package be.inf1.flappybird2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.view.Controller;
import be.inf1.flappybird2.model.Bird;
import be.inf1.flappybird2.model.Grenzen;

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

    private Controller gameController;
    private Grenzen grenzen;
    public Timeline gameLoop;
    private Circle vogel;
    private Bird birdModel;
    List<Pilaar> pilaarModels = new ArrayList<>();
    private Grenzen grenzenModel;

    public BirdFXMLController() {

    }

    @FXML
    void initialize() {

        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            for (int i = 1; i < 20; i++) {
                double x = i * 200;
                Pilaar pilaar = new Pilaar(x, 100, 30, paneel.getHeight(), Color.GREEN);
                pilaarModels.add(pilaar);
                Rectangle bovenPilaar = pilaar.getBovenPilaar();
                Rectangle onderPilaar = pilaar.getOnderPilaar();
                paneel.getChildren().addAll(bovenPilaar, onderPilaar);
            }
            // vogel initailiseren
            birdModel = new Bird(100, 100, 10, Color.RED);
            // grenzen initialiseren
            grenzenModel = new Grenzen(0, 0, paneel.getWidth(), paneel.getHeight(), Color.RED, 10);
            // gameController initialiseren
            gameController = new Controller(this, birdModel, grenzenModel, pilaarModels);
            Rectangle bovenGrens = grenzenModel.getBovenGrens();
            Rectangle onderGrens = grenzenModel.getOnderGrens();
            Circle vogel = birdModel.getVogel();
            paneel.getChildren().addAll(bovenGrens, onderGrens, vogel);
        });
        pause.play();

        startKnop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!gameController.isGameGestart()) {
                    gameController.startGame();
                    System.out.println("Game gestart");
                }
                paneel.requestFocus();
            }
        });
    

        paneel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {
                    if(!gameController.isGameGestart()) {
                        gameController.startGame();

                    }
                    gameController.flap();
                    paneel.requestFocus();
                }
            }
        });

        paneel.setFocusTraversable(true);
    }



    public void resetPaneel(){
        
        // pilaarModels.clear();
        // paneel.getChildren().clear();
        // Circle vogel = birdModel.getVogel();
        // Rectangle bovenGrens = grenzenModel.getBovenGrens();
        // Rectangle onderGrens = grenzenModel.getOnderGrens();
        // paneel.getChildren().addAll(bovenGrens, onderGrens, vogel);

        for (int i = 1; i < 20; i++) {
                double x = i * 200;
                Pilaar pilaar = new Pilaar(x, 100, 30, paneel.getHeight(), Color.GREEN);
                pilaarModels.add(pilaar);
                Rectangle bovenPilaar = pilaar.getBovenPilaar();
                Rectangle onderPilaar = pilaar.getOnderPilaar();
                paneel.getChildren().addAll(bovenPilaar, onderPilaar);
            }



    }

    

}
