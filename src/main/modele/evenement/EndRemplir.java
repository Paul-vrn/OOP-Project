package main.modele.evenement;

import main.modele.robot.Robot;



public class EndRemplir extends Evenement{

    Robot robot;

    public EndRemplir(int date, Robot robot){
        super(date);
        this.robot = robot;
    }

    public void execute() {
        this.robot.RemplirReservoir();
        this.robot.ActionFin();
    }
}
