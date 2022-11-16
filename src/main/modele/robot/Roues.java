package main.modele.robot;

import main.modele.Case;
import main.modele.NatureTerrain;

import java.util.ArrayList;

public class Roues extends Robot {

    public Roues(Case position) {
        setPosition(position);
        this.type = RobotType.ROUES;
        this.baseVitesse = 80;
        this.reservoir = 5000;
        // 30 minutes
        this.tempsRemplissage = 10 * 60;
        this.quantiteEauParIntervention = 100;
        this.tempsIntervention = 5;
        this.evenements = new ArrayList<>();
    }

    public void setPosition(Case position) {
        try {
            if (position.getNature() == NatureTerrain.TERRAIN_LIBRE || position.getNature() == NatureTerrain.HABITAT) {
                this.position = position;
            } else {
                throw new RobotTerrainException(
                        "Le robot à roues ne peut se déplacer que sur du terrain libre ou dans des zones d'habitations");
            }
        } catch (RobotTerrainException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger sur une case adjacente
        if (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) != 1) {
            return false;
        }
        return (targetPosition.getNature() == NatureTerrain.TERRAIN_LIBRE
                || targetPosition.getNature() == NatureTerrain.HABITAT);
    }

    public boolean canRobotBeOnCase(Case targetPosition) {
        return (targetPosition.getNature() == NatureTerrain.TERRAIN_LIBRE
                || targetPosition.getNature() == NatureTerrain.HABITAT);
    }

    @Override
    public String getName() {
        return super.getName() + " à roues";
    }
}