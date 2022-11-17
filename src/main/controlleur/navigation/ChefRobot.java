package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;

import java.util.*;

/**
 * Classe singleton qui gère la distribution des robots aux incendies
 */
public final class ChefRobot {
    private static ChefRobot instance; // instance unique de la classe

    public NavigationStrategy strategy; // stratégie de navigation
    public boolean notif; // notification de fin de simulation
    public PriorityQueue<Chemin> chemins; // liste ordonnée des chemins à parcourir
    public int n; // le nombre de pas par seconde

    /**
     * Constructeur privé pour le singleton
     *
     * @param strategy stratégie de navigation
     */
    private ChefRobot(NavigationStrategy strategy) {
        this.strategy = strategy;
        this.notif = false;
        this.chemins = new PriorityQueue<>(Comparator.comparingInt(Chemin::getDuration));
    }

    /**
     * Création du singleton
     *
     * @return instance du singleton
     */
    public static ChefRobot getInstance() {
        if (instance == null) {
            instance = new ChefRobot(new NavigationStrategy1());
        }
        return instance;
    }

    /**
     * Setter de la stratégie de navigation
     *
     * @param strategy stratégie de navigation
     */
    public void setStrategy(NavigationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Initialise la distribution des robots aux incendies
     *
     * @param donneesSimulation données de la simulation
     */
    public void initDistribution(DonneesSimulation donneesSimulation) {
        strategy.fillChemins(donneesSimulation);
        strategy.distribution();
        notif = false;
    }

    /**
     * Met à jour les chemins
     *
     * @param donneesSimulation données de la simulation
     */
    public void updateChemins(DonneesSimulation donneesSimulation) {
        if (notif) {
            // si chemin est obsolète, robot vide ou incendie éteint, on le supprime
            strategy.fillChemins(donneesSimulation);
            strategy.distribution();
            System.out.println("chemins updated");
            System.out.println(chemins);
            notif = false;
        }
    }

    /**
     * Reset la distribution le singleton
     */
    public void reset() {
        chemins.clear();
        notif = false;
    }
}
