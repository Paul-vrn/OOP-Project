package main.robot;

import main.Case;
import main.NatureTerrain;

public class Chenilles extends Robot {
    // definition of the abstract setVitesse method
    public void setVitesse(int vitesse) {
        // if the speed is greater than the 100, throw an exception
        if (vitesse > 80) {
            try {
                throw new RobotMaxSpeedException("The speed of the robot is greater than 80");
            } catch (RobotMaxSpeedException e) {
                e.printStackTrace();
            }
        } else {
            this.baseVitesse = vitesse;
        }
    }

    public int getVitesse() {
        return getVitesse(this.position);
    }

    public int getVitesse(Case position) {
        if (position.getNature() == NatureTerrain.FORET) {
            return this.baseVitesse / 2;
        } else {
            return this.baseVitesse;
        }
    }

    public void setPosition(Case position) {
        try {
            if (position.getNature() == NatureTerrain.EAU || position.getNature() == NatureTerrain.ROCHE) {
                throw new RobotTerrainException(
                        "Le robot à chenilles ne peut pas se déplacer sur les rochers ou sur l'eau");
            } else {
                this.position = position;
            }
        } catch (RobotTerrainException e) {
            e.printStackTrace();
        }
    }

    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        if (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) != 1) {
            return false;
        }
        return (targetPosition.getNature() != NatureTerrain.EAU && targetPosition.getNature() != NatureTerrain.ROCHE);
    }

    public Chenilles(Case position) {
        setPosition(position);
        this.type = RobotType.CHENILLES;
        this.baseVitesse = 60;
        this.reservoir = 0;
        // 30 minutes
        this.tempsRemplissage = 5 * 60;
    }

}
