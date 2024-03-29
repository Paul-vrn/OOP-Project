package main.controlleur.navigation;

import main.controlleur.io.DonneesSimulation;

import java.util.*;

/**
 * Classe singleton qui gère la distribution des robots aux incendies
 */
public final class ChefRobot {
    private static ChefRobot instance; // instance unique de la classe

    /**
     * Stratégie de navigation {@link NavigationStrategy}
     */
    public NavigationStrategy strategy;
    /**
     * Notification d'un changement d'état d'un robot ou d'un incendie
     */
    public boolean notif; // notification de fin de simulation

    /**
     * Liste ordonnée des robots
     */
    public PriorityQueue<Chemin> chemins; // liste ordonnée des chemins à parcourir
    /**
     * Nombre de pas effectué durant un appel de next()
     */
    public int n; // le nombre de pas par seconde

    /**
     * Texture pack utilisé
     */
    public String texturePack;

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
     * Gets instance.
     *
     * @return the instance
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
     * @return true si la simulation est terminée
     */
    public boolean updateChemins(DonneesSimulation donneesSimulation) {
        // si chemin est obsolète, robot vide ou incendie éteint, on le supprime
        strategy.fillChemins(donneesSimulation);
        boolean res = chemins.isEmpty();
        strategy.distribution();
        notif = false;
        return res;
    }

    /**
     * Reset la distribution le singleton
     */
    public void reset() {
        chemins.clear();
        notif = false;
    }

    /**
     * Return if texture pack is fortnite
     * @return true if texture pack is fortnite else false
     */
    public boolean isFortnite() {
        return texturePack.equals("FORTNITE");
    }
}
