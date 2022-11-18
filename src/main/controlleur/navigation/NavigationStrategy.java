package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Case;
import main.modele.Incendie;
import main.modele.robot.Robot;

/**
 * Interface représentant une stratégie de navigation.
 */
public interface NavigationStrategy {

    /**
     * Méthode qui permet de calculer le plus court chemin entre un robot et un incendie
     * @param robot le robot
     * @param incendie l'incendie
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court
     */
    Chemin plusCourtCheminIncendie(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation);
    /**
     * Méthode qui permet de calculer le plus court chemin entre un robot est tous les points d'eau dont il peut avoir accès
     * @param robot le robot
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court
     */
    Chemin plusCourtCheminEau(Robot robot, DonneesSimulation donneesSimulation);


    /**
     * Méthode qui permet de calculer le plus court chemin entre un robot et une case
     * @param robot le robot
     * @param caseArrivee la case d'arrivée
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court
     */
    Chemin plusCourtChemin(Robot robot, Case caseArrivee, DonneesSimulation donneesSimulation);

    /**
     * Méthode qui permet de re-assigner des chemins aux robots qui sont inoccupés ou qui sont vides
     * dans le cas où il y a encore des incendies à éteindre
     *
     * @param donneesSimulation les données de la simulation
     */
    void fillChemins(DonneesSimulation donneesSimulation);

    /**
     * Méthode qui distribue les chemins aux robots dans l'ordre de la priority queue (en fonction de la durée)
     */
    void distribution();
}
