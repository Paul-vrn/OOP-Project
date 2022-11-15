package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Incendie;
import main.modele.robot.Robot;

import java.util.*;

public class ChefRobot {
    private static ChefRobot instance = null;
    public static NavigationStrategy strategy;
    public static boolean notif;

    public static Queue<Chemin> chemins;

    private ChefRobot(NavigationStrategy strategy) {
        ChefRobot.strategy = strategy;
        notif = false;
        chemins = new PriorityQueue<>(Chemin.Comparators.DURATION);
    }

    public static ChefRobot getInstance() {
        if (instance == null) {
            instance = new ChefRobot(new NavigationStrategy1());
        }
        return instance;
    }

    public static void setStrategy(NavigationStrategy strategy) {
        ChefRobot.strategy = strategy;
    }

    public static void initDistribution(DonneesSimulation donneesSimulation) {
        strategy.fillChemins(chemins, donneesSimulation);
        strategy.distribution(chemins);
        notif = false;
    }

    /**
     * Appel après que notify == true, supprime les chemins qui servent plus à rien.
     */
    public static void updateChemins(DonneesSimulation donneesSimulation) {
        if (notif) {
            // si chemin est obsolète, robot vide ou incendie éteint, on le supprime
            chemins.removeIf(chemin ->
                    !chemin.getStart().equals(chemin.getRobot().getPosition()) ||
                            !chemin.getEnd().equals(chemin.getIncendie().getPosition()) ||
                            chemin.getRobot().IsEmpty() || chemin.getIncendie().IsEteint()
            );
            strategy.fillChemins(chemins, donneesSimulation);
            strategy.distribution(chemins);
            notif = false;
        }
    }

    public static void reset() {
    }
}
