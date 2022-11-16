package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;

import java.util.*;

/**
 * Classe singleton qui gère la distribution des robots aux incendies
 */
public final class ChefRobot {
    private static ChefRobot instance;

    public NavigationStrategy strategy; // stratégie de navigation
    public boolean notif;
    public Queue<Chemin> chemins; // liste ordonnée des chemins à parcourir

    private ChefRobot(NavigationStrategy strategy) {
        this.strategy = strategy;
        this.notif = false;
        this.chemins = new PriorityQueue<>(Chemin.Comparators.DURATION);
    }

    public static ChefRobot getInstance() {
        if (instance == null) {
            instance = new ChefRobot(new NavigationStrategy1());
        }
        return instance;
    }

    public void setStrategy(NavigationStrategy strategy) {
        this.strategy = strategy;
    }

    public void initDistribution(DonneesSimulation donneesSimulation) {
        strategy.fillChemins(chemins, donneesSimulation);
        strategy.distribution(chemins);
        notif = false;
    }

    /**
     * Appel après que notify == true, supprime les chemins qui servent plus à rien.
     */
    public void updateChemins(DonneesSimulation donneesSimulation) {
        if (notif) {
            // si chemin est obsolète, robot vide ou incendie éteint, on le supprime
            chemins.removeIf(chemin ->
                    !chemin.getStart().equals(chemin.getRobot().getPosition()) ||
                            !chemin.getEnd().equals(chemin.getIncendie().getPosition()) ||
                            chemin.getRobot().isEmpty() || chemin.getIncendie().isEteint()
            );
            strategy.fillChemins(chemins, donneesSimulation);
            strategy.distribution(chemins);
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
