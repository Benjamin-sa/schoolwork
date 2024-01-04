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
        

        public Pilaar(Rectangle pilaar) {
        
            this.pilaar = pilaar;
            pilaar.setTranslateX(150);

            

            
        }

        

        public Rectangle getPilaar() {
            return pilaar;
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

        

        public double getx() {
            return pilaar.getX();
        }

        public double gety() {
            return pilaar.getY();
        }

         

        


    
    
    
    
    
    
    
    
}
