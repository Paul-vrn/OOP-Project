package main.robot;

import main.Case;
import main.NatureTerrain;

public class Roues extends Robot {

    public void setVitesse(int vitesse) {
        this.baseVitesse = vitesse;
    }

    public int getVitesse() {
        return getVitesse(this.position);
    }

    public int getVitesse(Case position) {
        return this.baseVitesse;
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
            System.out.println(e.getMessage());
        }
    }

    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        if (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) != 1) {
            return false;
        }
        return (targetPosition.getNature() == NatureTerrain.TERRAIN_LIBRE || targetPosition.getNature() == NatureTerrain.HABITAT);
    }

    public Roues(Case position) {
        setPosition(position);
        this.type = RobotType.ROUES;
        this.baseVitesse = 80;
        this.reservoir = 0;
        // 30 minutes
        this.tempsRemplissage = 10 * 60;
    }

}