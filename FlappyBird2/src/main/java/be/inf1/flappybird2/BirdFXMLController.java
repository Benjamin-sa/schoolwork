package be.inf1.flappybird2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import be.inf1.flappybird2.model.Pilaar;
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
    private Button speelSpel;

    @FXML
    private AnchorPane paneel;

    @FXML
    private Label score;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label levelCounter;

    @FXML
    private Button startKnop;

    @FXML
    private TextArea spelerNaam;

    @FXML
    private VBox menu;

    @FXML
    private HBox hBox;

    @FXML
    private ListView<String> lijst;

    @FXML
    private ImageView foto1;

    @FXML
    private ImageView foto2;

    @FXML
    private ImageView vogelUp;

    @FXML
    private ImageView vogelMid;

    @FXML
    private ImageView vogelDown;

    private Controller gameController;

    public BirdFXMLController() {

    }

    @FXML
    void initialize() {

        paneel.setVisible(false);
        startKnop.setVisible(false);
        vogelDown.setVisible(false);
        vogelMid.setVisible(false);

        // Vertraging wegens trage paneel rendering
        Duration delay = Duration.seconds(1);
        Timeline timeline = new Timeline(new KeyFrame(delay, event -> {
            // gameController initialiseren
            gameController = new Controller(this);
            // beste spelers updaten
            updateBesteSpelers();

            foto1.setLayoutX(0);
            foto1.setLayoutY(0);
            foto1.setFitHeight(paneel.getHeight());
            foto1.setFitWidth(paneel.getWidth());

            foto2.setLayoutX(paneel.getWidth());
            foto2.setLayoutY(0);
            foto2.setFitHeight(paneel.getHeight());
            foto2.setFitWidth(paneel.getWidth());
        }));
        timeline.play();

        speelSpel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menu.setVisible(false);
                hBox.setVisible(false);
                paneel.setVisible(true);
                startKnop.setVisible(true);
                plaatsElementen();
                paneel.requestFocus();
                gameController.setSpelerNaam(spelerNaam.getText());
            }
        });

        startKnop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!gameController.isGameGestart()) {
                    gameController.startGame();

                if(gameController.isGameGestart()){
                    gameController.resetGame();
                }
            }
                paneel.requestFocus();
            }
        });

        paneel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE) {

                    if (gameController.isGameGestart()) {
                        gameController.flap();

                        // Roteer tussen de vogelafbeeldingen
                        if (vogelUp.isVisible()) {
                            vogelMidZichtbaar();
                        } else if (vogelMid.isVisible()) {
                            vogelDownZichtbaar();
                        } else {
                            vogelUpZichtbaarr();
                        }
                    }
                    paneel.requestFocus();
                }
            }
        });

        paneel.setFocusTraversable(true);
    }

    public void plaatsElementen() {
        for (Pilaar pilaar : gameController.getPilaren()) {
            Rectangle bovenPilaar = pilaar.getBovenPilaar();
            Rectangle onderPilaar = pilaar.getOnderPilaar();
            paneel.getChildren().addAll(bovenPilaar, onderPilaar);
        }

        Grenzen grenzen = gameController.getGrenzen();
        Rectangle bovenGrens = grenzen.getBovenGrens();
        Rectangle onderGrens = grenzen.getOnderGrens();

        Bird bird = gameController.getBird();
        Circle vogel = bird.getVogel();
        paneel.getChildren().addAll(bovenGrens, onderGrens, vogel);
    }

    public void scrollendeAchtergrond() {

        double snelheidsverhouding = gameController.getPilaarBreedte() / (paneel.getWidth());
        double aangepasteSnelheid = gameController.getSnelheid() * snelheidsverhouding;
        foto1.setLayoutX(foto1.getLayoutX() - aangepasteSnelheid);
        foto2.setLayoutX(foto2.getLayoutX() - aangepasteSnelheid);

        if (foto1.getLayoutX() + foto1.getFitWidth() <= 0) {
            foto1.setLayoutX(foto2.getLayoutX() + foto2.getFitWidth());
        }
        if (foto2.getLayoutX() + foto2.getFitWidth() <= 0) {
            foto2.setLayoutX(foto1.getLayoutX() + foto1.getFitWidth());
        }
    }

    public void updateBesteSpelers() {
        List<SpelerGegevens> besteSpelers = gameController.getBesteSpelers();

        List<String> besteSpelerLijst = new ArrayList<>();

        for (SpelerGegevens speler : besteSpelers) {
            besteSpelerLijst.add(speler.getNaam() + "score = " + speler.getHighScore());
        }

        lijst.setItems(FXCollections.observableArrayList(besteSpelerLijst));
    }

    public void updateVogelAnimatie() {
        double diameter = gameController.getCircle().getRadius() * 2;

        vogelUp.setFitWidth(diameter);
        vogelUp.setFitHeight(diameter);
        vogelUp.setLayoutX(gameController.getBird().getxCoord() - diameter / 2);
        vogelUp.setLayoutY((gameController.getBird().getyCoord() - diameter / 2));
        System.out.println(gameController.getBird().getyCoord());
        System.out.println(gameController.getBird().getyCoord());

        vogelMid.setFitWidth(diameter);
        vogelMid.setFitHeight(diameter);
        vogelMid.setLayoutX(gameController.getBird().getxCoord() - diameter / 2);
        vogelMid.setLayoutY(gameController.getBird().getyCoord() - diameter / 2);

        vogelDown.setFitWidth(diameter);
        vogelDown.setFitHeight(diameter);
        vogelDown.setLayoutX(gameController.getBird().getxCoord() - diameter / 2);
        vogelDown.setLayoutY(gameController.getBird().getyCoord() - diameter / 2);

    }

    public void vogelUpZichtbaarr() {
        vogelUp.setVisible(true);
        vogelMid.setVisible(false);
        vogelDown.setVisible(false);
    }

    public void vogelMidZichtbaar() {
        vogelUp.setVisible(false);
        vogelMid.setVisible(true);
        vogelDown.setVisible(false);
    }

    public void vogelDownZichtbaar() {
        vogelUp.setVisible(false);
        vogelMid.setVisible(false);
        vogelDown.setVisible(true);
    }

    public void updateScore(int scoreWaarde) {
        score.setText(Integer.toString(scoreWaarde));
    }

    public void updateLevel(int levelWaarde) {
        levelCounter.setText(Integer.toString(levelWaarde));
    }

    public void updateHighScore(int highScoreWaarde) {
        highScore.setText(Integer.toString(highScoreWaarde));
    }

    public double getPaneelHoogte() {
        return paneel.getHeight();
    }

    public double getPaneelBreedte() {
        return paneel.getWidth();
    }
}
