package main.modele.evenement;

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
        if (this.duration > 0) {
            this.duration--;
        } else {
            try {
                switch (robot.getType()) {
                    case DRONE:
                        if (robot.getPosition().getNature() == NatureTerrain.EAU) {
                            robot.fillReservoir();
                        } else {
                            throw new IllegalFillException("Tentative de remplissage interdit, les drones doivent être sur une case d'eau pour pouvoir remplir leur réservoir.");
                        }
                        break;
                    //on vérifie qu'il y a bien une case d'eau adjacente sans sortir de la carte
                    case ROUES:
                    case CHENILLES:
                        if (robot.getPosition().getColonne() + 1 < carte.getNbColonnes()) {
                            if (carte.getCase(robot.getPosition().getColonne() + 1, robot.getPosition().getLigne()).getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                break;
                            }
                        }
                        if (robot.getPosition().getColonne() - 1 >= 0) {
                            if (carte.getCase(robot.getPosition().getColonne() - 1, robot.getPosition().getLigne()).getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                break;
                            }
                        }
                        if (robot.getPosition().getLigne() + 1 < carte.getNbLignes()) {
                            if (carte.getCase(robot.getPosition().getColonne(), robot.getPosition().getLigne() + 1).getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                break;
                            }
                        }
                        if (robot.getPosition().getLigne() - 1 >= 0) {
                            if (carte.getCase(robot.getPosition().getColonne(), robot.getPosition().getLigne() - 1).getNature() == NatureTerrain.EAU) {
                                robot.fillReservoir();
                                break;
                            }
                        }
                        throw new IllegalFillException("Les robots à roues ou à chenilles doivent être adjacent à une case d'eau pour pouvoir se remplir");

                    case PATTES:
                        throw new IllegalFillException("Tentative de remplir un robot à pattes (réservoir infinie)");
                }
            } catch (IllegalFillException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
