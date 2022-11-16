package main.controlleur;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;
import gui.Text;
import main.controlleur.io.LecteurDonnees;
import main.controlleur.navigation.ChefRobot;
import main.controlleur.navigation.NavigationStrategy1;
import main.controlleur.navigation.NavigationStrategy2;
import main.modele.Carte;
import main.modele.Incendie;
import main.modele.robot.Robot;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Classe de gestion de la simulation
 */
public class Simulateur implements Simulable {

    private GUISimulator gui;
    private DonneesSimulation donneesSimulation;
    private int tempsEcoule;
    private String map;

    /**
     * Constructeur de la classe Simulateur
     * 
     * @param args les arguments de la ligne de commande
     */
    public Simulateur(String[] args) {
        this.gui = new GUISimulator(800, 600, Color.BLACK);
        gui.setSimulable(this);
        try {
            this.map = args[0];
            donneesSimulation = LecteurDonnees.getData(map);
        } catch (DataFormatException | FileNotFoundException e) {
            System.err.println("Erreur lors de la lecture du fichier");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Erreur argument 0 manquant");
            System.exit(1);
        }

        ChefRobot.getInstance(); // création du singleton chef robot
        ChefRobot.getInstance().n = 50;
        switch (args.length > 1 && args[1] != null ? args[1] : "1") {
            case "1" -> ChefRobot.getInstance().setStrategy(new NavigationStrategy1());
            case "2" -> ChefRobot.getInstance().setStrategy(new NavigationStrategy2());
            default -> {
                ChefRobot.getInstance().setStrategy(new NavigationStrategy1());
                System.out.println("Stratégie non reconnue, stratégie 1 par défaut");
            }
        }

        this.tempsEcoule = 0;
        ChefRobot.getInstance().initDistribution(donneesSimulation);
        draw();
    }

    /**
     * Dessine la carte et les robots
     */
    private void draw() {
        gui.reset();
        Carte carte = donneesSimulation.getCarte();
        int caseWidth = gui.getPanelHeight() / carte.getNbLignes();
        int caseHeight = gui.getPanelWidth() / carte.getNbColonnes();
        // dessine la carte
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                gui.addGraphicalElement(new ImageElement(
                        j * caseHeight,
                        i * caseWidth,
                        carte.getCase(i, j).getNature().getImage(),
                        caseHeight,
                        caseWidth,
                        null));
            }
        }
        // dessine les incendies
        List<Incendie> incendies = donneesSimulation.getIncendies();
        for (Incendie incendie : incendies) {
            if (!incendie.isEteint()) {
                int width = caseWidth * (incendie.getEauNecessaire() / incendie.getEauNecessaireStart());
                int height = caseHeight * (incendie.getEauNecessaire() / incendie.getEauNecessaireStart());
                int insertWidth = (caseWidth - width) / 2;
                int insertHeight = (caseHeight - height) / 2;
                gui.addGraphicalElement(new ImageElement(
                        incendie.getPosition().getColonne() * caseHeight + insertHeight,
                        incendie.getPosition().getLigne() * caseWidth + insertWidth,
                        incendie.getImage(),
                        height,
                        width,
                        null));
            }
        }
        // dessine les robots
        List<Robot> robots = donneesSimulation.getRobots();
        for (Robot robot : robots) {
            gui.addGraphicalElement(new ImageElement(
                    robot.getPosition().getColonne() * caseHeight,
                    robot.getPosition().getLigne() * caseWidth,
                    robot.getImage(),
                    caseHeight,
                    caseWidth,
                    null));
            gui.addGraphicalElement(new Text(
                    robot.getPosition().getColonne() * caseHeight + caseWidth / 2,
                    robot.getPosition().getLigne() * caseWidth + (int) (caseHeight / 1.2),
                    Color.WHITE,
                    robot.getName()));
        }
    }

    /**
     * Met à jour la simulation
     */
    @Override
    public void next() {
        if (ChefRobot.getInstance().notif) {
            ChefRobot.getInstance().updateChemins(this.donneesSimulation);
            ChefRobot.getInstance().notif = false;
        }
        for (Robot robot : donneesSimulation.getRobots()) {
            if (robot.isOccupied()) {
                robot.execute();
            }
        }
        incrementeTemps();
        draw();
    }

    /**
     * Réinitialise la simulation
     */
    @Override
    public void restart() {
        try {
            donneesSimulation = LecteurDonnees.getData(map);
            ChefRobot.getInstance().reset();
            ChefRobot.getInstance().notif = true;
            draw();
        } catch (FileNotFoundException | DataFormatException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retourne un item
     *
     * @param s
     */
    @Override
    public void selectedItem(String s) {
        Simulable.super.selectedItem(s);
    }

    /**
     * Incrémente le temps écoulé
     */
    public void incrementeTemps() {
        this.tempsEcoule++;
    }

    /**
     * Retourne le temps écoulé
     */
    public void simulationTerminee() {
        System.out.println("Simulation terminée");
        System.exit(0);
    }
}
