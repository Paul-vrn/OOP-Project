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
}
