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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import be.inf1.flappybird2.model.Pilaar;
import be.inf1.flappybird2.model.Bird;
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



public class ViewGame  {
    private AnchorPane paneel;
    private Label highScore;
    private double score = 0;
    private BirdFXMLController BirdFXMLController;
    private double openingHoogte = 100; // De hoogte van de opening tussen de pilaren
    private List<Pilaar> pilaren = new ArrayList<>();
    private Rectangle bovenGrens;
    private Rectangle onderGrens;
    private Bird vogel;
    





    public ViewGame(AnchorPane paneel, BirdFXMLController BirdFXMLController, Label highScore) {
        this.BirdFXMLController = BirdFXMLController;
        this.paneel = BirdFXMLController.getPaneel();
        this.pilaren = new ArrayList<>();
       
    }

    public List<Pilaar> getPilaren() {
        return pilaren;
    }

    public void setPilaren(List<Pilaar> pilaren) {
        this.pilaren = pilaren;

    }

    public Bird tekenVogel(){
        vogel = new Bird(10, 125, 7, paneel);
        paneel.getChildren().add(vogel.getVogel());
        return vogel;
    }


    public Bird getVogel() {
        return vogel;
    }



    

    public void tekenBorders() {
        // Initialiseer bovenGrens en onderGrens
        bovenGrens = new Rectangle(); // 10 is de dikte van de rand
        bovenGrens.heightProperty().set(10);
        bovenGrens.widthProperty().bind(paneel.widthProperty());
        bovenGrens.setFill(Color.RED); // De kleur van de rand

        onderGrens = new Rectangle();
        onderGrens.yProperty().bind(paneel.heightProperty().subtract(10));
        onderGrens.heightProperty().set(10);
        onderGrens.widthProperty().bind(paneel.widthProperty());
        onderGrens.setFill(Color.RED);

        paneel.getChildren().addAll(bovenGrens, onderGrens);
    }

    public Rectangle getBovenGrens() {
        return bovenGrens;
    }

    public Rectangle getOnderGrens() {
        return onderGrens;
    }




    public void tekenPilaren() {
        int breedte = 10;
        double Xpos = 200;
        System.out.println(paneel.getWidth()); // Begin aan de rechterkant van het paneel
    
        // Maak een nieuwe Random om de hoogte van de opening te bepalen
        Random rand = new Random();
    
        // Maak een loop om meerdere pilaren te maken
        for (int i = 0; i < 2; i++) {
            // Bereken de y-positie van de opening
            double openingY = Math.max(rand.nextDouble() * (paneel.getHeight() - openingHoogte), 50);    
    
            Pilaar pilaar = new Pilaar(Xpos, openingY, openingHoogte, breedte, Color.GREEN, paneel);
    
            pilaren.add(pilaar);
    
            paneel.getChildren().addAll(pilaar.getBovenPilaar(), pilaar.getOnderPilaar());
    
            // Verhoog Xpos voor de volgende pilaar
            Xpos += 200;
        }
    }


    public void updateScore() {
        highScore.setText("Score: " + score); 
    }


    public void reset() {
        // Verwijder de oude pilaren
        pilaren.clear();
        paneel.getChildren().removeIf(node -> node instanceof Rectangle && Color.GREEN.equals(((Rectangle) node).getFill()));
    
        // Teken de nieuwe pilaren
        tekenPilaren();
        
    }

    

}
    
    
    

