package be.inf1.flappybird2.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bird {
    

    
    private Circle vogel;
    private double snelheid = 0;
    private double zwaartekracht = 0.2;
    private double startX, startY;

    public Bird(double x, double y, double radius, Color kleur) {
        vogel = new Circle(x, y, radius);
        vogel.setFill(kleur);
        this.startX = x;
        this.startY = y;
    }

    public double getxCoord() {
        return vogel.getCenterX();
    }

    public void setxCoord(double x) {
        vogel.setCenterX(x);
    }

    public double getyCoord() {
        return vogel.getCenterY();
    }

    public void setyCoord(double y) {
        vogel.setCenterY(y);
    }

    public Color getKleur() {
        return (Color) vogel.getFill();
    }

    public double getRadius() {
        return vogel.getRadius();
    }

    public void setRadius(double radius) {
        vogel.setRadius(radius);
    }

    public Circle getVogel() {
        return vogel;
    }

    public void flap(){
        double y = vogel.getCenterY();
        vogel.setCenterY(y - 5);
        snelheid = -5;
    }

    public void val(){
        double y = vogel.getCenterY();
        snelheid += zwaartekracht;
        vogel.setCenterY(y + snelheid);
    }

    public void reset(){
        vogel.setCenterX(startX);
        vogel.setCenterY(startY);
        snelheid = 0;
    }

}

