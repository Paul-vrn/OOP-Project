package main;

import java.util.ArrayList;
import main.robot.*;

public class DonneesSimulation {

    private Carte carte;
    private ArrayList<Incendie> incendies;
    private ArrayList<Robot> robots;

    public DonneesSimulation(Carte carte, ArrayList<Incendie> incendies, ArrayList<Robot> robots) {
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public ArrayList<Incendie> getIncendies() {
        return incendies;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

}
