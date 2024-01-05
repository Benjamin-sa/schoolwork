/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.flappybird2.model;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jahma
 */
public class Pilaar {

    
    

        
        private Rectangle pilaar;
        private boolean voorbijGevlogen = false;
        Rectangle bovenPilaar;
        Rectangle onderPilaar;
        private double x;
        

        public Pilaar(Rectangle bovenPilaar, Rectangle onderPilaar, double x) {
        
            this.bovenPilaar = bovenPilaar;
            this.onderPilaar = onderPilaar;
            this.x = x;
            this.pilaar = new Rectangle(x, 0, bovenPilaar.getWidth(), onderPilaar.getHeight() + bovenPilaar.getHeight());
            pilaar.setTranslateX(x);


            

            
        }

        

        public Rectangle getBovenPilaar() {
            return bovenPilaar;
        }

        public Rectangle getOnderPilaar() {
            return onderPilaar;
        }

        public boolean isVoorbijGevlogen() {
            return voorbijGevlogen;
        }

        public void setVoorbijGevlogen(boolean voorbijGevlogen) {
            this.voorbijGevlogen = voorbijGevlogen;
        }

        public void setPilaar(Rectangle pilaar) {
            this.pilaar = pilaar;
        }

    
        
        public double getBreedte(){
            return pilaar.getWidth();   
        } 

        public double getHoogte(){
            return pilaar.getHeight();
        }

        

        public double getX() {
            return this.x;
        }

        public void setX(double x) {
            this.x = x;
        
            // Update de x-waarde van de Rectangle objecten
            this.bovenPilaar.setX(x);
            this.onderPilaar.setX(x);
        }

        

         

        


    
    
    
    
    
    
    
    
}
