package main;

import gui.GUISimulator;
import main.controlleur.DonneesSimulation;
import main.controlleur.Simulateur;
import main.controlleur.navigation.NavigationStrategy;
import main.controlleur.navigation.NavigationStrategy1;
import main.modele.evenement.Move;
import main.controlleur.io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Main {

    public static void main(String[] args) {
        try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation data = LecteurDonnees.getData(args[0]);
            NavigationStrategy navigationStrategy = new NavigationStrategy1();
            new Simulateur(gui, data, navigationStrategy);
        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

