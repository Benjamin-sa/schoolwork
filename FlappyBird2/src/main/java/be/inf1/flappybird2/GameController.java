
package be.inf1.flappybird2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import be.inf1.flappybird2.view.ViewGame;
import be.inf1.flappybird2.BirdFXMLController;
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.model.Bird;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import be.inf1.flappybird2.view.ViewGame;
import be.inf1.flappybird2.BirdFXMLController;
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.model.Bird;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class GameController {
    private ViewGame viewGame;
    private BirdFXMLController BirdFXMLController;
    private Pilaar pilaar;
    private AnchorPane paneel;  
    private Timeline gameLoop; 
    private Circle Vogel;
    private Bird bird;
    private boolean gameGestart = false;
    private int score = 0;

    public GameController(ViewGame viewGame, AnchorPane paneel, Circle Vogel, BirdFXMLController BirdFXMLController, Pilaar rectangle) {
        this.viewGame = viewGame;
        this.BirdFXMLController = BirdFXMLController;
        this.pilaar = rectangle;
        this.paneel = paneel;  
        this.Vogel = Vogel;
    }

    // De rest van je code...

    public void startGame() {
        System.out.println("startGame");
        this.bird = new Bird(0, 0, viewGame, BirdFXMLController, pilaar);
        // Start de game loop
        gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.017), e ->{
            Vogel.setCenterY(Vogel.getCenterY() + 1);
            updateGameState();
            beweegPilaren();
            gameGestart = true;
            if (checkBotsingen()) {
                gameLoop.stop();
                setGameGestart(false);
                restartGame();
            }
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    
        paneel.requestFocus();
    }

    public boolean checkBotsingen() {
        for (Pilaar pilaar : viewGame.getPilaren()) {
            // Controleer of de vogel botst met de bovenste of onderste Rectangle van elke pilaar
            if (BirdFXMLController.getVogel().getBoundsInParent().intersects(pilaar.getPilaar().getBoundsInParent())) {
                return false;
            }
        }
        return false;
    }

    public void updateGameState() {
        List<Pilaar> pilaren = viewGame.getPilaren();
    }


    public void restartGame(){

        bird.reset();
        viewGame.reset();
    }
        
        
    

    

    public void beweegPilaren() {
        List<Pilaar> pilaren = viewGame.getPilaren();

        System.out.println(pilaren.size());
        for (Pilaar pilaar : pilaren) {
            // Beweeg de bovenste en onderste Rectangle van elke pilaar naar links
            pilaar.getPilaar().setTranslateX(pilaar.getPilaar().getTranslateX() - 5);
        }

        for (Pilaar pilaar : viewGame.getPilaren()) {

            // Check if the bird has passed the pillar
            if (bird.getxCoord() > pilaar.getx() + pilaar.getBreedte() && !pilaar.isVoorbij()) {
                score++;
                pilaar.setIsVoorbij(true);
                    BirdFXMLController.updateScore(score);
                }
            }
        }   

    public boolean isGameGestart() {
        return gameGestart;
    }

    public void setGameGestart(boolean gameGestart) {
        this.gameGestart = gameGestart;
    }
        


}