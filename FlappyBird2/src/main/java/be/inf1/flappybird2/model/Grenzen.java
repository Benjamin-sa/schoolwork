package be.inf1.flappybird2.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import be.inf1.flappybird2.BirdFXMLController;


public class Grenzen {

    private double x, y, breedte, hoogte;
    private Color kleur = Color.RED; // De kleur van de grenzen

    public Grenzen() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getBreedte() {
        return breedte;
    }

    public void setBreedte(double breedte) {
        this.breedte = breedte;
    }

    public double getHoogte() {
        return hoogte;
    }

    public void setHoogte(double hoogte) {
        this.hoogte = hoogte;
    }



    public Color getKleur() {
        return kleur;
    }

   
}