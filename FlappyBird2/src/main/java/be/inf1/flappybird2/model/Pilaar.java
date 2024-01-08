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

        private double x;
        private double yBoven, yOnder;
        private Color kleur = Color.GREEN;
        private Rectangle bovenPilaar;
        private Rectangle onderPilaar;
        private double opening;
        private double dikte = 20;
        private boolean isVoorbij = false;


        public Pilaar(double x, double opening, double dikte, double paneelHoogte, Color kleur) {
            Random rand = new Random();
            double yBoven = rand.nextDouble() * (paneelHoogte - opening);
            bovenPilaar = new Rectangle(x, 0, dikte, yBoven);
            bovenPilaar.setFill(kleur);
        
            double yOnder = yBoven + opening;
            double hoogteOnder = paneelHoogte - yOnder;
            onderPilaar = new Rectangle(x, yOnder, dikte, hoogteOnder);
            onderPilaar.setFill(kleur);
        }


    public Rectangle getBovenPilaar() {
            return bovenPilaar;
    }

    public Rectangle getOnderPilaar() {
            return onderPilaar;
    }
        
    public void setX(double xPos){
        bovenPilaar.setX(xPos);
        onderPilaar.setX(xPos);  
    }

    public double getX(){
        return bovenPilaar.getX();
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

    public double getDikte(){
        return dikte;
    } 

    public boolean isVoorbij(){
        return isVoorbij;
    }

    public void setVoorbij(boolean isVoorbij){
        this.isVoorbij = isVoorbij;
    }



    
}
