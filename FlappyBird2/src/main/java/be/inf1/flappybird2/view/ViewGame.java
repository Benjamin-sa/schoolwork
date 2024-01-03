package be.inf1.flappybird2.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.BirdFXMLController;
import be.inf1.flappybird2.model.Pilaar;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
public class ViewGame {
    private Pane paneel;
    private double grensHoogte;
    private double openingHoogte = 100; // De hoogte van de opening tussen de pilaren
    private Canvas canvas;
    private GraphicsContext gc;
    private List<Pilaar> pilaren = new ArrayList<>();

    public ViewGame(Pane paneel) {
        this.paneel = paneel;
        this.pilaren = new ArrayList<>();

        
    }

    public List<Pilaar> getPilaren() {
        return pilaren;
    }


    public void tekenPilaren() {
        int breedte = 20;
        double Xpos = 100;
    
        // Maak een nieuwe Random om de hoogte van de opening te bepalen
        Random rand = new Random();
    
        // Maak een loop om meerdere pilaren te maken
        for (int i = 0; i < 5; i++) {
            // Bereken de y-positie van de opening
            double openingY = rand.nextDouble() * (paneel.getHeight() - openingHoogte);
    
            // Maak het bovenste deel van de pilaar
            Rectangle bovenPilaar = new Rectangle(Xpos, 0, breedte, openingY);
            bovenPilaar.setFill(Color.GREEN);
    
            // Maak het onderste deel van de pilaar
            Rectangle onderPilaar = new Rectangle(Xpos, openingY + openingHoogte, breedte, paneel.getHeight() - openingY - openingHoogte);
            onderPilaar.setFill(Color.GREEN);
    
            // Voeg de pilaren toe aan het paneel
            paneel.getChildren().addAll(bovenPilaar, onderPilaar);
    
            // Voeg de pilaren toe aan de lijst
            pilaren.add(new Pilaar(Xpos, openingY, breedte, openingHoogte));
    
            // Verhoog Xpos voor de volgende pilaar
            Xpos += 200;
        }
    }

    

        public void startPilaren() {
    for (Pilaar pilaar : pilaren) {
        // Create a TranslateTransition for the top pillar
        TranslateTransition ttTop = new TranslateTransition(Duration.seconds(10), pilaar.getBoven());
        ttTop.setByX(-200); // Move the pillar 200 pixels to the left
        ttTop.setCycleCount(Animation.INDEFINITE); // Repeat the animation indefinitely

        // Create a TranslateTransition for the bottom pillar
        TranslateTransition ttBottom = new TranslateTransition(Duration.seconds(10), pilaar.getOnder());
        ttBottom.setByX(-200); // Move the pillar 200 pixels to the left
        ttBottom.setCycleCount(Animation.INDEFINITE); // Repeat the animation indefinitely

        // Start the animations
        ttTop.play();
        ttBottom.play();
    }

    // Create a Timeline that checks if the pillars are out of sight every frame
    Timeline checkPillars = new Timeline(new KeyFrame(Duration.seconds(20), e -> {
        for (Pilaar pilaar : pilaren) {
            // If the top pillar is completely out of sight, reset its x-position to the right side of the screen
            if (pilaar.getBoven().getTranslateX() + pilaar.getBoven().getWidth() < 0) {
                pilaar.getBoven().setTranslateX(paneel.getWidth());
            }

            // If the bottom pillar is completely out of sight, reset its x-position to the right side of the screen
            if (pilaar.getOnder().getTranslateX() + pilaar.getOnder().getWidth() < 0) {
                pilaar.getOnder().setTranslateX(paneel.getWidth());
            }
        }
    }));
    checkPillars.setCycleCount(Animation.INDEFINITE); // Repeat the Timeline indefinitely
    checkPillars.play(); // Start the Timeline
}}
    
    
    

