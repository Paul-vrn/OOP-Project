package main.controlleur.navigation;

import javaslang.Tuple2;
import main.controlleur.DonneesSimulation;
import main.modele.Incendie;
import main.modele.robot.Robot;

import java.util.List;
import java.util.Map;

public class ChefRobot
{
    private NavigationStrategy strategy;

    private List<Chemin> chemins;

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
        chemins = this.strategy.init(donneesSimulation);
        this.strategy.distribution(this, chemins);
    }


}
