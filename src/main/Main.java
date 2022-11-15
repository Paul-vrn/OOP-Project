package main;

import gui.GUISimulator;
import main.controlleur.DonneesSimulation;
import main.controlleur.Simulateur;
import main.controlleur.navigation.ChefRobot;
import main.controlleur.navigation.NavigationStrategy;
import main.controlleur.navigation.NavigationStrategy1;
import main.controlleur.io.LecteurDonnees;
import main.controlleur.navigation.NavigationStrategy2;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.zip.DataFormatException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream("logs/out.txt");
        PrintStream err = new PrintStream("logs/err.txt");
        System.setOut(out);
        System.setErr(err);
        new Simulateur(args);
    }
}

