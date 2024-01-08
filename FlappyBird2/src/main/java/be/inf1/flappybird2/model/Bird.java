package be.inf1.flappybird2.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import be.inf1.flappybird2.view.ViewGame;

public class Bird {
    private Circle vogel;
    private double xCoord;
    private double yCoord;
    private double xEen;
    private double yEen;
    private AnchorPane paneel;

    public Bird(double x, double y, double radius, AnchorPane paneel) {
        this.vogel = new Circle(x, y, radius);
        this.vogel.setCenterX(x);
        this.vogel.setCenterY(y);
        this.xCoord = x;
        this.yCoord = y;
        this.xEen = x;
        this.yEen = y;
    
        this.paneel = paneel;
        
    }

    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(double x) {
        this.xCoord = x;
        this.vogel.setCenterX(x);
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(double y) {
        this.yCoord = y;
        this.vogel.setCenterY(y);
    }

    public double getCenterumx() {
        return vogel.getCenterX();
    }

    public double getCenterumy() {
        return vogel.getCenterY();
    }

    public Circle getVogel() {
        return vogel;
    }

    public void resetVogel(){
        this.vogel.setCenterX(xEen);
        this.vogel.setCenterY(yEen);
    }
}

