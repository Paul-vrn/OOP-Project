package main.controlleur;

import java.util.List;

import main.modele.Carte;
import main.modele.Incendie;
import main.modele.robot.*;

public class DonneesSimulation {

    private Carte carte;
    private List<Incendie> incendies;
    private List<Robot> robots;

    public DonneesSimulation(Carte carte, List<Incendie> incendies, List<Robot> robots) {
        this.carte = carte;
        this.incendies = incendies;
        if (robots.isEmpty()){
            throw new IllegalArgumentException("Il faut au moins un robot");
        }
        this.robots = robots;
    }

    public Carte getCarte() {
        return carte;
    }

    public List<Incendie> getIncendies() {
        return incendies;
    }

    public List<Robot> getRobots() {
        return robots;
    }

}
