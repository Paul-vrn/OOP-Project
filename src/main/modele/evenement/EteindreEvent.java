package main.modele.evenement;

import main.modele.Incendie;
import main.modele.robot.Robot;

public class EteindreEvent extends Evenement {

    private Incendie incendie;

    public EteindreEvent(int dateStart, int duration, Robot robot, Incendie incendie) {
        super(dateStart, duration, robot);
        this.incendie = incendie;
    }

    public void execute(int n) {

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

    public String toString() {
        return "EteindreEvent (" + incendie.toString() + ") duration=" + duration;
    }
}
