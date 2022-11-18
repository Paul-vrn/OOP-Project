package main.vue;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Text;
import main.controlleur.io.DonneesSimulation;
import main.modele.Carte;
import main.modele.Incendie;
import main.modele.robot.Robot;

import java.awt.*;
import java.util.List;

/**
 * Classe de gestion de la vue
 */
public class Vue {

    private GUISimulator gui;
    private DonneesSimulation donneesSimulation;

    /**
     * Constructeur de la classe Vue
     *
     * @param gui  l'interface graphique
     * @param data les données de la simulation
     */
    public Vue(GUISimulator gui, DonneesSimulation data) {
        this.gui = gui;
        this.donneesSimulation = data;
    }

    /**
     * Dessine la carte et les robots
     */
    public void draw() {
        gui.reset();
        Carte carte = donneesSimulation.getCarte();
        int caseWidth = gui.getPanelHeight() / carte.getNbLignes();
        int caseHeight = gui.getPanelWidth() / carte.getNbColonnes();
        // dessine la carte
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                gui.addGraphicalElement(new ImageElement(j * caseHeight, i * caseWidth, carte.getCase(i, j).getNature().getImage(), caseHeight, caseWidth, null));
            }
        }
        // dessine les incendies
        List<Incendie> incendies = donneesSimulation.getIncendies();
        for (Incendie incendie : incendies) {
            if (!incendie.isEteint()) {
                int width = (int) ((float) caseWidth * (float) incendie.getEauNecessaire() / incendie.getEauNecessaireStart());
                int height = (int) ((float) caseHeight * (float) incendie.getEauNecessaire() / incendie.getEauNecessaireStart());
                int insertWidth = ((caseWidth - width) / 2);
                int insertHeight = ((caseHeight - height) / 2);
                gui.addGraphicalElement(new ImageElement(incendie.getPosition().getColonne() * caseHeight + insertHeight, incendie.getPosition().getLigne() * caseWidth + insertWidth, incendie.getImage(), height, width, null));
            }
        }
        // dessine les robots
        List<Robot> robots = donneesSimulation.getRobots();
        for (Robot robot : robots) {
            gui.addGraphicalElement(new ImageElement(robot.getPosition().getColonne() * caseHeight, robot.getPosition().getLigne() * caseWidth, robot.getImage(), caseHeight, caseWidth, null));
            gui.addGraphicalElement(new Text(robot.getPosition().getColonne() * caseHeight + caseWidth / 2, robot.getPosition().getLigne() * caseWidth + (int) (caseHeight / 1.2), Color.WHITE, robot.getName()));
        }
    }

}
