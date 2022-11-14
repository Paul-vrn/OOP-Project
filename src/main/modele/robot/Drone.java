package main.modele.robot;

import main.modele.Case;

import java.util.ArrayList;

/**
 * Classe représentant un Drone.
 */
public class Drone extends Robot {

    /**
     * Constructeur de la classe Drone
     * @param position La position du robot
     */
    public Drone(int i, Case position) {
        super(i);
        setPosition(position);
        this.type = RobotType.DRONE;
        this.baseVitesse = 100;
        this.reservoir = 10000;
        // 30 minutes
        this.tempsRemplissage = 30 * 60;
        this.QuantiteEauParIntervention = 10000;
        this.TempsIntervention = 30;
        this.evenements = new ArrayList<>();
    }


    /**
     * Définit la vitesse du robot Drone.
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
    public void setPosition(Case position) {
        this.position = position;
    }

    public boolean canMoveTo(Case targetPosition) {
        // vérrifie bien qu'on essaye de bouger à sur une case adjacente
        return (Math.abs(this.position.getLigne() - targetPosition.getLigne())
                + Math.abs(this.position.getColonne() - targetPosition.getColonne()) == 1);
    }



    @Override
    public String getImage(){
        return "images/drone.gif";
    }

    @Override
    public String getName() { return "Drone"; }
}
