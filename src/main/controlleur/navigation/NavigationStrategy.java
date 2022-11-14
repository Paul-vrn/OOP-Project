package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Case;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.robot.Robot;

import java.util.List;
import java.util.Queue;

public interface NavigationStrategy {


    /**
     * calcule le plus court chemin entre un robot et un incendie
     * @param robot
     * @param incendie
     * @param donneesSimulation
     * @return
     */
    Chemin plusCourtChemin(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation);

    /**
     * Initialise la liste des chemins de tous les robots vers tous les incendies
     * @param donneesSimulation
     * @return
     */
    void init(Queue<Chemin> chemins, DonneesSimulation donneesSimulation);
    void distribution(ChefRobot chef, List<Chemin> chemins);
}
