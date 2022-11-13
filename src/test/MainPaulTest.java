package test;

import gui.GUISimulator;
import main.controlleur.DonneesSimulation;
import main.controlleur.Simulateur;
import main.controlleur.io.LecteurDonnees;
import main.controlleur.navigation.NavigationStrategy;

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
            int temps = 0;
            simu.AjouteMouvement(temps, data.getRobots().get(0), data.getCarte().getCase(4,3));
            temps = temps + data.getRobots().get(0).getTempsDeplacement(data.getCarte(),data.getCarte().getCase(4,3));
            simu.AjouteMouvement(temps, data.getRobots().get(0), data.getCarte().getCase(5,3));
            temps = temps + data.getRobots().get(0).getTempsDeplacement(data.getCarte(),data.getCarte().getCase(5,3));
            simu.AjouteMouvement(temps, data.getRobots().get(0), data.getCarte().getCase(5,4));
            temps = temps + data.getRobots().get(0).getTempsDeplacement(data.getCarte(),data.getCarte().getCase(5,4));
            simu.AjouteMouvement(temps, data.getRobots().get(0), data.getCarte().getCase(5,5));
            temps = temps + data.getRobots().get(0).getTempsDeplacement(data.getCarte(),data.getCarte().getCase(5,5))+10;
            data.getIncendies().get(4).print();
            simu.AjouteEteindre(temps, data.getRobots().get(0), data.getIncendies().get(4));

        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

