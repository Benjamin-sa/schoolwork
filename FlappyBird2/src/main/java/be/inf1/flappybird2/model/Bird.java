/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.flappybird2.model;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import be.inf1.flappybird2.view.ViewGame;
import be.inf1.flappybird2.BirdFXMLController;
import be.inf1.flappybird2.model.Pilaar;
import java.util.List;

/**
 *
 * @author Jahma
 */
public class Bird {
    private int xCoordBird;
    private int yCoordBird;
    private int dy;
    private Circle circle;
    private static final double zwaartekracht = 1.5;
    private ViewGame viewGame;
    private BirdFXMLController BirdFXMLController;

  


    public Bird(int xCoord, int yCoord, ViewGame viewGame, BirdFXMLController BirdFXMLController, Pilaar rectangle) {
        this.xCoordBird = xCoord;
        this.yCoordBird =yCoord;
        this.circle = new Circle();
        this.viewGame = viewGame;
        this.BirdFXMLController = BirdFXMLController;
    }

    /**
     * @return the xCoord
     */
    public int getxCoord() {
        return xCoordBird;
    }

    public float getBreedte() {
        return 10;
    }
    
    public void reset(){
        this.yCoordBird=90;
    }
    
  

    /**
     * @return the yCoord
     */
    public int getyCoord() {
        return yCoordBird;
    }
    
  
    

    /**
     * @param yCoord the yCoord to set
     */
   

    public void omLaagGaan() {
        circle.setCenterY(circle.getCenterY() - zwaartekracht);


    }
    
        

    
    
    
    public void tick(){
        yCoordBird=yCoordBird+dy;
    }
    
    


    /**
     * @return the dy
     */
    public void setDyNul() {
        this.dy=0;
    }
    
    /**
     * @return the dy
     */
    public void setDyAan() {
        this.dy=4;
        
    
    }


    /**
     * @param volgendeKlik the volgendeKlik to set
     */
    

    public void zakken() {
        this.yCoordBird -= 1  ;
    }
    
    public void update(){
        this.yCoordBird += dy;
        
    }
    
    
    
    
    

    
    
    
}
