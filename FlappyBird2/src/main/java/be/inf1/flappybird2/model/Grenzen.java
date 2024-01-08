package be.inf1.flappybird2.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import be.inf1.flappybird2.BirdFXMLController;


public class Grenzen {

    private Rectangle bovenGrens;
    private Rectangle onderGrens;
    private double x, y;
    private Color kleur = Color.RED; // De kleur van de grenzen
    private double dikte = 10;

    public Grenzen(double x, double y, double paneelBreedte, double paneelHoogte, Color kleur, double dikte) {
        bovenGrens = new Rectangle(x, y, paneelBreedte, dikte);
        bovenGrens.setFill(kleur);

        onderGrens = new Rectangle(x, paneelHoogte - dikte, paneelBreedte, dikte);
        onderGrens.setFill(kleur);
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

    public double getHoogte() {
        return dikte;
    }

    public void setHoogte(double dikte) {
        this.dikte = dikte;
    }
    public Color getKleur() {
        return kleur;
    }

    public Rectangle getBovenGrens() {
        return bovenGrens;
    }

    public Rectangle getOnderGrens() {
        return onderGrens;
    }

   
}