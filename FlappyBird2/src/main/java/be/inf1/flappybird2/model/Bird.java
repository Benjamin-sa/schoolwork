package be.inf1.flappybird2.model;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bird {
    

    
    private Circle vogel;
    private double sprongKracht = -4;
    private double snelheid = 0;
    private double zwaartekracht = 0.2;
    private double rotatieHoek;
    private double startY, startX;
    private double dy;
    private Color kleur;



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

    public void setSprongkracht(double sprongKracht) {
        this.sprongKracht = sprongKracht;
    }

    public double getSprongkracht() {
        return sprongKracht;
    }

    public Circle getVogel() {
        return vogel;
    }

    public void flap(){
        snelheid = sprongKracht;
    }

    public void val(){
        double y = vogel.getCenterY();
        snelheid += zwaartekracht;
        vogel.setCenterY(y + snelheid);

        //verandering in y
        dy = y - (y + snelheid);

        // Copilot code
        rotatieHoek = Math.toDegrees(Math.atan2(dy, 1));
    }

    public double getRotatieHoek(){
        return rotatieHoek;
    }
 

    public void reset(){
        vogel.setCenterX(startX);
        vogel.setCenterY(startY);
        snelheid = 0;
    }

}

