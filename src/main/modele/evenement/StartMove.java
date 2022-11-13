package main.modele.evenement;

import main.modele.Case;
import main.modele.robot.Robot;

public class StartMove extends Evenement{

    private Case CaseCible;
    protected Robot robot;
    /**

     */
    public StartMove(int date, Robot robot, Case CaseCible){
        super(date);
        this.robot = robot;
        this.CaseCible = CaseCible;
    }


    public void execute() {
        try {
            if (this.robot.canMoveTo(CaseCible)) {
                robot.ActionDebut();
            } else {
                throw new IllegalMove("Tentative de mouvement interdit");
            }
        } catch (IllegalMove e) {
            System.err.println(e.getMessage());
        }
    }
}
