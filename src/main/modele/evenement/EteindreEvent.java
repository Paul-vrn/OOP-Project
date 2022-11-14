package main.modele.evenement;

import main.controlleur.navigation.ChefRobot;
import main.modele.Incendie;
import main.modele.robot.Robot;

public class EteindreEvent extends Evenement{

    private Incendie incendie;

    public EteindreEvent(int dateStart, int duration, Robot robot, Incendie incendie){
        super(dateStart, duration, robot);
        this.incendie = incendie;
    }

    public void execute(){
        if(duration > 0){
            duration--;
        }
        else{
            try{
            if(robot.getPosition()!=incendie.getPosition()){
                throw new NoIncendieException("Le robot n'est pas sur la case de l'incendie");
            } else if (incendie.IsEteint()) {
                throw new NoIncendieException("L'incendie est déjà éteint");
            }else if(robot.IsEmpty()){
                throw new NoIncendieException("Le robot est vide");
            }
            else{
                int EauDeverse = robot.EmptyTank();
                incendie.Eteindre(EauDeverse);
                robot.nextEvent();
            }}
            catch(NoIncendieException e){
                System.err.println(e.getMessage());
            }

        }
    }
}
