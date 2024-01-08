package be.inf1.flappybird2.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bird {
    



    private double snelheid = 0;
    private double zwaartekracht = 0.2;
    private double x = 100;
    private double y = 10;
    private double radius = 10;

    public Bird() {
    }

    public double getxCoord() {
        return x;
    }

    public void setxCoord(double x) {
        this.x = x;
    }

    public double getyCoord() {
        return y;
    }

    public void setyCoord(double y) {
        this.y = y;
    }

    public Color getKleur() {
        return Color.RED;
    }

    public double getRadius() {
        return radius;
    }


    public void update() {
        snelheid += zwaartekracht;
        setyCoord(getyCoord() + snelheid);
    }

    public void flap() {
        snelheid = -5;
    }

}

