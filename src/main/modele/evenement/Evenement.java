package main.modele.evenement;

import main.modele.robot.Robot;

/**
 * Classe abstraite représentant un événement
 */
public abstract class Evenement {
    protected int duration;
    protected Robot robot;

    /**
     * Constructeur de la classe Evenement
     *
     * @param duration la durée de l'événement
     * @param robot    le robot
     */
    protected Evenement(int duration, Robot robot) {
        this.duration = duration;
        this.robot = robot;
    }

    /**
     * Execute l'évènement
     */
    public abstract void execute();

    /**
     * Returns a string representation of the object.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Evenement: duration=" + this.duration;
    }
}
