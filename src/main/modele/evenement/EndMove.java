package main.modele.evenement;

import main.modele.Case;
import main.modele.robot.Robot;

public class EndMove extends Evenement{

    private Case CaseCible;
    protected Robot robot;

    public EndMove(int date, Robot robot, Case CaseCible){
        super(date);
        this.robot = robot;
        this.CaseCible = CaseCible;
    }
    public void execute() {
        print();
        robot.setPosition(CaseCible);
        robot.ActionFin();
    }

    public String toString(){
        return "Fin mouvement -> date : "+date+" Position robot : "+robot.getPosition().toString()+" Case cible : "+CaseCible.toString();
    }

    public void print(){
        System.out.println(this.toString());
    }
}
