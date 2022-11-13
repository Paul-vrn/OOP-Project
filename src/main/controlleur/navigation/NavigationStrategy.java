package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Case;
import main.modele.evenement.Evenement;

import java.util.List;

public interface NavigationStrategy {

    /**
     * Calcule le chemin le plus court entre deux cases
     * @param depart la case de départ
     * @param arrivee la case d'arrivée
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court entre les deux cases
     */
    List<Evenement> plusCourtChemin(Case depart, Case arrivee, DonneesSimulation donneesSimulation);

}