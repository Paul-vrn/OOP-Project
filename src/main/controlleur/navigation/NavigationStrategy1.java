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

    public void initChemins(Queue<Chemin> chemins, DonneesSimulation donneesSimulation) {
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            for (Robot robot : donneesSimulation.getRobots()) {
                Chemin chemin = plusCourtChemin(robot, incendie, donneesSimulation);
                chemins.add(chemin);
            }
        }
    }

    /**
     * Va reremplir les chemins avec les robots non assignés et les incendies non éteints
     * @param chemins les chemins à remplir
     * @param donneesSimulation les données de la simulation
     */
    public void fillChemins(Queue<Chemin> chemins, DonneesSimulation donneesSimulation){
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            if (!incendie.IsEteint() && !incendie.IsHandled()) {
                for (Robot robot : donneesSimulation.getRobots()) {
                    if (!robot.IsEmpty() && !robot.isOccupied()) {
                        Chemin chemin = plusCourtChemin(robot, incendie, donneesSimulation);
                        chemins.add(chemin);
                    }
                }
            }
        }
    }

    /**
     * Une fois qu'on a viré les chemins obsolètes et ceux relient des robots vides ou des incendies éteints
     * on redistribue les chemins restants aux robots non occupés et aux incendies non éteints
     */
    public void distribution(Queue<Chemin> chemins) {
        Iterator<Chemin> iterator = chemins.iterator();
        while (iterator.hasNext()) {
            Chemin chemin = iterator.next();
            if (!chemin.getRobot().isOccupied() && !chemin.getIncendie().IsHandled()) {
                chemin.getRobot().addEvenements(chemin.getEvents());
                chemin.getRobot().setOccupied(true);
                chemin.getIncendie().setHandle(true);
                iterator.remove();
            }
        }
    }


}
