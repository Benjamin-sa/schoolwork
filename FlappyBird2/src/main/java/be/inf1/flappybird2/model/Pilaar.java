/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.flappybird2.model;



import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author Jahma
 */
public class Pilaar {

       private double yBoven, yOnder;
        private Rectangle bovenPilaar;
        private Rectangle onderPilaar;
        private double opening;
        private boolean isVoorbij = false;


        private double snelheid = 2;
        private double snelheidVerhoogd = 0.3;
        private double beginAfstand = 300;
        private double tussenAfstand = 200;
        private double dikte = 30;
        private double paneelHoogte;
        private Color kleur = Color.GREEN;
        private double x;




        public Pilaar() {
            
            bovenPilaar = new Rectangle(x, 0, dikte, 0);
            bovenPilaar.setFill(kleur);
        
            onderPilaar = new Rectangle(x, 0, dikte, 0);
            onderPilaar.setFill(kleur);
        }

        

    public void resetOpening(double paneelHoogte) {

        // getal tussen 0.2 en 0.8 zodat het niet bij de randen open is 
        double getalTussen02en08 = 0.2 + (0.8 - 0.2) * new Random().nextDouble();
        double yBoven = getalTussen02en08 * (paneelHoogte - opening);
        bovenPilaar.setHeight(yBoven);

        double yOnder = yBoven + opening;
        double hoogteOnder = paneelHoogte - yOnder;
        onderPilaar.setY(yOnder);
        onderPilaar.setHeight(hoogteOnder);
    }

    public void setPaneelHoogte(double paneelHoogte){
        this.paneelHoogte = paneelHoogte;
    }

    public void verHoogsnelheid(){
        snelheid += snelheidVerhoogd;
    }

    //beetje overbodig
    public double getPaneelHoogte(){
        return paneelHoogte;
    }

    public void setDikte(double dikte){
        this.dikte = dikte;
    }
    
    public double getDikte(){
        return dikte;
    }

    public double berekenBeginPositie(int i){
        return (1 + i) * tussenAfstand + beginAfstand;
    }

    public void setTussenAfstand(double tussenAfstand){
        this.tussenAfstand = tussenAfstand;
    }

    public double getTussenAfstand(){
        return tussenAfstand;
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

    public void setOpening(double opening){
        this.opening = opening;
    }

    public double getOpening(){
        return opening;
    }

    public boolean isVoorbij(){
        return isVoorbij;
    }

    public void setVoorbij(boolean isVoorbij){
        this.isVoorbij = isVoorbij;
    }

    public double getSnelheid(){
        return snelheid;
    }

    public void setSnelheid(double snelheid){
        this.snelheid = snelheid;
    }

    public void setSnelheidVerhoogd(double snelheidVerhoogd){
        this.snelheidVerhoogd = snelheidVerhoogd;
    }  

    public double getSnelheidVerhoogd(){
        return snelheidVerhoogd;
    }

    public void SetBeginAfstand(double beginAfstand){
        this.beginAfstand = beginAfstand;
    }   

    public double getBeginAfstand(){
        return beginAfstand;
    }   



    
}
