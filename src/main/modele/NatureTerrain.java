package main.modele;

import main.Application;
import main.controlleur.Simulateur;
import main.controlleur.navigation.ChefRobot;

import java.awt.*;

/**
 * Classe représentant une case de la carte.
 */
public enum NatureTerrain {
    /**
     * Case de type eau
     */
    EAU,
    /**
     * Case de type foret
     */
    FORET,
    /**
     * Case de type roche
     */
    ROCHE,
    /**
     * Case de type terrain libre
     */
    TERRAIN_LIBRE,
    /**
     * Case de type habitation
     */
    HABITAT,
    /**
     * Case de type indefinie
     */
    UNDEFINED;

    /**
     * Retourne la couleur associée à la nature du terrain.
     *
     * @return la couleur associée à la nature du terrain
     */
    public Color getColor() {
        return switch (this) {
            case EAU -> Color.BLUE;
            case FORET -> Color.GREEN;
            case ROCHE -> Color.GRAY;
            case TERRAIN_LIBRE -> Color.WHITE;
            case HABITAT -> Color.RED;
            default -> Color.BLACK;
        };
    }

    /**
     * Retourne l'image correspondant à la nature du terrain
     *
     * @return l'image correspondant à la nature du terrain
     */
    public String getImage() {
        return switch (this) {
            case EAU -> (ChefRobot.getInstance().isFortnite())? "images/Fortnite_water.jpg" : "images/water.png";
            case FORET -> (ChefRobot.getInstance().isFortnite())? "images/Fortnite_Bush.jpg" : "images/tree.png";
            case ROCHE -> (ChefRobot.getInstance().isFortnite())? "images/Fornite_Boulder.jpg" : "images/wall.png";
            case TERRAIN_LIBRE -> (ChefRobot.getInstance().isFortnite())? "images/Fortnite_grass.jpg" : "images/grass01.png";
            case HABITAT -> (ChefRobot.getInstance().isFortnite())? "images/Fortnite_House.jpg" : "images/hut.png";
            default -> (ChefRobot.getInstance().isFortnite())? "images/Fornite_Boulder.jpg" : "images/road00.png";
        };
    }
}
