package be.inf1.flappybird2.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Grenzen {
    private Rectangle bovenGrens;
    private Rectangle onderGrens;
    private double x, y;
    private Color kleur = Color.RED; // De kleur van de grenzen
    private double dikte = 10;

    public Grenzen() {
        bovenGrens = new Rectangle();
        onderGrens = new Rectangle();
    }

    public void setX(double x) {
        this.x = x;
        bovenGrens.setX(x);
        onderGrens.setX(x);
    }

    public void setY(double y) {
        this.y = y;
        bovenGrens.setY(y);
    }

    public void setPaneelBreedte(double paneelBreedte) {
        bovenGrens.setWidth(paneelBreedte);
        onderGrens.setWidth(paneelBreedte);
    }

    public void setPaneelHoogte(double paneelHoogte) {
        onderGrens.setY(paneelHoogte - dikte);
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
        bovenGrens.setFill(kleur);
        onderGrens.setFill(kleur);
    }

    public void setDikte(double dikte) {
        this.dikte = dikte;
        bovenGrens.setHeight(dikte);
        onderGrens.setHeight(dikte);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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