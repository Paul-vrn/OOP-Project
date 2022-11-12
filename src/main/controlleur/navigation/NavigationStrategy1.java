package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Case;
import main.modele.evenement.Evenement;

import java.util.LinkedList;
import java.util.List;

/**
 * Stratégie de navigation 1
 */
public class NavigationStrategy1 implements NavigationStrategy {


    /**
     * Calcule le chemin le plus court entre deux cases
     *
     * @param depart            la case de départ
     * @param arrivee           la case d'arrivée
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court entre les deux cases
     */
    @Override
    public List<Evenement> plusCourtChemin(Case depart, Case arrivee, DonneesSimulation donneesSimulation) {
        List<Evenement> events = new LinkedList<>();
        // TODO : implémenter l'algorithme de Dijkstra pour trouver le chemin le plus court en prenant en compte :
        // - les obstacles (mur et eau)
        return events;
    }
}
