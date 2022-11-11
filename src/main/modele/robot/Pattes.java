package main.modele.robot;

import main.modele.Case;
import main.modele.NatureTerrain;

public class Pattes extends Robot {

    public void setVitesse(int vitesse) {
        this.baseVitesse = vitesse;
    }

    public double getVitesse() {
        return getVitesse(this.position);
    }

    public double getVitesse(Case position) {
        if (position.getNature() == NatureTerrain.ROCHE) {
            // vitesse réduite à 10 sur la roche
            return 10;
        } else {
            return this.baseVitesse;
        }
    }

    public void setPosition(Case position) {
        try {
            if (position.getNature() == NatureTerrain.EAU) {
                throw new RobotTerrainException("Le robot à pattes ne peut pas se déplacer sur l'eau");
            } else {
                this.position = position;
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
        return (targetPosition.getNature() != NatureTerrain.EAU);
    }

    public Pattes(Case position) {
        setPosition(position);
        this.type = RobotType.PATTES;
        this.baseVitesse = 100;
        this.reservoir = 0;
        // 30 minutes
        this.tempsRemplissage = 30 * 60;
    }

}