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
public class ViewGame {
    private AnchorPane paneel;
    private BirdFXMLController BirdFXMLController;
    private double openingHoogte = 100; // De hoogte van de opening tussen de pilaren
    private GraphicsContext gc;
    private List<Pilaar> pilaren = new ArrayList<>();
    private Rectangle bovenGrens;
    private Rectangle onderGrens;
    





    public ViewGame(AnchorPane paneel, BirdFXMLController BirdFXMLController) {
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

    public void tekenBorders() {
        // Initialize bovenGrens and onderGrens
    bovenGrens = new Rectangle(); // 10 is the thickness of the border
    bovenGrens.heightProperty().set(10);
    bovenGrens.widthProperty().bind(paneel.widthProperty());
    bovenGrens.setFill(Color.RED); // The color of the border

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
        double Xpos = 20;
    
        // Maak een nieuwe Random om de hoogte van de opening te bepalen
        Random rand = new Random();
    
        // Maak een loop om meerdere pilaren te maken
        for (int i = 0; i < 30; i++) {
            // Bereken de y-positie van de opening
            double openingY = Math.max(rand.nextDouble() * (paneel.getHeight() - openingHoogte), 50);    
            // Maak het bovenste deel van de pilaar
            Rectangle bovenPilaar = new Rectangle(Xpos, 0, breedte, openingY);
            bovenPilaar.setFill(Color.GREEN);
    
            // Maak het onderste deel van de pilaar
            double onderPilaarY = openingY + openingHoogte;
            Rectangle onderPilaar = new Rectangle(Xpos, onderPilaarY, breedte, paneel.getHeight() - onderPilaarY);
            onderPilaar.setFill(Color.GREEN);
    
            Pilaar pilaar = new Pilaar(bovenPilaar);
            Pilaar pilaar2 = new Pilaar(onderPilaar);
    
            pilaren.add(pilaar);
            pilaren.add(pilaar2);
            paneel.getChildren().addAll(bovenPilaar, onderPilaar);
    
            // Verhoog Xpos voor de volgende pilaar
            Xpos += 200;
        }
    
        
    
        
    }


    public void reset() {
        pilaren.clear();

        paneel.getChildren().removeIf(node -> node instanceof Rectangle && Color.GREEN.equals(((Rectangle) node).getFill()));

        tekenPilaren();
    }

    

}
    
    
    

