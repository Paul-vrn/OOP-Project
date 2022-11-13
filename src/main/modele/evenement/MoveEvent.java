package main.modele.evenement;

import main.modele.Case;
import main.modele.robot.Robot;

public class MoveEvent extends Evenement{

    private Case CaseCible;

    public MoveEvent(int dateStart, int duration,Robot robot, Case CaseCible){
        super(dateStart, duration, robot);
        this.CaseCible = CaseCible;
    }

    public void execute(){
        if(duration > 0){
            duration--;
        }
        else{
            try{
                if(robot.canMoveTo(CaseCible)){
                    robot.setPosition(CaseCible);
                }
                else{
                    throw new IllegalMove("Le robot ne peut pas se déplacer sur la case cible");
                }
            }
            catch(IllegalMove e){
                System.err.println(e.getMessage());
            }
        }
    }



}
