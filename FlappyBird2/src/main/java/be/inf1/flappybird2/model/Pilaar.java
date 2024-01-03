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

    
    

        
        private Rectangle rectangle;
        private Rectangle boven;
        private Rectangle onder;
        public Pilaar(double breedte, double hoogte, double x, double y) {
            this.rectangle = new Rectangle(breedte, hoogte, Color.GREEN);
            this.rectangle.setX(x);
            this.rectangle.setY(y);
            this.boven = new Rectangle(breedte, hoogte, Color.GREEN);
            this.boven.setX(x);
            this.boven.setY(y);
            this.onder = new Rectangle(breedte, hoogte, Color.GREEN);
            this.onder.setX(x);
            this.onder.setY(y);
        }

        public Rectangle getBoven() {
            return boven;
        }

        public void setBoven(Rectangle boven) {
            this.boven = boven;
        }

        public Rectangle getOnder() {
            return onder;
        }

        public void setOnder(Rectangle onder) {
            this.onder = onder;
        }

        public Rectangle getRectangle() {
            return this.rectangle;
        }
        
        public void getBreedte(){
            
        }


    
    
    
    
    
    
    
    
}
