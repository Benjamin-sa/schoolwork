/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.flappybird2.model;

import java.util.Random;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jahma
 */
public class Pilaar {

    
        private double x = 1; 
        private double yBoven, yOnder;
        private Color kleur = Color.GREEN; // De kleur van de pilaren
        private static final double opening = 100; // De opening tussen de pilaren
        private double breedte = 50;
        private double snelheid = 0.00000000000000000000000000002;
        private double paneelHoogte;


        

        public Pilaar() {  
    }
        


    public void berekenPosities( double paneelhoogte) {

            yBoven = Math.random() * (paneelHoogte - opening);
            yOnder = yBoven +   opening;
        }


    public void setX(double xPos){
        this.x = xPos;  
    }

    public double getX(){
        return x;
    }

    public void setPaneelHoogte(double paneelHoogte) {
        this.paneelHoogte = paneelHoogte;
    }

    public void setKleur(Color kleur){
        this.kleur = kleur;
    }

    public Color getKleur(){
        return kleur;
    }


    public void setYBoven(double yPos){
        this.yBoven = yPos;
    }

    public double getYBoven(){
        return yBoven;
    }

    public void setYOnder(double yPos){
        this.yOnder = yPos;
    }

    public double getYOnder(){
        return yOnder;
    }

    public double getOpening(){
        return opening;
    }


    public void update(double paneelHoogte, double paneelBreedte) {
        // Beweeg de pilaar naar links
        x -= snelheid;

        // Als de pilaar volledig uit het zicht is verdwenen...
        if (x < -breedte) {
            // ... verplaats het naar de rechterkant van het scherm ...
            x = paneelBreedte;
            // ... en geef het een nieuwe willekeurige y-positie
            berekenPosities(paneelHoogte);
        }
    }

        

        
        

         

        


    
    
    
    
    
    
    
    
}
