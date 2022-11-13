package main.modele.evenement;

import main.modele.robot.Robot;
import main.modele.Incendie;

public class Eteindre extends Evenement{

    private Robot robot;
    private Incendie incendie;

    public Eteindre(int date, Robot robot, Incendie incendie){
        super(date);
        this.robot = robot;
        this.incendie = incendie;
    }

    /*
    Si le robot si situe bien sur une case ou il y a un incendie on vide le réservoir du robot
    (d'une quantité qui dépend de la vitesse à laquelle le robot peut se vider et de la vietesse de la simu)
    et on diminue la quantité d'eau nécessaire à éteindre l'incendie dans les attributs de l'incendie
     */
    public void execute() {
        int EauDeverse = this.robot.EmptyTank();
        this.incendie.Eteindre(EauDeverse);
    }
}
