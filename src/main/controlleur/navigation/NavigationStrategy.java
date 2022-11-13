package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Case;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.robot.Robot;

import java.util.List;

public interface NavigationStrategy {

    /**
     * Calcule le chemin le plus court entre deux cases
     * @param depart la case de départ
     * @param arrivee la case d'arrivée
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court entre les deux cases
     */
    Chemin plusCourtChemin(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation);

    List<Chemin> init(DonneesSimulation donneesSimulation);
    void distribution(ChefRobot chef, List<Chemin> chemins);
}
