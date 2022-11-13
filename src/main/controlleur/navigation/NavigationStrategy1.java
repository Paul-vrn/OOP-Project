package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.robot.Robot;

import java.util.*;


/**
 * Stratégie de navigation 1
 */
public class NavigationStrategy1 implements NavigationStrategy {


    @Override
    public Chemin plusCourtChemin(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation) {
        List<Evenement> events = new LinkedList<>();
        int duration = 0;
        // TODO : implémenter l'algorithme de Dijkstra pour trouver le chemin le plus court en prenant en compte :
        // - les obstacles (mur et eau)


        return new Chemin(robot, incendie, duration, events);
    }

    @Override
    public List<Chemin> init(DonneesSimulation donneesSimulation) {
        List<Chemin> chemins = new LinkedList<>();
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            for (Robot robot : donneesSimulation.getRobots()) {
                Chemin chemin = plusCourtChemin(robot, incendie, donneesSimulation);
                chemins.add(chemin);
            }
        }
        Collections.sort(chemins, Chemin.Comparators.DURATION);
        return chemins;
    }

    public void distribution(ChefRobot chef, List<Chemin> chemins) {
        for (Chemin chemin : chemins) {
            if (!chemin.getRobot().isOccupied()) {
                chemin.getRobot().addEvenements(chemin.getEvents());
                chemin.getRobot().setOccupied(true);
                chemin.getIncendie().setHandle(true);
            }
        }
    }


}
