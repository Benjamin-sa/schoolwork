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

    
    

        
        private Rectangle bovenPilaar;
        private Rectangle onderPilaar;

        private double openingHoogte = 100; // De hoogte van de opening tussen de pilaren
        int breedte = 10; // De breedte van de pilaren
        private Color kleur = Color.GREEN; // De kleur van de pilaren



        

        public Pilaar(double Xpos, AnchorPane paneel) {

            Random rand = new Random();
            double openingY = Math.max(rand.nextDouble() * (paneel.getHeight() - openingHoogte), 50);
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

    public double getX(){
        return bovenPilaar.getX();
    }

        

        
        

         

        


    
    
    
    
    
    
    
    
}
