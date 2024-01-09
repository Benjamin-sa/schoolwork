package be.inf1.flappybird2.model;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bird {
    

    
    private Circle vogel;
    private double snelheid = 0;
    private double zwaartekracht = 0.2;
    private double startX, startY;
    private Color kleur;
    private List<Image> vogelAnimatie;
    private int fotoIndex;

    public Bird() {
        vogel = new Circle();
        vogel.setFill(kleur);
    }

    public void setEersteX(double x) {
        vogel.setCenterX(x);
        this.startX = x;
    }
    
    public void setEersteY(double y) {
        vogel.setCenterY(y);
        this.startY = y;
    }

    public void setRadius(double radius) {
        vogel.setRadius(radius);
    }

    public void setKleur(Color kleur) {
        vogel.setFill(kleur);
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


    public Circle getVogel() {
        return vogel;
    }

    public Image getFoto() {
        return vogelAnimatie.get(fotoIndex);
    }

    public void flap(){
        double y = vogel.getCenterY();
        vogel.setCenterY(y - 5);
        snelheid = -5;
        fotoIndex = (fotoIndex + 1) % vogelAnimatie.size();
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

