
package be.inf1.flappybird2;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    private int aantalPilarenVoorbij = 0;


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
            updatePilaren();
            checkPassedPillar();
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


    public void updatePilaren() {
        for (Pilaar pilaar : viewGame.getPilaren()) {
            // Verminder de x-positie van de pilaar
            double newX = pilaar.getX() - 1;
            pilaar.setX(newX);
    
            // Update de positie van de bovenste en onderste rechthoeken
            pilaar.getBovenPilaar().setX(newX);
            pilaar.getOnderPilaar().setX(newX);
        }
    }
    

    public boolean checkBotsingen() {

        Rectangle bovenGrens = viewGame.getBovenGrens();
        Rectangle onderGrens = viewGame.getOnderGrens();
        // Controleer of de vogel de boven- of onderkant van het scherm raakt
        if (bird.getVogel().getBoundsInParent().intersects(bovenGrens.getBoundsInParent()) ||
        bird.getVogel().getBoundsInParent().intersects(onderGrens.getBoundsInParent())) {
            gameOverScherm();
            
            return true;      
    }

    for (Pilaar pilaar : viewGame.getPilaren()) {
        // Controleer of de vogel in aanraking komt met de bovenste of onderste pilaar
        if (bird.getVogel().getBoundsInParent().intersects(pilaar.getBovenPilaar().getBoundsInParent()) ||
            bird.getVogel().getBoundsInParent().intersects(pilaar.getOnderPilaar().getBoundsInParent())) {
            
            gameOverScherm();
            return true;
        }
    }
     return false;
}

private void gameOverScherm() {
    try {
        AnchorPane gameOverScherm = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
        paneel.getChildren().setAll(gameOverScherm);
    } catch (IOException e) {
        e.printStackTrace();
    }
}




    private int currentPilaarIndex = 0;

public void checkPassedPillar() {
    if (currentPilaarIndex < viewGame.getPilaren().size()) {
        Pilaar pilaar = viewGame.getPilaren().get(currentPilaarIndex);
        
        // Controleer of de pilaar volledig uit het zicht is verdwenen aan de linkerkant van het scherm
        if (pilaar.getX() < bird.getCenterumx() && !pilaar.isVoorbijGevlogen()) {
            pilaar.setVoorbijGevlogen(true);  // Markeer de pilaar als gepasseerd
            updateScore();  // Update de score

            // Ga naar de volgende pilaar
            currentPilaarIndex++;
        }
    }
}



    public void updateScore() {
        score++;
    System.out.println("Score: " + score);
    BirdFXMLController.updateScore(score);
            
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
            // Beweeg de bovenste pilaar
            pilaar.getBovenPilaar().setTranslateX(pilaar.getBovenPilaar().getTranslateX() - 2);
            // Beweeg de onderste pilaar
            pilaar.getOnderPilaar().setTranslateX(pilaar.getOnderPilaar().getTranslateX() - 2);
        }
    }

            

    public boolean isGameGestart() {
        return gameGestart;
    }

    

    public void setGameGestart(boolean gameGestart) {
        this.gameGestart = gameGestart;
    }
        


}