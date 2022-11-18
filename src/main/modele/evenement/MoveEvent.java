package main.modele.evenement;

import main.controlleur.navigation.ChefRobot;
import main.modele.Case;
import main.modele.robot.Robot;

/**
 * Evenement de déplacement d'un robot
 */
public class MoveEvent extends Evenement {

    private Case caseCible; // case cible du déplacement

    /**
     * Constructeur de l'évènement de déplacement d'un robot
     * @param dateStart date de début de l'évènement
     * @param duration durée de l'évènement
     * @param robot robot qui va se déplacer
     * @param caseCible case cible du déplacement
     */
    public MoveEvent(int dateStart, int duration, Robot robot, Case caseCible) {
        super(dateStart, duration, robot);
        this.caseCible = caseCible;
    }

    /**
     * Execute l'évènement
     */
    public void execute() {
        int n = ChefRobot.getInstance().n;
        if (duration >= n) {
            duration = duration - n;
        } else {
            try {
                if (robot.canMoveTo(caseCible)) {
                    robot.setPosition(caseCible);
                    robot.nextEvent();
                } else {
                    throw new IllegalMove("Le robot ne peut pas se déplacer sur la case cible");
                }
            } catch (IllegalMove e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Returns a string representation of the object.
     * @return String
     */
    @Override
    public String toString() {
        return "MoveEvent vers (" + caseCible.toString() + "): duration=" + duration;
    }

}
