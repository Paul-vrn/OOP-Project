package main.modele.evenement;

import main.controlleur.navigation.ChefRobot;
import main.modele.Carte;
import main.modele.NatureTerrain;
import main.modele.robot.Robot;

public class RemplirEvent extends Evenement {

    private Carte carte;

    public RemplirEvent(int dateStart, int duration, Robot robot, Carte carte) {
        super(dateStart, duration, robot);
        this.carte = carte;
    }

    public void execute() {
        int n = ChefRobot.getInstance().n;

        if (this.duration >= n) {
            this.duration = duration - n;
        } else {
            try {
                // print le type du robot
                switch (robot.getType()) {
                    case DRONE:
                        if (robot.getPosition().getNature() == NatureTerrain.EAU) {
                            // print le servervoir du robot
                            robot.fillReservoir();
                            robot.nextEvent();
                        } else {
                            throw new IllegalFillException(
                                    "Tentative de remplissage interdit, les drones doivent être sur une case d'eau pour pouvoir remplir leur réservoir.");
                        }
                        break;
                    // on vérifie qu'il y a bien une case d'eau adjacente sans sortir de la carte
                    case CHENILLES:
                    case ROUES:
                        if (robot.getPosition().getColonne() + 1 < carte.getNbColonnes()) {
                            if (carte.getCases()[robot.getPosition().getLigne()][robot.getPosition().getColonne() + 1]
                                    .getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                robot.nextEvent();
                                break;
                            }
                        }
                        if (robot.getPosition().getColonne() - 1 >= 0) {
                            if (carte.getCases()[robot.getPosition().getLigne()][robot.getPosition().getColonne() - 1]
                                    .getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                robot.nextEvent();
                                break;
                            }
                        }
                        if (robot.getPosition().getLigne() + 1 < carte.getNbLignes()) {
                            if (carte.getCases()[robot.getPosition().getLigne() + 1][robot.getPosition().getColonne()]
                                    .getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                robot.nextEvent();
                                break;
                            }
                        }
                        if (robot.getPosition().getLigne() - 1 >= 0) {
                            if (carte.getCases()[robot.getPosition().getLigne() - 1][robot.getPosition().getColonne()]
                                    .getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                robot.nextEvent();
                                break;
                            }
                        }
                        throw new IllegalFillException(
                                "Les robots à roues ou à chenilles doivent être adjacent à une case d'eau pour pouvoir se remplir");

                    case PATTES:
                        throw new IllegalFillException("Tentative de remplir un robot à pattes (réservoir infinie)");
                }
            } catch (IllegalFillException e) {
                System.err.println(e.getMessage());
            }
        }

    }
}
