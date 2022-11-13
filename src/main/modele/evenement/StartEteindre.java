package main.modele.evenement;

import main.modele.robot.Robot;
import main.modele.Incendie;

public class StartEteindre extends Evenement{

    private Robot robot;
    private Incendie incendie;

    public StartEteindre(int date, Robot robot, Incendie incendie){
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
        try{
            if (robot.getPosition().getColonne() != incendie.getPosition().getColonne() || robot.getPosition().getLigne()!=incendie.getPosition().getLigne()) {
                throw new NoIncendieException("Le robot n'est pas sur la même case que l'incendie");
            } else if (incendie.IsEteint()) {
                throw new NoIncendieException("L'incendie est déjà éteint");
            } else if(robot.IsEmpty()){
                throw new NoIncendieException("Le robot est vide");
            }
            else {
                this.robot.ActionDebut();
            }
        }
        catch(NoIncendieException e){
            System.err.println(e.getMessage());
        }
    }
}
