package main.modele.robot;

import main.modele.Case;

public class Drone extends Robot {

    // definition of the abstract setVitesse method
    public void setVitesse(int vitesse) {
        // if the speed is greater than the 100, throw an exception
        if (vitesse > 100) {
            try {
                throw new RobotMaxSpeedException("The speed of the drone is greater than 100");
            } catch (RobotMaxSpeedException e) {
                e.printStackTrace();
            }
        } else {
            this.baseVitesse = vitesse;
        }
    }

    public double getVitesse() {
        return getVitesse(this.position);
    }

    public double getVitesse(Case position) {
        return this.baseVitesse;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        return (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) == 1);
    }

    public Drone(Case position) {
        setPosition(position);
        this.type = RobotType.DRONE;
        this.baseVitesse = 100;
        this.reservoir = 0;
        // 30 minutes
        this.tempsRemplissage = 30 * 60;
    }

}
