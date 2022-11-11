package main.simulateur;

import gui.*;
import gui.Rectangle;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;

import main.modele.Carte;
import main.modele.Incendie;
import main.simulateur.evenement.Evenement;
import main.simulateur.evenement.Move;
import main.simulateur.io.LecteurDonnees;
import main.modele.robot.Robot;

class SimulateurTest {
    public static void main(String[] args) throws DataFormatException, FileNotFoundException {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        DonneesSimulation data = LecteurDonnees.getData(args[0]);
        Simulateur simulateur = new Simulateur(gui, data);
        simulateur.ajouteEvenement(new Move(0, data.getRobots().get(0), data.getCarte().getCase(3,4)));
        simulateur.ajouteEvenement(new Move(1, data.getRobots().get(0), data.getCarte().getCase(3,5)));
        simulateur.ajouteEvenement(new Move(2, data.getRobots().get(0), data.getCarte().getCase(3,6)));
        simulateur.ajouteEvenement(new Move(3, data.getRobots().get(0), data.getCarte().getCase(3,7)));
        simulateur.ajouteEvenement(new Move(4, data.getRobots().get(0), data.getCarte().getCase(3,8)));
        simulateur.ajouteEvenement(new Move(5, data.getRobots().get(0), data.getCarte().getCase(3,9)));
    }
}

public class Simulateur implements Simulable {

    private GUISimulator gui;
    private DonneesSimulation donneesSimulation;
    private int tempsEcoule;
    int xMin;
    int yMin;

    private List<Evenement> evenements;
    public Simulateur(GUISimulator gui, DonneesSimulation donneesSimulation) {
        this.gui = gui;
        gui.setSimulable(this);
        this.donneesSimulation = donneesSimulation;
        this.tempsEcoule = 0;
        this.evenements = new LinkedList<>();
        draw();
    }


    private void draw() {
        gui.reset();
        Carte carte = donneesSimulation.getCarte();
        int caseWidth = gui.getPanelWidth() / carte.getNbColonnes();
        int caseHeight = gui.getPanelHeight() / carte.getNbLignes();
        xMin = caseWidth / 2;
        yMin = caseHeight / 2;
        // dessine la carte
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                gui.addGraphicalElement(new Rectangle(xMin+i * caseWidth,  yMin+j * caseHeight, Color.BLACK, carte.getCase(i,j).getNature().getColor(), caseWidth, caseHeight));
            }
        }
        // dessine les incendies
        List<Incendie> incendies = donneesSimulation.getIncendies();
        for (Incendie incendie : incendies) {
            gui.addGraphicalElement(new Oval(xMin+incendie.getPosition().getLigne() * caseWidth, yMin+incendie.getPosition().getColonne() * caseHeight, Color.BLACK, Color.YELLOW, caseWidth/2, caseHeight/2));
        }
        // dessine les robots
        List<Robot> robots = donneesSimulation.getRobots();
        for (Robot robot : robots) {
            gui.addGraphicalElement(new Rectangle(xMin+robot.getPosition().getLigne() * caseWidth, yMin+robot.getPosition().getColonne() * caseHeight, Color.BLACK, Color.MAGENTA, caseWidth/2, caseHeight/2));
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
        // TODO faire qlq chose quand on appuie sur next

        draw();
    }

    @Override
    public void restart() {
        // TODO reset la simulation
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
        this.restart();
    }
}
