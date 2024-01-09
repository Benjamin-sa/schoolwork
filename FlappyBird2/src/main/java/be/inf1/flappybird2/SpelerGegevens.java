package be.inf1.flappybird2;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class SpelerGegevens {
    String naam;
    int highScore;

    SpelerGegevens(String naam, int highScore) {
        this.naam = naam;
        this.highScore = highScore;
    }

    public void gegevensOpslaan(String naam, int highScore){
        SpelerGegevens speler = new SpelerGegevens(naam, highScore);
        Gson gson = new Gson();
        String Json = gson.toJson(speler);
    }
}