package main.modele.evenement;

import main.modele.Case;
import main.modele.Carte;
import main.modele.robot.Robot;
import main.modele.Direction;

public class Move extends Evenement{
    private Robot robot;
    private Case caseCible;
    /**

     */
    public StartMove(int date, Robot robot, Case caseCible){
        super(date);
        this.robot = robot;
        this.caseCible = caseCible;
    }

    public Move(int date, Robot robot, Carte carte, Direction direction){
        super(date);
        this.robot = robot;
        try{
            this.caseCible = carte.getCase(-1,-1);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Tentative de bouger en dehors de la carte");
        }
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
