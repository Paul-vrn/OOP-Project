package main.modele.evenement;

import main.modele.Case;
import main.modele.robot.Robot;

public class MoveEvent extends Evenement {

    private Case caseCible;

    public MoveEvent(int dateStart, int duration, Robot robot, Case caseCible) {
        super(dateStart, duration, robot);
        this.caseCible = caseCible;
    }

    public void execute() {
        if (duration > 0) {
            duration--;
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

    @Override
    public String toString() {
        return "MoveEvent" + caseCible.toString();
    }

}
