package main.controlleur;

import java.util.List;

import main.modele.Carte;
import main.modele.Incendie;
import main.modele.robot.*;

/**
 * Classe de gestion des données de la simulation
 */
public class DonneesSimulation {

    private Carte carte; // la carte
    private List<Incendie> incendies; // la liste des incendies
    private List<Robot> robots; // la liste des robots

    /**
     * Constructeur de la classe DonneesSimulation
     * @param carte la carte
     * @param incendies la liste des incendies
     * @param robots la liste des robots
     */
    public DonneesSimulation(Carte carte, List<Incendie> incendies, List<Robot> robots) {
        this.carte = carte;
        this.incendies = incendies;
        if (robots.isEmpty()){
            throw new IllegalArgumentException("Il faut au moins un robot");
        }
        this.robots = robots;
    }

    /**
     * Getter de la carte
     * @return la carte
     */
    public Carte getCarte() {
        return carte;
    }

    /**
     * Getter de la liste des incendies
     * @return la liste des incendies
     */
    public List<Incendie> getIncendies() {
        return incendies;
    }

    /**
     * Getter de la liste des robots
     * @return la liste des robots
     */
    public List<Robot> getRobots() {
        return robots;
    }

}
