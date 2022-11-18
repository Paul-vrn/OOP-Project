package test;

import main.controlleur.io.DonneesSimulation;
import main.controlleur.navigation.NavigationStrategy;
import main.controlleur.navigation.NavigationStrategy1;
import main.controlleur.io.LecteurDonnees;
import main.controlleur.navigation.Chemin;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Strategy1Test {

    public static void main(String[] args) {
        try {
            DonneesSimulation data = LecteurDonnees.getData("cartes/desertOfDeath-20x20.map");
            NavigationStrategy strategy = new NavigationStrategy1();
            Chemin chemin = strategy.plusCourtCheminEau(data.getRobots().get(1), data);
            chemin.print();
        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
