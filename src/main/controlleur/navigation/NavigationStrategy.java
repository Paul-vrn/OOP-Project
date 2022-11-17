package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Case;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.robot.Robot;

import java.util.List;
import java.util.Queue;

public interface NavigationStrategy {

    Chemin plusCourtCheminIncendie(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation);

    Chemin plusCourtCheminEau(Robot robot, DonneesSimulation donneesSimulation);


    /**
     *
     * @param robot
     * @param caseArrivee
     * @param donneesSimulation
     * @return
     */
    Chemin plusCourtChemin(Robot robot, Case caseArrivee, DonneesSimulation donneesSimulation);

    /**
     * Initialise la liste des chemins de tous les robots vers tous les incendies
     * 
     * @param donneesSimulation
     * @return
     */
    void fillChemins(DonneesSimulation donneesSimulation);

    void distribution();
}
