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

public class Controller {
    private Bird bird;
    private List<Pilaar> pilaren;
    private Grenzen grenzen;
    private BirdFXMLController view;
    private boolean gameGestart = false;
    private int scoreWaarde = 0;
    private int highScoreWaarde = 0;
    private Timeline movePilaren;
    private Pilaar laatstePilaarVoorbij = null;

    public Controller(BirdFXMLController view, Bird birdModel, Grenzen grenzenModel, List<Pilaar> pilaarModels) {
        this.view = view;
        this.bird = birdModel;
        this.grenzen = grenzenModel;
        this.pilaren = pilaarModels;

    }

    public void startGame() {


        gameGestart = true;
        movePilaren = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if (gameGestart) {
                updateGame();
            }

        }));
        movePilaren.setCycleCount(Timeline.INDEFINITE);

        movePilaren.play();

    }

    public void updateGame() {

        laatstePilaarVoorbij = null;


        // beweeg de pilaren
        for (Pilaar pilaar : pilaren) {
            pilaar.setX(pilaar.getX() - 2);

            // botsing detectie met de pilaar
            if (bird.getVogel().getBoundsInParent().intersects(pilaar.getBovenPilaar().getBoundsInParent()) ||
                    bird.getVogel().getBoundsInParent().intersects(pilaar.getOnderPilaar().getBoundsInParent())) {
                stopGame();
                System.out.println("Botsing gedetecteerd!");
            }
            // botsing detectie met de grenzen
            if (bird.getVogel().getBoundsInParent().intersects(grenzen.getBovenGrens().getBoundsInParent()) ||
                    bird.getVogel().getBoundsInParent().intersects(grenzen.getOnderGrens().getBoundsInParent())) {
                System.out.println("Botsing gedetecteerd!");
                stopGame();
            }

            if (!pilaar.isVoorbij() && bird.getVogel().getCenterX() > pilaar.getX() + pilaar.getDikte()) {
                    pilaar.setVoorbij(true);
                    verhoogScore();
                }

            


        }

        bird.val();
    }

    public void flap() {
        bird.flap();
    }

    public void stopGame() {
        scoreWaarde = 0;
        view.updateScore(scoreWaarde);
        gameGestart = false;
        movePilaren.stop();

    }

    public void resetGame(){

        view.resetPilaren();
        bird.reset();

    }

    public void restartGame() {
        setGameGestart(true);
        movePilaren.play();
    }

    public void verhoogScore() {
        scoreWaarde++;
        view.updateScore(scoreWaarde);

        if (scoreWaarde > highScoreWaarde) {
            highScoreWaarde = scoreWaarde;
            view.updateHighScore(highScoreWaarde);
        }
        System.out.println("Score: " + scoreWaarde);
    }

    public int getHighScoreWaarde() {
        return highScoreWaarde;
    }

    public boolean isGameGestart() {
        return gameGestart;
    }

    public boolean setGameGestart(boolean gameGestart) {
        this.gameGestart = gameGestart;
        return gameGestart;
    }

}