package main.modele;

import java.awt.*;

public enum NatureTerrain {
    EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT, UNDEFINED;

    public Color getColor() {
        switch (this) {
            case EAU:
                return Color.BLUE;
            case FORET:
                return Color.GREEN;
            case ROCHE:
                return Color.GRAY;
            case TERRAIN_LIBRE:
                return Color.WHITE;
            case HABITAT:
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }
    public String getImage() {
        switch (this) {
            case EAU:
                return "images/water01.png";
            case FORET:
                return "images/tree.png";
            case ROCHE:
                return "images/wall.png";
            case TERRAIN_LIBRE:
                return "images/grass01.png";
            case HABITAT:
                return "images/hut.png";
            default:
                return "images/road00.png";
        }
    }
}
