package main.modele.robot;

import main.modele.Case;
import main.modele.NatureTerrain;

import java.util.ArrayList;

/**
 * Classe représentant un robot à roues
 */
public class Roues extends Robot {

    /**
     * Constructeur de la classe Roues
     * @param position la position du robot
     */
    public Roues(Case position) {
        setPosition(position);
        this.type = RobotType.ROUES;
        this.baseVitesse = 80;
        this.reservoir = 5000;
        this.capaciteReservoir = 5000;
        // 30 minutes
        this.tempsRemplissage = 10 * 60;
        this.quantiteEauParIntervention = 100;
        this.debitVidage = 20;
        this.tempsIntervention = 5;
        this.evenements = new ArrayList<>();
    }

    /**
     * Setter de la position du robot
     * @param position la position du robot
     */
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

    /**
     * Méthode qui dit si le robot peut se déplacer sur la case passée en paramètre
     * @param targetPosition la case cible
     * @return true si le robot peut se déplacer sur la case cible, false sinon
     */
    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger sur une case adjacente
        if (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) != 1) {
            return false;
        }
        return (targetPosition.getNature() == NatureTerrain.TERRAIN_LIBRE
                || targetPosition.getNature() == NatureTerrain.HABITAT);
    }

    /**
     * Méthode qui dit si le robot peut être sur la case passée en paramètre
     * @param targetPosition la case cible
     * @return true si le robot peut être sur la case cible, false sinon
     */
    public boolean canRobotBeOnCase(Case targetPosition) {
        return (targetPosition.getNature() == NatureTerrain.TERRAIN_LIBRE
                || targetPosition.getNature() == NatureTerrain.HABITAT);
    }

    /**
     * Getter du nom du robot
     * @return le nom du robot
     */
    @Override
    public String getName() {
        return super.getName() + " à roues";
    }
}