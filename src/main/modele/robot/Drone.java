package main.modele.robot;

import main.modele.Case;

import java.util.ArrayList;

/**
 * Classe représentant un Drone.
 */
public class Drone extends Robot {

    /**
     * Constructeur de la classe Drone
     *
     * @param position La position du robot
     */
    public Drone(Case position) {
        setPosition(position);
        this.type = RobotType.DRONE;
        this.baseVitesse = 100;
        this.reservoir = 10000;
        this.capaciteReservoir = 10000;
        // 30 minutes
        this.tempsRemplissage = 30 * 60;
        this.quantiteEauParIntervention = 10000;
        this.tempsIntervention = 30;
        this.debitVidage = 333;
        this.evenements = new ArrayList<>();
    }

    /**
     * Définit la vitesse du robot Drone.
     *
     * @param vitesse La vitesse du robot Drone.
     * @throws RobotMaxSpeedException Si la vitesse du robot dépasse 100.
     */
    @Override
    public void setVitesse(int vitesse) {
        // if the speed is greater than the 100, throw an exception
        if (vitesse > 150) {
            try {
                throw new RobotMaxSpeedException("The speed of the drone is greater than 100");
            } catch (RobotMaxSpeedException e) {
                e.printStackTrace();
            }
        } else {
            this.baseVitesse = vitesse;
        }
    }

    /**
     * Définit la position du robot Drone.
     *
     * @param position La position du robot Drone.
     */
    public void setPosition(Case position) {
        this.position = position;
    }

    /**
     * Retourne si le robot Drone peut se déplacer sur la case cible.
     *
     * @param targetPosition La case cible.
     * @return Si le robot peut se déplacer sur la case cible.
     */
    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        return (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) == 1);
    }

    /**
     * Retourne la vitesse du robot Drone sur la case cible.
     *
     * @param targetPosition La case cible.
     * @return La vitesse du robot Drone sur la case cible.
     */
    public boolean canRobotBeOnCase(Case targetPosition) {
        return true;
    }

    /**
     * Retourne l'image du robot Drone.
     *
     * @return L'image du robot Drone.
     */
    @Override
    public String getImage() {
        return "images/drone.gif";
    }

    /**
     * Retourne le nom du robot Drone.
     *
     * @return Le nom du robot Drone.
     */
    @Override
    public String getName() {
        return "Drone";
    }
}
