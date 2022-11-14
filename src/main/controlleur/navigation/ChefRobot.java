package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Incendie;
import main.modele.robot.Robot;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ChefRobot
{
    private static ChefRobot instance = null;
    private NavigationStrategy strategy;
    public static boolean notif;

    public static Queue<Chemin> chemins;

    private ChefRobot(NavigationStrategy strategy)
    {
        this.strategy = strategy;
        notif = false;
    }

    public static ChefRobot getInstance() {
        if (instance == null) {
            instance = new ChefRobot(new NavigationStrategy1());
        }
        return instance;
    }

    public static void setStrategy(NavigationStrategy strategy) {
        instance.strategy = strategy;
    }

    public static void setChemins(Queue<Chemin> chemins) {
        instance.chemins = chemins;
    }

    public void calculChemins(DonneesSimulation donneesSimulation) {
        this.chemins = new PriorityQueue<>(Chemin.Comparators.DURATION);
        this.strategy.init(chemins, donneesSimulation);
        for (Chemin chemin : chemins) {
            if (!chemin.getRobot().isOccupied()) {
                chemin.getRobot().addEvenements(chemin.getEvents());
                chemin.getRobot().setOccupied(true);
                chemin.getIncendie().setHandle(true);
            }
        }
    }
}
