package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Case;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.robot.Robot;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Stratégie de navigation 1
 */
public class NavigationStrategy2 implements NavigationStrategy {

    @Override
    public Chemin plusCourtChemin(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation) {
        List<Evenement> events = new LinkedList<>();
        int duration = 0;
        // TODO : implémenter l'algorithme de Dijkstra pour trouver le chemin le plus court en prenant en compte :
        // - les obstacles (mur et eau)
        // - la capacité du robot à éteindre un incendie (si le robot n'a pas assez de capacité, il doit aller chercher de l'eau et revenir)
        return new Chemin(robot, incendie, duration, events);
    }

    @Override
    public void init(Queue<Chemin> chemins, DonneesSimulation donneesSimulation) {
    }

    @Override
    public void distribution(ChefRobot chef, List<Chemin> chemins) {

    }
}
