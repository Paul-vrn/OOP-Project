package main.modele.robot;

import main.modele.Case;
import main.modele.NatureTerrain;

import java.util.ArrayList;

/**
 * Classe représentant un robot à pattes.
 */
public class Pattes extends Robot {

    /**
     * Constructeur de la classe Robot à Pattes
     *
     * @param position La position du robot
     */
    public Pattes(Case position) {
        setPosition(position);
        this.type = RobotType.PATTES;
        this.baseVitesse = 100;
        this.capaciteReservoir = Integer.MAX_VALUE;
        this.reservoir = Integer.MAX_VALUE; // infini
        // 30 minutes
        this.tempsRemplissage = 0;
        this.quantiteEauParIntervention = 10;
        this.debitVidage = 10;

        this.tempsIntervention = 1;
        this.evenements = new ArrayList<>();
    }

    /**
     * Getter de la position du robot
     * 
     * @param position La position du robot
     * @return La position du robot
     */
    @Override
    public double getVitesse(Case position) {
        if (position.getNature() == NatureTerrain.ROCHE) {
            // vitesse réduite à 10 sur la roche
            return 10;
        } else {
            return this.baseVitesse;
        }
    }

    /**
     * Setter de la position du robot
     *
     * @param position La position du robot
     */
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

    /**
     * Méthode qui dit si le robot peut se déplacer sur la case passée en paramètre
     *
     * @param targetPosition La case cible
     * @return true si le robot peut se déplacer sur la case cible, false sinon
     */
    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        if (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) != 1) {
            return false;
        }
        return (targetPosition.getNature() != NatureTerrain.EAU);
    }

    /**
     * Méthode qui dit si le robot peut être sur la case passée en paramètre
     * @param targetPosition la case cible
     * @return true si le robot peut être sur la case cible, false sinon
     */
    public boolean canRobotBeOnCase(Case targetPosition) {
        return (targetPosition.getNature() != NatureTerrain.EAU);
    }

    /**
     * Getter du nom du robot
     * @return le nom du robot
     */
    @Override
    public String getName() {
        return super.getName() + " à pattes";
    }

    /**
     * Méthode qui dit si le robot est vide
     * @return true si le robot est vide, false sinon
     */
    @Override
    public boolean isEmpty() {
        return false;
    }
}
