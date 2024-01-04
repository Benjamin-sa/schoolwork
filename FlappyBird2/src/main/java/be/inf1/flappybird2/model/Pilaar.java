/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.flappybird2.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jahma
 */
public class Pilaar {

    
    

        
        private Rectangle pilaar;
        private boolean isVoorbij = false;
        

        public Pilaar(Rectangle pilaar) {
        
            this.pilaar = pilaar;

            
        }

        

        public Rectangle getPilaar() {
            return pilaar;
        }

        public void setPilaar(Rectangle pilaar) {
            this.pilaar = pilaar;
        }

        public boolean isVoorbij() {
            return isVoorbij;
        }

        public void setIsVoorbij(boolean isVoorbij) {
            this.isVoorbij = isVoorbij;
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
