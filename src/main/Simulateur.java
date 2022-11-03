package main;

import gui.*;
import gui.Rectangle;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.zip.DataFormatException;

import main.io.LecteurDonnees;
import main.robot.Robot;

class SimulateurTest {
    public static void main(String[] args) throws DataFormatException, FileNotFoundException {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        DonneesSimulation data = LecteurDonnees.getData(args[0]);
        new Simulateur(gui, data);
    }
}

public class Simulateur implements Simulable {

    private GUISimulator gui;
    private DonneesSimulation donneesSimulation;
    private int tempsEcoule;
    int xMin = 40;
    int yMin = 40;
    public Simulateur(GUISimulator gui, DonneesSimulation donneesSimulation) {
        this.gui = gui;
        gui.setSimulable(this);
        this.donneesSimulation = donneesSimulation;
        this.tempsEcoule = 0;
        draw();
    }


    private void draw() {
        gui.reset();
        Carte carte = donneesSimulation.getCarte();
        int caseHeight = (gui.getPanelHeight()-40) / carte.getNbLignes();
        int caseWidth = (gui.getPanelWidth()-40) / carte.getNbColonnes();
        // dessine la carte
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                gui.addGraphicalElement(new Rectangle(yMin+i * caseWidth,  xMin+j * caseHeight, Color.BLACK, carte.getCase(i,j).getNature().getColor(), caseWidth, caseHeight));
            }
        }
        // dessine les incendies
        List<Incendie> incendies = donneesSimulation.getIncendies();
        for (Incendie incendie : incendies) {
            gui.addGraphicalElement(new Oval(yMin+incendie.getPosition().getLigne() * caseWidth, xMin+incendie.getPosition().getColonne() * caseHeight, Color.BLACK, Color.YELLOW, caseWidth/2, caseHeight/2));
        }
        // dessine les robots
        List<Robot> robots = donneesSimulation.getRobots();
        for (Robot robot : robots) {
            gui.addGraphicalElement(new Rectangle(yMin+robot.getPosition().getLigne() * caseWidth, xMin+robot.getPosition().getColonne() * caseHeight, Color.BLACK, Color.MAGENTA, caseWidth/2, caseHeight/2));
        }
    }
    @Override
    public void next() {
        tempsEcoule++;
        // TODO faire qlq chose quand on appuie sur next
    }

    @Override
    public void restart() {
        // TODO reset la simulation
        draw();
    }

    @Override
    public void selectedItem(String s) {
        Simulable.super.selectedItem(s);
    }
}
