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
        print();
        try {
            if (this.robot.canMoveTo(CaseCible)) {
                robot.ActionDebut();
            } else {
                throw new IllegalMove("Tentative de mouvement interdit, le robot ne peut pas se déplacer vers la case cible");
            }
        } catch (IllegalMove e) {
            System.err.println(e.getMessage());
        }
    }

    public String toString(){
        return "Début mouvement -> date : "+date+" Position robot : "+robot.getPosition().toString()+" Case cible : "+CaseCible.toString();
    }

    public void print(){
        System.out.println(this.toString());
    }
}
