package be.inf1.flappybird2;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Benjamin
 */
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.model.Bird;
import be.inf1.flappybird2.model.Grenzen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Controller {
    private Bird bird; // Vogel object
    private Grenzen grenzen; // Grenzen object
    private BirdFXMLController view; // View object
    private boolean gameGestart = false; // Geeft aan of het spel gestart is
    private int scoreWaarde = 0; // Score van het spel
    private int highScoreWaarde = 0; // Hoogste score behaald
    private int level = 0; // Huidig level
    private Timeline movePilaren; // Tijdlijn voor het bewegen van de pilaren
    private SpelerGegevens speler; // Spelergegevens object
    private boolean snelheidVerhoogd = false; // Geeft aan of de snelheid van de pilaren is verhoogd
    private List<Pilaar> pilaren; // Lijst van pilaar objecten

    public Controller(BirdFXMLController view) {
        this.view = view;
        
        pilaren = new ArrayList<>();
        speler = new SpelerGegevens();

        // Pilaren aanmaken 
        for (int i = 0; i < 5; i++) {
            Pilaar pilaarModel = new Pilaar();
            double x = pilaarModel.berekenBeginPositie(i);
            pilaarModel.setX(x);
            pilaarModel.setOpening(150);
            pilaarModel.setPaneelHoogte(view.getPaneelHoogte());
            pilaarModel.resetOpening(view.getPaneelHoogte());
            pilaren.add(pilaarModel);
        }

        // Vogel aanmaken
        bird = new Bird();
        bird.setEersteX(100);
        bird.setEersteY(100);
        bird.setRadius(10);
        bird.setKleur(Color.TRANSPARENT);

        // Grenzen aanmaken
        grenzen = new Grenzen();
        grenzen.setX(0);
        grenzen.setY(0);
        grenzen.setPaneelBreedte(view.getPaneelBreedte());
        grenzen.setPaneelHoogte(view.getPaneelHoogte());
        grenzen.setKleur(Color.RED);
        grenzen.setDikte(10);
        System.out.println("Paneel hoogte: " + grenzen.getBovenGrens());
    }

    // Start het spel
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



    // Update het spel
    public void updateGame() {
        for (Pilaar pilaar : pilaren) {
            // Pilaar bewegen
            pilaar.setX(pilaar.getX() - pilaar.getSnelheid());
            view.scrollendeAchtergrond();
            // Score tellen als de pilaar voorbij is
            if (!pilaar.isVoorbij() && bird.getVogel().getCenterX() > pilaar.getX() + pilaar.getDikte()) {
                pilaar.setVoorbij(true);
                verhoogScore();
            }

            // Pilaar resetten als hij uit het scherm is
            if (pilaar.getX() < -pilaar.getDikte()) {
                pilaar.setX(pilaren.size() * 300);
                pilaar.setVoorbij(false);
                pilaar.resetOpening(view.getPaneelHoogte());
            }
        }
        // Vogel bewegen
        bird.val();
        view.updateVogelAnimatie();
        if (botsing()) {
            stopGame();
        }

        // Level up controleren
        levelUpCheck();
    }

    // Vogel laten flappen
    public void flap() {
        bird.flap();
    }

    // Controleert of er een botsing is
    public Boolean botsing(){
        for(Pilaar pilaar : pilaren){
            // Botsing detectie met de pilaar
            if(bird.getVogel().getBoundsInParent().intersects(pilaar.getBovenPilaar().getBoundsInParent()) ||
                    bird.getVogel().getBoundsInParent().intersects(pilaar.getOnderPilaar().getBoundsInParent())){
                return true;
            }
            // Botsing detectie met de grenzen
            if (bird.getVogel().getBoundsInParent().intersects(grenzen.getBovenGrens().getBoundsInParent()) ||
                    bird.getVogel().getBoundsInParent().intersects(grenzen.getOnderGrens().getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }

    // Controleert of er een level-up is bereikt
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

    // Verhoogt het level
    public void levelUp() {
        for (Pilaar pilaar : pilaren) {
            pilaar.setSnelheid(pilaar.getSnelheid() + pilaar.getSnelheidVerhoogd());
        }
    }

    // Stopt het spel
    public void stopGame() {
        view.stopPaneel();
        view.startGameOver();
        view.setSpatieBalk(false);
        setGameGestart(false);
        System.out.println(getGameGestart());
        movePilaren.stop();
    }

    // Reset het spel
    public void resetGame() {
        bird.reset();
        scoreWaarde = 0;
        level = 0;
        view.updateLevel(level);
        view.updateScore(scoreWaarde);

        for (Pilaar pilaar : pilaren) {
            double x = pilaar.berekenBeginPositie(pilaren.indexOf(pilaar));
            pilaar.setX(x);
            pilaar.setSnelheid(2);
        }
    }

    // Herstart het spel
    public void restartGame() {
        setGameGestart(true);
        movePilaren.play();
    }

    // Verhoogt de score
    public void verhoogScore() {
        scoreWaarde++;
        view.updateScore(scoreWaarde);
        view.updateLevel(level);
        if (scoreWaarde > highScoreWaarde) {
            highScoreWaarde = scoreWaarde;
            view.updateHighScore(highScoreWaarde);
        }
    }

    // Geeft de hoogste score terug
    public int getHighScoreWaarde() {
        return highScoreWaarde;
    }

    // Geeft aan of het spel gestart is
    public boolean getGameGestart() {
        return gameGestart;
    }

    // Stelt in of het spel gestart is
    public boolean setGameGestart(boolean gameGestart) {
        this.gameGestart = gameGestart;
        return gameGestart;
    }

    // Stelt de naam van de speler in
    public void setSpelerNaam(String naam) {
        speler.setNaam(naam);
        // Highscore opslaan bij het afsluiten van het programma
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                speler.setHighScore(getHighScoreWaarde());
                speler.saveData();
            }
        }));
    }

    // Geeft de beste spelers terug
    public List<SpelerGegevens> getBesteSpelers() {
        return speler.besteSpelers();
    }

    // Geeft de lijst van pilaren terug
    public List<Pilaar> getPilaren() {
        return pilaren;
    }

    // Geeft het vogel object terug
    public Bird getBird() {
        return bird;
    }

    public Circle getCircle() {
        return bird.getVogel();
    }

    // Geeft het grenzen object terug
    public Grenzen getGrenzen() {
        return grenzen;
    }

    public double getSnelheid() {
        return pilaren.get(0).getSnelheid();
    }

    public double getPilaarBreedte() {
        return pilaren.get(0).getDikte();
    }

}