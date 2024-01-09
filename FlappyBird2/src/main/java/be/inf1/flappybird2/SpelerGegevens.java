package be.inf1.flappybird2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class SpelerGegevens {
    private  String naam;
    private int highScore;

    public SpelerGegevens() { 
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void saveData() {
    
    Gson gson = new Gson();

    List<SpelerGegevens> spelerGegevensLijst = new ArrayList<>();
    File file = new File("GegevensSpeler.json");
    if (file.exists()) {
        try (FileReader lezen = new FileReader(file)) {
            Type type = new TypeToken<List<SpelerGegevens>>(){}.getType();
            spelerGegevensLijst = gson.fromJson(lezen, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // nieuwe toevoegen
    spelerGegevensLijst.add(this);

    // sorteren
    spelerGegevensLijst.sort(Comparator.comparing(SpelerGegevens::getHighScore).reversed());

    // opslaan
    String json = gson.toJson(spelerGegevensLijst);
    try (FileWriter schrijven = new FileWriter(file)) {
        schrijven.write(json);
    } catch (IOException e) {
        e.printStackTrace();
    }
}   

    public List<SpelerGegevens> besteSpelers(){
        Gson gson = new Gson();
        Type type = new TypeToken<List<SpelerGegevens>>(){}.getType();
        List<SpelerGegevens> spelerGegevensLijst = new ArrayList<>();

        try (FileReader reader = new FileReader("GegevensSpeler.json")) {
            spelerGegevensLijst = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        spelerGegevensLijst.sort(Comparator.comparing(SpelerGegevens::getHighScore).reversed());

        return spelerGegevensLijst.subList(0, Math.min(spelerGegevensLijst.size(), 13));
        
    }


    
}
