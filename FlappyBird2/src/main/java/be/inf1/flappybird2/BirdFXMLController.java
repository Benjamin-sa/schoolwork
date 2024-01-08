package be.inf1.flappybird2;

import java.net.URL;
import java.util.ArrayList;
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

import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.GameController;
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

    private GameController gameController;
    private Grenzen grenzen;
    public Timeline gameLoop;
    private double schermBreedte;
    private Circle vogel;
    private Rectangle bovenGrens, onderGrens;
    private Bird birdModel;
    List<Pilaar> pilaarModels = new ArrayList<>();
    List<Rectangle> pilaarBoven = new ArrayList<>();
    List<Rectangle> pilaarOnder = new ArrayList<>();    private Grenzen grenzenModel;


    public BirdFXMLController() {
       
    }

    @FXML
    void initialize() {


        
        for (int i = 0; i < 5; i++) {
            Pilaar pilaarModel = new Pilaar();
            pilaarModels.add(pilaarModel);
            Rectangle pilaarBovenRect = new Rectangle();
            Rectangle pilaarOnderRect = new Rectangle();
            pilaarBoven.add(pilaarBovenRect);
            pilaarOnder.add(pilaarOnderRect);
            paneel.getChildren().addAll(pilaarBovenRect, pilaarOnderRect);
        }

        birdModel = new Bird();
        grenzenModel = new Grenzen();
        bovenGrens = new Rectangle();
        onderGrens = new Rectangle();
        vogel = new Circle();

        paneel.getChildren().addAll(vogel, bovenGrens, onderGrens);
        System.out.println(paneel.getChildren().size());
        gameController = new GameController(this, birdModel, grenzenModel, pilaarModels);
        



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
                        birdModel.flap();
                    paneel.requestFocus();
                }
            }
        });

        paneel.setFocusTraversable(true);
    }

    public void tekenVogel(){
        vogel.setCenterX(birdModel.getxCoord());
        vogel.setCenterY(birdModel.getyCoord());
        vogel.setRadius(birdModel.getRadius());
        vogel.setFill(birdModel.getKleur());    
    }

    public void tekenPilaren(){
        double paneelHoogte = paneel.getHeight();
        double paneelBreedte = paneel.getWidth();
        double afstand = 200; // afstand tussen de pilaren
    
        for (int i = 0; i < pilaarModels.size(); i++) {
            Pilaar pilaarModel = pilaarModels.get(i);
            Rectangle pilaarBovenRect = pilaarBoven.get(i);
            Rectangle pilaarOnderRect = pilaarOnder.get(i);
    
            double x = i * afstand + paneelBreedte;
            pilaarModel.setX(x);
    
            pilaarBovenRect.setX(pilaarModel.getX());
            System.out.println(pilaarModel.getX());
            pilaarBovenRect.setY(0);
            pilaarBovenRect.setWidth(10); // breedte van de pilaar
            pilaarBovenRect.setHeight(paneelHoogte - pilaarModel.getYBoven() - pilaarModel.getOpening()); // hoogte van de bovenste pilaar
    
            pilaarOnderRect.setX(pilaarModel.getX());
            pilaarOnderRect.setY(pilaarModel.getYBoven() + pilaarModel.getOpening()); // y-positie van de onderste pilaar
            pilaarOnderRect.setWidth(10); // breedte van de pilaar
            pilaarOnderRect.setHeight(paneelHoogte - pilaarModel.getYBoven() - pilaarModel.getOpening()); // hoogte van de onderste pilaar
    
            pilaarBovenRect.setFill(pilaarModel.getKleur());
            pilaarOnderRect.setFill(pilaarModel.getKleur());
        }
    }


    public void tekenGrenzen(){
        bovenGrens.setX(grenzenModel.getX());
        bovenGrens.setY(grenzenModel.getY());
        bovenGrens.setWidth(grenzenModel.getBreedte());
        bovenGrens.setHeight(grenzenModel.getHoogte());
        bovenGrens.setFill(grenzenModel.getKleur());

        onderGrens.setX(grenzenModel.getX());
        onderGrens.setY(paneel.getHeight() - grenzenModel.getY());
        onderGrens.setWidth(grenzenModel.getBreedte());
        onderGrens.setHeight(grenzenModel.getHoogte());
        onderGrens.setFill(grenzenModel.getKleur());

    }
        


    public double getPaneelBreedte() {
        return paneel.getWidth();
    }

    public double getPaneelHoogte() {
        return paneel.getHeight();
    }
    

}
