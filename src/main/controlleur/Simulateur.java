package main.controlleur;

import gui.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import main.modele.Carte;
import main.modele.Incendie;
import main.controlleur.evenement.Evenement;
import main.controlleur.evenement.Move;
import main.controlleur.io.LecteurDonnees;
import main.modele.robot.Robot;

class SimulateurTest {
    public static void main(String[] args) throws DataFormatException, FileNotFoundException {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        try {
            DonneesSimulation data = LecteurDonnees.getData(args[0]);
            Simulateur simulateur = new Simulateur(gui, data);
            simulateur.ajouteEvenement(new Move(0, data.getRobots().get(0), data.getCarte().getCase(3,4)));
            simulateur.ajouteEvenement(new Move(1, data.getRobots().get(0), data.getCarte().getCase(3,5)));
            simulateur.ajouteEvenement(new Move(2, data.getRobots().get(0), data.getCarte().getCase(3,6)));
            simulateur.ajouteEvenement(new Move(3, data.getRobots().get(0), data.getCarte().getCase(3,7)));
            simulateur.ajouteEvenement(new Move(4, data.getRobots().get(0), data.getCarte().getCase(3,8)));
            simulateur.ajouteEvenement(new Move(5, data.getRobots().get(0), data.getCarte().getCase(3,9)));
        } catch (DataFormatException | FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

public class Simulateur implements Simulable {

    private GUISimulator gui;
    private DonneesSimulation donneesSimulation;
    private int tempsEcoule;
    private List<Evenement> evenements;
    public Simulateur(GUISimulator gui, DonneesSimulation donneesSimulation) {
        this.gui = gui;
        gui.setSimulable(this);

        this.donneesSimulation = donneesSimulation;
        this.tempsEcoule = 0;
        this.evenements = new ArrayList<>();
        draw();
    }


    private void draw() {
        gui.reset();
        Carte carte = donneesSimulation.getCarte();
        int caseWidth = gui.getPanelWidth() / carte.getNbColonnes();
        int caseHeight = gui.getPanelHeight() / carte.getNbLignes();
        // dessine la carte
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                gui.addGraphicalElement(new ImageElement(
                         i * caseWidth,
                        j * caseHeight,
                        carte.getCase(i, j).getNature().getImage(),
                        caseWidth,
                        caseHeight,
                        null));
            }
        }
        // dessine les incendies
        List<Incendie> incendies = donneesSimulation.getIncendies();
        for (Incendie incendie : incendies) {
            gui.addGraphicalElement(new ImageElement(
                    incendie.getPosition().getLigne() * caseWidth,
                    incendie.getPosition().getColonne() * caseHeight,
                    incendie.getImage(),
                    caseWidth,
                    caseHeight,
                    null));
        }
        // dessine les robots
        List<Robot> robots = donneesSimulation.getRobots();
        for (Robot robot : robots) {
            gui.addGraphicalElement(new ImageElement(
                    robot.getPosition().getLigne() * caseWidth,
                    robot.getPosition().getColonne() * caseHeight,
                    robot.getImage(),
                    caseWidth,
                    caseHeight,
                    null));
            gui.addGraphicalElement(new Text(
                    robot.getPosition().getLigne() * caseWidth + caseWidth / 2,
                    robot.getPosition().getColonne() * caseHeight + (int)(caseHeight / 1.2),
                    Color.WHITE,
                    robot.getName()));
        }
    }
    @Override
    public void next() {
        if(tempsEcoule >= evenements.size()){
            System.out.println("Il n'y a plus d'évènements à afficher/simuler");
            return;
        }
        evenements.get(tempsEcoule).execute();
        incrementeTemps();
        draw();
    }

    @Override
    public void restart() {
        draw();
    }

    /**
     * Retourne un item
     * @param s
     */
    @Override
    public void selectedItem(String s) {
        Simulable.super.selectedItem(s);
    }

    /**
     * ajoute un événement à la liste des événements
     * @param e l'événement à ajouter
     */
    public void ajouteEvenement(Evenement e) {
        this.evenements.add(e);
    }


    public void incrementeTemps() {
        this.tempsEcoule++;
    }
    public void simulationTerminee() {
        System.out.println("Simulation terminée");
        System.exit(0);
    }
}
