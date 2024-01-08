package be.inf1.flappybird2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.model.Bird;
import be.inf1.flappybird2.model.Grenzen;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.util.Duration;

public class GameController {
    private Bird bird;
    private List<Pilaar> pilaren;
    private Grenzen grenzen;
    private BirdFXMLController view;
    private boolean gameGestart = false;
    private int score = 0;
    private AnimationTimer gameLoop;

    public GameController(BirdFXMLController view, Bird birdModel, Grenzen grenzenModel, List<Pilaar> pilaarModels) {
        this.view = view;
        this.bird = birdModel;
        this.grenzen = grenzenModel;
        this.pilaren = pilaarModels;

        this.gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameGestart) {
                        updateGame();

                    
                }
            } 
        };
}

    public void startGame() {
        gameGestart = true;
        gameLoop.start();
    }

    public void stopGame() {
        gameGestart = false;
        gameLoop.stop();
    }

    private void updateGame() {

        double paneelHoogte = view.getPaneelHoogte();
        double paneelbreedte = view.getPaneelBreedte();
        
        bird.update();
        for (Pilaar pilaar : pilaren) {
            pilaar.update(paneelHoogte, paneelbreedte);
    
            

        }
        view.tekenVogel();
        view.tekenPilaren();
    }

    public void incrementScore() {
        score++;
        System.out.println("Score: " + score);
    }

    public void resetGame() {
            score = 0;
    }

    public boolean isGameGestart() {
        return gameGestart;
    }
}