package main.controlleur;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import gui.Text;
import main.controlleur.navigation.ChefRobot;
import main.controlleur.navigation.NavigationStrategy;
import main.modele.Carte;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.robot.Robot;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Simulateur implements Simulable {

    private GUISimulator gui;
    private DonneesSimulation donneesSimulation;
    private int tempsEcoule;
    private Map<Integer, List<Evenement>> evenements;

    private ChefRobot chefRobot;
    /**
     * Constructeur de la classe Simulateur
     * @param gui l'interface graphique
     * @param donneesSimulation les données de la simulation
     */
    public Simulateur(GUISimulator gui, DonneesSimulation donneesSimulation, NavigationStrategy navigationStrategy) {
        this.gui = gui;
        gui.setSimulable(this);
        this.donneesSimulation = donneesSimulation;
        this.chefRobot = new ChefRobot(navigationStrategy);
        this.tempsEcoule = 0;
        this.evenements = new HashMap<>();
        draw();
    }


    /**
     * Dessine la carte et les robots
     */
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
            if(!incendie.IsEteint()){
            gui.addGraphicalElement(new ImageElement(
                    incendie.getPosition().getLigne() * caseWidth,
                    incendie.getPosition().getColonne() * caseHeight,
                    incendie.getImage(),
                    caseWidth,
                    caseHeight,
                    null));}
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
        if(evenements.get(tempsEcoule)!=null) {
            for (Evenement evenement : evenements.get(tempsEcoule)) {
                evenement.execute();
            }
        }
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

        this.evenements.computeIfAbsent(e.getDate(), k -> new ArrayList<>()).add(e);
    }



    public void incrementeTemps() {
        this.tempsEcoule++;
    }
    public void simulationTerminee() {
        System.out.println("Simulation terminée");
        System.exit(0);
    }
}
