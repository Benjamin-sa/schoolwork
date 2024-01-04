
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class GameController {
    private ViewGame viewGame;
    private BirdFXMLController BirdFXMLController;
    private Pilaar pilaar;
    private AnchorPane paneel;  
    private Timeline gameLoop; 
    private boolean gameGestart = false;
    private int score = 0;
    private int hvlpilaar = 0;
    private Bird bird;
    private long startTijd;


    public GameController(ViewGame viewGame, Bird bird, AnchorPane paneel, BirdFXMLController BirdFXMLController, Pilaar rectangle) {
        this.viewGame = viewGame;
        this.BirdFXMLController = BirdFXMLController;
        this.pilaar = rectangle;
        this.paneel = paneel;
        this.bird = bird;  
        
    }

    // De rest van je code...

    public void startGame() {

        System.out.println("startGame");
        if (!gameGestart) {
            startTijd = System.currentTimeMillis();
        }
        
        // Start de game loop
        gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.017), e ->{
            updateScore();
            bird.setyCoord(bird.getCenterumy() + 1);
            
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

        Rectangle bovenGrens = viewGame.getBovenGrens();
        Rectangle onderGrens = viewGame.getOnderGrens();
        // Controleer of de vogel de boven- of onderkant van het scherm raakt
        if (bird.getVogel().getBoundsInParent().intersects(bovenGrens.getBoundsInParent()) ||
        bird.getVogel().getBoundsInParent().intersects(onderGrens.getBoundsInParent())) {
            restartGame(); 
            return true;      
    }

    for (Pilaar pilaar : viewGame.getPilaren()) {

        if (bird.getVogel().getBoundsInParent().intersects(pilaar.getPilaar().getBoundsInParent())) {
            restartGame();  
            return true;
        }
    }
    return false;}



    public void updateScore() {
        double afstandTussenPilaren = 250;  // De afstand tussen de pilaren
        double pilaarSnelheid = 1;  // De snelheid waarmee de pilaren naar links bewegen
        double beginAfstand = 225;
        long verstrekenTijd = System.currentTimeMillis() - startTijd;
    
        // Bereken het aantal pilaren dat de vogel zou moeten hebben gepasseerd
        
    double totaleAfstand = (verstrekenTijd / 10) * pilaarSnelheid + beginAfstand;
    int verwachteGepasseerdePilaren = (int) (totaleAfstand / afstandTussenPilaren);
    
        System.out.println("Score: " + score);
        System.out.println("Verwachte gepasseerde pilaren: " + verwachteGepasseerdePilaren);
    
        if (score < verwachteGepasseerdePilaren) {
            score = verwachteGepasseerdePilaren;
            System.out.println("Score: " + score);
             BirdFXMLController.updateScore(score);
        }
            
    }


    public void restartGame(){

        score = 0;
        BirdFXMLController.updateScore(score);
        viewGame.reset();
        bird.resetVogel();

        
    }
        
        
    

    

    public void beweegPilaren() {
        List<Pilaar> pilaren = viewGame.getPilaren();

        for (Pilaar pilaar : pilaren) {
            pilaar.getPilaar().setTranslateX(pilaar.getPilaar().getTranslateX() - 2); 
        }
    }

            

    public boolean isGameGestart() {
        return gameGestart;
    }

    

    public void setGameGestart(boolean gameGestart) {
        this.gameGestart = gameGestart;
    }
        


}