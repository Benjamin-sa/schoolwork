/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.flappybird2.model;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jahma
 */
public class Pilaar {

    
    

        
        private Rectangle bovenPilaar;
        private Rectangle onderPilaar;
        private AnchorPane paneel;
        

        public Pilaar(double Xpos, double openingY, double openingHoogte, double breedte, Color kleur, AnchorPane paneel) {
            bovenPilaar = new Rectangle(Xpos, 0, breedte, openingY);
            bovenPilaar.setFill(kleur);

            double onderPilaarY = openingY + openingHoogte;
            onderPilaar = new Rectangle(Xpos, onderPilaarY, breedte, paneel.getHeight() - onderPilaarY);
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

        

        
        

         

        


    
    
    
    
    
    
    
    
}
