package test;

import main.controlleur.DonneesSimulation;
import main.controlleur.navigation.NavigationStrategy;
import main.controlleur.navigation.NavigationStrategy1;
import main.controlleur.io.LecteurDonnees;
import main.controlleur.navigation.Chemin;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Strategy1Test {

    public static void main(String[] args) {
        try {
            DonneesSimulation data = LecteurDonnees.getData("cartes/carteSujet.map");
            NavigationStrategy strategy = new NavigationStrategy1();
            System.out.println(data.getIncendies().get(0));
            Chemin chemin = strategy.plusCourtChemin(data.getRobots().get(1), data.getIncendies().get(0), data);
            chemin.print();
        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
