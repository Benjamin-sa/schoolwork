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
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.model.Bird;
import be.inf1.flappybird2.model.Grenzen;
import be.inf1.flappybird2.SpelerGegevens;

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
    private Button speelSpel;

    @FXML
    private AnchorPane paneel;

    @FXML
    private Label score;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button startKnop;

    @FXML
    private TextArea spelerNaam;

    @FXML
    private VBox menu;

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

        menu.setVisible(true);
        paneel.setVisible(false);
        startKnop.setVisible(false);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> {
            for (int i = 1; i < 20; i++) {
                double x = i * 300;
                Pilaar pilaar = new Pilaar(x, 100, 30, paneel.getHeight(), Color.GREEN);
                pilaarModels.add(pilaar);
            }
            // vogel initailiseren
            birdModel = new Bird(100, 100, 5, Color.RED);
            // grenzen initialiseren
            grenzenModel = new Grenzen(0, 0, paneel.getWidth(), paneel.getHeight(), Color.RED, 10);
            // gameController initialiseren
            gameController = new Controller(this, birdModel, grenzenModel, pilaarModels);
        });
        pause.play();

        speelSpel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menu.setVisible(false);
                paneel.setVisible(true);
                startKnop.setVisible(true);
                plaatsElementen();
                paneel.requestFocus();
            }
        });

        startKnop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!gameController.isGameGestart()) {
                    gameController.startGame();
                    resetPilaren();
                    birdModel.reset();
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

        // highscore opslaan bij het afsluiten van het programma
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                SpelerGegevens speler = new SpelerGegevens(spelerNaam.getText(), gameController.getHighScoreWaarde());
                speler.gegevensOpslaan(spelerNaam.getText(), gameController.getHighScoreWaarde());
            }
        }));
    }



    public void resetPilaren(){

        for (Pilaar pilaar : pilaarModels) {
            paneel.getChildren().removeAll(pilaar.getBovenPilaar(), pilaar.getOnderPilaar());
        }
        pilaarModels.clear();

        for (int i = 1; i < 20; i++) {
                double x = i * 200;
                Pilaar pilaar = new Pilaar(x, 100, 30, paneel.getHeight(), Color.GREEN);
                pilaarModels.add(pilaar);
                Rectangle bovenPilaar = pilaar.getBovenPilaar();
                Rectangle onderPilaar = pilaar.getOnderPilaar();
                paneel.getChildren().addAll(bovenPilaar, onderPilaar);
            }

    }

    public void plaatsElementen(){

        for (Pilaar pilaar : pilaarModels) {
            Rectangle bovenPilaar = pilaar.getBovenPilaar();
        Rectangle onderPilaar = pilaar.getOnderPilaar();
        paneel.getChildren().addAll(bovenPilaar, onderPilaar);
        }
        
        Rectangle bovenGrens = grenzenModel.getBovenGrens();
        Rectangle onderGrens = grenzenModel.getOnderGrens();
        Circle vogel = birdModel.getVogel();
        paneel.getChildren().addAll(bovenGrens, onderGrens, vogel);
    }

    public void updateScore(int scoreWaarde) {
        score.setText(Integer.toString(scoreWaarde));
        
    }

    public void updateHighScore(int highScoreWaarde) {
        highScore.setText(Integer.toString(highScoreWaarde));
    }


    

}
