package test;

import gui.GUISimulator;
import main.controlleur.DonneesSimulation;
import main.controlleur.io.LecteurDonnees;
import main.modele.evenement.MoveEvent;
import main.modele.robot.Robot;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestEvent {
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
            data.getRobots().get(0).addEvenement(new MoveEvent(0, 5, data.getRobots().get(0), data.getCarte().getCase(4,3)));
            data.getRobots().get(0).addEvenement(new MoveEvent(5, 5, data.getRobots().get(0), data.getCarte().getCase(5,3)));
            Robot robot = data.getRobots().get(0);
            System.out.println(robot);
            robot.getEvenements().get(robot.getIndexEvenement()).execute();
            System.out.println(robot);
            robot.getEvenements().get(robot.getIndexEvenement()).execute();
            System.out.println(robot);
        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
