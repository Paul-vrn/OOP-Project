package main.robot;

import Exceptions.RobotTerrainException;
import main.Case;
import main.NatureTerrain;

public class Pattes extends Robot {

    public void setVitesse(int vitesse) {
        this.BaseVitesse = vitesse;
    }

    public int getVitesse() {
        return getVitesse(this.Position);
    }

    public int getVitesse(Case position) {
        if (position.getNature() == NatureTerrain.ROCHE) {
            // vitesse réduite à 10 sur la roche
            return 10;
        } else {
            return this.BaseVitesse;
        }
    }

    public void setPosition(Case position) {
        try {
            if (position.getNature() == NatureTerrain.EAU) {
                throw new RobotTerrainException("Le robot à pattes ne peut pas se déplacer sur l'eau");
            } else {
                this.Position = position;
            }
        } catch (RobotTerrainException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean CanMoveTo(Case Target_Position) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        if (Math.abs(this.Position.getLigne() - Target_Position.getLigne())
                + Math.abs(this.Position.getColonne() - Target_Position.getColonne()) != 1) {
            return false;
        }
        if (Target_Position.getNature() == NatureTerrain.EAU) {
            return false;
        }
        return true;
    }

    public Pattes(Case position) {
        setPosition(position);
        this.Type = RobotType.PATTES;
        this.BaseVitesse = 100;
        this.Reservoir = 0;
        // 30 minutes
        this.TempsRemplissage = 30 * 60;
    }

}
