package main.modele.evenement;

import main.controlleur.navigation.ChefRobot;
import main.modele.Incendie;
import main.modele.robot.Robot;

/**
 * Evenement de l'extinction d'un incendie
 */
public class EteindreEvent extends Evenement {

    private Incendie incendie; // incendie à éteindre

    /**
     * Constructeur de l'évènement d'extinction d'un incendie
     * @param dateStart date de début de l'évènement
     * @param duration durée de l'évènement
     * @param robot robot qui va éteindre l'incendie
     * @param incendie incendie à éteindre
     */
    public EteindreEvent(int dateStart, int duration, Robot robot, Incendie incendie) {
        super(dateStart, duration, robot);
        this.incendie = incendie;
    }

    /**
     * Execute l'évènement
     */
    public void execute() {
        int n = ChefRobot.getInstance().n;
        try {
            if (robot.getPosition() != incendie.getPosition()) {
                throw new NoIncendieException("Le robot n'est pas sur la case de l'incendie");
            } else if (incendie.isEteint()) {
                throw new NoIncendieException("L'incendie est déjà éteint");
            } else if (robot.isEmpty()) {
                throw new NoIncendieException("Le robot est vide");
            } else if (duration >= n) {
                duration = duration - n;
                // TODO : revoir pour que ça baisse petit à petit
            } else {
                int eauDeverse = robot.emptyTank();
                incendie.eteindre(eauDeverse);
                robot.nextEvent();
            }
        } catch (NoIncendieException e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public String toString() {
        return "EteindreEvent (" + incendie.toString() + ") duration=" + duration;
    }
}
