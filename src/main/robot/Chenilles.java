package main.robot;

import Exceptions.RobotMaxSpeedException;
import Exceptions.RobotTerrainException;
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
            this.BaseVitesse = vitesse;
        }
    }

    public int getVitesse() {
        return getVitesse(this.Position);
    }

    public int getVitesse(Case position) {
        if (position.getNature() == NatureTerrain.FORET) {
            return this.BaseVitesse / 2;
        } else {
            return this.BaseVitesse;
        }
    }

    public void setPosition(Case position) {
        try {
            if (position.getNature() == NatureTerrain.EAU || position.getNature() == NatureTerrain.ROCHE) {
                throw new RobotTerrainException(
                        "Le robot à chenilles ne peut pas se déplacer sur les rochers ou sur l'eau");
            } else {
                this.Position = position;
            }
        } catch (RobotTerrainException e) {
            e.printStackTrace();
        }
    }

    public boolean CanMoveTo(Case Target_Position) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        if (Math.abs(this.Position.getLigne() - Target_Position.getLigne())
                + Math.abs(this.Position.getColonne() - Target_Position.getColonne()) != 1) {
            return false;
        }
        if (Target_Position.getNature() == NatureTerrain.EAU || Target_Position.getNature() == NatureTerrain.ROCHE) {
            return false;
        }
        return true;
    }

    public Chenilles(Case position) {
        setPosition(position);
        this.Type = RobotType.CHENILLES;
        this.BaseVitesse = 60;
        this.Reservoir = 0;
        // 30 minutes
        this.TempsRemplissage = 5 * 60;
    }

}
