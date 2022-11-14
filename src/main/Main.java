package main;

import gui.GUISimulator;
import main.controlleur.DonneesSimulation;
import main.controlleur.Simulateur;
import main.controlleur.navigation.ChefRobot;
import main.controlleur.navigation.NavigationStrategy;
import main.controlleur.navigation.NavigationStrategy1;
import main.controlleur.io.LecteurDonnees;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Main {

    public static void main(String[] args) {
        try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation data = LecteurDonnees.getData(args[0]);
            ChefRobot robot = ChefRobot.getInstance(); // création du singleton chef robot avec la stratégie 1
            new Simulateur(gui, data);
        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

