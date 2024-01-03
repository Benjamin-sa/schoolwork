
package be.inf1.flappybird2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import be.inf1.flappybird2.view.ViewGame;
import be.inf1.flappybird2.BirdFXMLController;
import be.inf1.flappybird2.model.Pilaar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;






public class GameController {
    private ViewGame viewGame;
    private BirdFXMLController BirdFXMLController;
    private Pilaar rectangle;
    private AnchorPane paneel;  
    private Timeline gameLoop; 
    private Circle Vogel;

    public GameController(ViewGame viewGame, AnchorPane paneel, Circle Vogel, BirdFXMLController BirdFXMLController, Pilaar rectangle) {
        this.viewGame = viewGame;
        this.BirdFXMLController = BirdFXMLController;
        this.rectangle = rectangle;
        this.paneel = paneel;  
        this.Vogel = Vogel;  
    }

    // De rest van je code...

    public void startGame() {
        System.out.println("startGame");
        
        List<Pilaar> pilaren = viewGame.getPilaren();
        // Verwijder de oude pilaren
        paneel.getChildren().removeAll(pilaren);
    
        // Teken de nieuwe pilaren
        viewGame.tekenPilaren();
    
        // Start de pilaren
        viewGame.startPilaren();
    
        gameLoop = new Timeline(new KeyFrame(Duration.seconds(0.017), e ->{

        Vogel.setCenterY(Vogel.getCenterY() + 1);
        checkBotsingen();
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    
        paneel.requestFocus();
    }

    public void updateGameState() {
        // Update the game state (e.g., move the bird, move the pillars)
        // ...

        // Check for collisions
        checkBotsingen();
    }



    public void checkBotsingen() {
    Circle vogel = BirdFXMLController.getVogel();
    List<Pilaar> pilaren = viewGame.getPilaren();

    for (Pilaar pilaar : pilaren) {
        Rectangle rectangle = pilaar.getRectangle();
        // Check if the bird intersects with the pillar
        
        if (Shape.intersect(vogel, rectangle).getBoundsInLocal().getWidth() != -1) {
            // Collision detected, handle it (e.g., end the game)
            gameOver();
            System.out.println("Collision detected");
        }
    }    }

    public void gameOver() {
        BirdFXMLController.gameLoop.stop();
        System.out.println("gameOver");
    }
}