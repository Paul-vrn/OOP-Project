package main.robot;

import Exceptions.RobotTerrainException;
import main.Case;
import main.NatureTerrain;

public class Roues extends Robot {

    public void setVitesse(int vitesse) {
        this.BaseVitesse = vitesse;
    }

    public int getVitesse() {
        return getVitesse(this.Position);
    }

    public int getVitesse(Case position) {
        return this.BaseVitesse;
    }

    public void setPosition(Case position) {
        try {
            if (position.getNature() == NatureTerrain.TERRAIN_LIBRE || position.getNature() == NatureTerrain.HABITAT) {
                this.Position = position;
            } else {
                throw new RobotTerrainException(
                        "Le robot à roues ne peut se déplacer que sur du terrain libre ou dans des zones d'habitations");
            }
        } catch (RobotTerrainException e) {
            System.out.println(e.getMessage());
        }
    }

    public Roues(Case position) {
        setPosition(position);
        this.Type = RobotType.ROUES;
        this.BaseVitesse = 80;
        this.Reservoir = 0;
        // 30 minutes
        this.TempsRemplissage = 10 * 60;
    }

}