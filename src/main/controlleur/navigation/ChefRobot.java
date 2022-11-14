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
    private NavigationStrategy strategy;

    private Queue<Chemin> chemins;

    public ChefRobot(NavigationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(NavigationStrategy strategy) {
        this.strategy = strategy;
    }

    public NavigationStrategy getStrategy() {
        return this.strategy;
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
