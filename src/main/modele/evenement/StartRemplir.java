package main.modele.evenement;

import main.modele.NatureTerrain;
import main.modele.robot.Robot;
import main.modele.Carte;


public class StartRemplir extends Evenement{

    Robot robot;
    Carte carte;

    public StartRemplir(int date, Robot robot, Carte carte){
        super(date);
        this.robot = robot;
        this.carte = carte;
    }

    public void execute(){
        try{
            switch(robot.getType()){
                case DRONE:
                    if(robot.getPosition().getNature() == NatureTerrain.EAU){
                        robot.ActionDebut();
                    }
                    else{
                        throw new IllegalFillException("Tentative de remplissage interdit, les drones doivent être sur une case d'eau pour pouvoir remplir leur réservoir.");
                    }
                    break;
                //on vérifie qu'il y a bien une case d'eau adjacente sans sortir de la carte
                case ROUES:
                case CHENILLES:
                    if(robot.getPosition().getColonne() + 1 < carte.getNbColonnes()){
                        if(carte.getCase(robot.getPosition().getColonne() + 1, robot.getPosition().getLigne()).getNature() == NatureTerrain.EAU){
                            robot.ActionDebut();
                            break;
                        }
                    }
                    if(robot.getPosition().getColonne() -1 >= 0){
                        if(carte.getCase(robot.getPosition().getColonne() - 1, robot.getPosition().getLigne()).getNature() == NatureTerrain.EAU){
                            robot.ActionDebut();
                            break;
                        }
                    }
                    if(robot.getPosition().getLigne() + 1 < carte.getNbLignes()){
                        if(carte.getCase(robot.getPosition().getColonne(),robot.getPosition().getLigne() + 1).getNature() == NatureTerrain.EAU){
                            robot.ActionDebut();
                            break;
                        }
                    }
                    if(robot.getPosition().getLigne() -1 >= 0){
                        if(carte.getCase(robot.getPosition().getColonne(),robot.getPosition().getLigne() - 1).getNature() == NatureTerrain.EAU){
                            robot.ActionDebut();
                            break;
                        }
                    }
                    throw new IllegalFillException("Les robots à roues ou à chenilles doivent être adjacent à une case d'eau pour pouvoir se remplir");

                case PATTES:
                    throw new IllegalFillException("Tentative de remplir un robot à pattes (réservoir infinie)");
            }
        }
        catch(IllegalFillException e){
            System.err.println(e.getMessage());
        }
    }
}
