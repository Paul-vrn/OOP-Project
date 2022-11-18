package main.modele.evenement;

import main.modele.robot.Robot;

/**
 * Classe abstraite représentant un événement
 */
public abstract class Evenement {

    protected int dateStart;

    protected int duration;

    protected Robot robot;

    /**
     * Constructeur de la classe Evenement
     * @param dateStart la date de début de l'événement
     * @param duration la durée de l'événement
     * @param robot le robot
     */
    protected Evenement(int dateStart, int duration, Robot robot) {
        this.dateStart = dateStart;
        this.duration = duration;
        this.robot = robot;
    }

    /**
     * Getter de la date de début de l'évènement
     * @return date de début de l'évènement
     */
    public int getDate() {
        return this.dateStart;
    }

    /**
     * Execute l'évènement
     */
    public abstract void execute();

    /**
     * Returns a string representation of the object.
     * @return
     */
    @Override
    public String toString() {
        return "Evenement (" + this.dateStart + ") : duration=" + this.duration;
    }
}
