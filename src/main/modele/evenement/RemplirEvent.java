package main.modele.evenement;

import main.controlleur.navigation.ChefRobot;
import main.modele.Carte;
import main.modele.robot.Robot;

/**
 * Classe Evenement de remplissage du réservoir d'un robot
 */
public class RemplirEvent extends Evenement {

    private Carte carte; // carte du robot

    /**
     * Constructeur de l'évènement de remplissage du réservoir d'un robot
     *
     * @param duration durée de l'évènement
     * @param robot    robot qui va se remplir
     * @param carte    carte du robot
     */
    public RemplirEvent(int duration, Robot robot, Carte carte) {
        super(duration, robot);
        this.carte = carte;
    }

    /**
     * Execute l'évènement
     */
    public void execute() {
        int n = ChefRobot.getInstance().n;
        if (this.duration >= n) {
            this.duration = duration - n;
        } else {
            try {
                switch (robot.getType()) {
                    case DRONE:
                        if (robot.getPosition().isEau()) {
                            robot.fillReservoir();
                            robot.nextEvent();
                        } else {
                            throw new IllegalFillException("Tentative de remplissage interdit, les drones doivent être sur une case d'eau pour pouvoir remplir leur réservoir.");
                        }
                        break;
                    // on vérifie qu'il y a bien une case d'eau adjacente sans sortir de la carte
                    case ROUES, CHENILLES:
                        int robotCol = robot.getPosition().getColonne();
                        int robotLig = robot.getPosition().getLigne();
                        if (
                                (robotCol + 1 < carte.getNbColonnes() && carte.getCase(robotLig, robotCol + 1).isEau())
                                        || (robotCol - 1 >= 0 && carte.getCase(robotLig, robotCol - 1).isEau())
                                        || (robotLig + 1 < carte.getNbLignes() && carte.getCase(robotLig + 1, robotCol).isEau())
                                        || (robotLig - 1 >= 0 && carte.getCase(robotLig - 1, robotCol).isEau())
                        ) {
                            robot.fillReservoir();
                            robot.nextEvent();
                            break;
                        }
                        throw new IllegalFillException("Les robots à roues ou à chenilles doivent être adjacent à une case d'eau pour pouvoir se remplir");
                    case PATTES:
                        throw new IllegalFillException("Tentative de remplir un robot à pattes (réservoir infinie)");
                    default:
                        throw new IllegalFillException("Type de robot inconnu");
                }
            } catch (IllegalFillException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
