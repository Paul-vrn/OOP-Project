package main.simulateur.evenement;
import main.modele.robot.Robot;
import main.modele.Case;

public class Move extends Evenement{
    private Robot robot;
    private Case case_cible;
    /**

     */
    public Move(int date, Robot robot, Case case_cible){
        super(date);
        this.robot = robot;
        this.case_cible = case_cible;
    }

    public void execute() {
        try {
            if (this.robot.canMoveTo(case_cible)) {
                robot.setPosition(case_cible);
            } else {
                throw new IllegalMove("Tentative de mouvement interdit");
            }
        } catch (IllegalMove e) {
            System.out.println(e.getMessage());
        }
    }
}
