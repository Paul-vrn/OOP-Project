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
    public Chemin plusCourtChemin(Robot robot, Case caseArrivee, DonneesSimulation donneesSimulation) {
        List<Evenement> events = new LinkedList<>();
        int duration = 0;
        // TODO : implémenter l'algorithme de Dijkstra pour trouver le chemin le plus
        // court en prenant en compte :
        // - les obstacles (mur et eau)
        // - la capacité du robot à éteindre un incendie (si le robot n'a pas assez de
        // capacité, il doit aller chercher de l'eau et revenir)
        return new Chemin(robot, caseArrivee, duration, events);
    }

    public Chemin plusCourtCheminIncendie(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation) {
        // TODO à implémenter (ou copier depuis strategy1 ça dépend de ce qu'on veut
        // faire)
        return plusCourtChemin(robot, incendie.getPosition(), donneesSimulation);
    }

    public Chemin plusCourtCheminEau(Robot robot, DonneesSimulation donneesSimulation) {
        // TODO à implémenter (ou copier depuis strategy1 ça dépend de ce qu'on veut
        // faire)
        return plusCourtChemin(robot, null, donneesSimulation);
    }

    @Override
    public void fillChemins(DonneesSimulation donneesSimulation) {
    }

    @Override
    public void distribution() {

    }
}
