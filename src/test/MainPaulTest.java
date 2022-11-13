package test;

import gui.GUISimulator;
import main.controlleur.DonneesSimulation;
import main.controlleur.Simulateur;
import main.controlleur.io.LecteurDonnees;
import main.controlleur.navigation.NavigationStrategy;
import main.modele.evenement.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class MainPaulTest {

    public static void main(String[] args) {
        try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            String path;
            if(args.length == 0){
                path = "C:\\Users\\paulb\\Documents\\Java\\projet-OOP\\cartes\\carteDrones.map";
            }
            else{
                path = args[0];
            }
            DonneesSimulation data = LecteurDonnees.getData(path);
            NavigationStrategy navigationStrategy = new NavigationStrategyPaulTest();
            Simulateur simu = new Simulateur(gui, data, navigationStrategy);
            simu.ajouteEvenement(new Move(0, data.getRobots().get(0), data.getCarte().getCase(3,4)));
            simu.ajouteEvenement(new Move(1, data.getRobots().get(0), data.getCarte().getCase(3,5)));
            simu.ajouteEvenement(new Move(1, data.getRobots().get(1), data.getCarte().getCase(3,4)));
        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

