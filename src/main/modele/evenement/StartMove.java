package main.modele.evenement;

import main.modele.Case;
import main.modele.robot.Robot;

public class Move extends Evenement{
    private Robot robot;
    private Case caseCible;
    /**

     */
    public Move(int date, Robot robot, Case caseCible){
        super(date);
        this.robot = robot;
        this.caseCible = caseCible;
    }


    public void execute() {
        try {
            if (this.robot.canMoveTo(caseCible)) {
                robot.setPosition(caseCible);
            } else {
                throw new IllegalMove("Tentative de mouvement interdit");
            }
        } catch (IllegalMove e) {
            System.err.println(e.getMessage());
        }
    }
}
