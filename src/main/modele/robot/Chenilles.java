package main.modele.robot;

import main.modele.Case;
import main.modele.NatureTerrain;

import java.util.ArrayList;

/**
 * Classe représentant un robot à chenilles.
 */
public class Chenilles extends Robot {

    /**
     * Constructeur de la classe Chenilles.
     *
     * @param position la position du robot
     */
    public Chenilles(Case position) {
        setPosition(position);
        this.type = RobotType.CHENILLES;
        this.baseVitesse = 60;
        this.reservoir = 2000;
        // 30 minutes
        this.tempsRemplissage = 5 * 60;
        this.quantiteEauParIntervention = 100;
        this.tempsIntervention = 8;
        this.evenements = new ArrayList<>();
    }

    /**
     * Définit la vitesse du robot à chenilles.
     *
     * @param vitesse La vitesse du robot à chenilles.
     */
    @Override
    public void setVitesse(int vitesse) {
        // if the speed is greater than the 100, throw an exception
        try {
            if (vitesse > 100) {
                throw new RobotMaxSpeedException("La vitesse du robot à chenilles ne peut pas dépasser 100");
            } else {
                this.baseVitesse = vitesse;
            }
        } catch (RobotMaxSpeedException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @param position
     * @return
     */
    @Override
    public double getVitesse(Case position) {
        if (position.getNature() == NatureTerrain.FORET) {
            return (double) this.baseVitesse / 2;
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

    public boolean canRobotBeOnCase(Case targetPosition) {
        return (targetPosition.getNature() != NatureTerrain.EAU && targetPosition.getNature() != NatureTerrain.ROCHE);
    }

    @Override
    public String getName() {
        return super.getName() + " à chenilles";
    }
}
