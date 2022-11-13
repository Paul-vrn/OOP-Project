package test;

import main.controlleur.DonneesSimulation;
import main.controlleur.navigation.NavigationStrategy;
import main.modele.Case;
import main.modele.evenement.Evenement;
import main.modele.evenement.Move;

import java.util.LinkedList;
import java.util.List;

public class NavigationStrategyPaulTest implements NavigationStrategy {
    @Override
    public List<Evenement> plusCourtChemin(Case depart, Case arrivee, DonneesSimulation donneesSimulation) {
        List<Evenement> events = new LinkedList<>();
        events.add(new Move(0, donneesSimulation.getRobots().get(0), donneesSimulation.getCarte().getCase(3,4)));
        return events;
    }
}

//à retirer du git
