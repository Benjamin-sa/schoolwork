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
import be.inf1.flappybird2.model.Bird;
import be.inf1.flappybird2.model.Grenzen;
import be.inf1.flappybird2.model.Pilaar;

public class Controller {
    private Bird bird;
    private List<Pilaar> pilaren;
    private Grenzen grenzen;
    private BirdFXMLController view;
    private boolean gameGestart = false;
    private int scoreWaarde = 0;
    private int highScoreWaarde = 0;
    private int level = 0;
    private Timeline movePilaren;
    private SpelerGegevens speler;
    private boolean snelheidVerhoogd = false;

    public Controller(BirdFXMLController view, Bird birdModel, Grenzen grenzenModel, List<Pilaar> pilaarModels) {
        this.view = view;
        this.bird = birdModel;
        this.grenzen = grenzenModel;
        this.pilaren = pilaarModels;
        speler = new SpelerGegevens();
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
        for (Pilaar pilaar : pilaren) {
            // pilaar bewegen
            pilaar.setX(pilaar.getX() - pilaar.getSnelheid());
            // Score tellen als de pilaar voorbij is
            if (!pilaar.isVoorbij() && bird.getVogel().getCenterX() > pilaar.getX() + pilaar.getDikte()) {
                pilaar.setVoorbij(true);
                verhoogScore();
            }

            // pilaar resetten als hij uit het scherm is
            if (pilaar.getX() < -pilaar.getDikte()) {
                pilaar.setX(pilaren.size() * 300);
                pilaar.setVoorbij(false);
                pilaar.resetOpening(view.getPaneelHoogte());
            }
        }
        // vogel bewegen
        bird.val();
        // Bij detectie stoppen
        if (botsing()) {
            stopGame();
        }

        // level up
        levelUpCheck();
    }

    public void flap() {
        bird.flap();
    }

    public Boolean botsing(){
    //     for(Pilaar pilaar : pilaren){
    //         // botsing detectie met de pilaar
    //         if(bird.getVogel().getBoundsInParent().intersects(pilaar.getBovenPilaar().getBoundsInParent()) ||
    //                 bird.getVogel().getBoundsInParent().intersects(pilaar.getOnderPilaar().getBoundsInParent())){
    //             return true;
    //         }
    //         // botsing detectie met de grenzen
    //         if (bird.getVogel().getBoundsInParent().intersects(grenzen.getBovenGrens().getBoundsInParent()) ||
    //                 bird.getVogel().getBoundsInParent().intersects(grenzen.getOnderGrens().getBoundsInParent())) {
    //             return true;
    //     }
    // }
        return false;
    }

    public void levelUpCheck() {
        if (scoreWaarde > 0 && scoreWaarde % pilaren.size() == 0) {
            if (!snelheidVerhoogd) {
                level++;
                System.out.println("Level: " + level);
                levelUp();
                snelheidVerhoogd = true;
            }
        } else {
            snelheidVerhoogd = false;
        }
    }

    public void levelUp() {
        for (Pilaar pilaar : pilaren) {
            pilaar.setSnelheid(pilaar.getSnelheid() + 0.5);
            System.out.println("Snelheid: " + pilaar.getSnelheid());
        }
        }
       

    public void stopGame() {
        scoreWaarde = 0;
        view.updateScore(scoreWaarde);
        setGameGestart(false);
        movePilaren.stop();
    }

    public void resetGame() {
        bird.reset();
    }


    public void restartGame() {
        setGameGestart(true);
        movePilaren.play();
    }

    public void verhoogScore() {
        scoreWaarde++;
        view.updateScore(scoreWaarde);
        view.updateLevel(level);
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

    public void setSpelerNaam(String naam) {

            speler.setNaam(naam);
    // highscore opslaan bij het afsluiten van het programma
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
        public void run() {
            speler.setHighScore(getHighScoreWaarde());
            speler.saveData();
        }
    }));
}

public List<SpelerGegevens> getBesteSpelers() {
    return speler.besteSpelers();
}

}