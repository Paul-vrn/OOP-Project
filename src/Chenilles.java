import Exceptions.RobotMaxSpeedException;
import Exceptions.RobotTerrainException;

public class Chenilles extends Robot {
    // definition of the abstract setVitesse method
    public void setVitesse(int vitesse) {
        // if the speed is greater than the 100, throw an exception
        if (vitesse > 80) {
            try {
                throw new RobotMaxSpeedException("The speed of the robot is greater than 80");
            } catch (RobotMaxSpeedException e) {
                e.printStackTrace();
            }
        } else {
            this.BaseVitesse = vitesse;
        }
    }

    public int getVitesse() {
        return getVitesse(this.Position);
    }

    public int getVitesse(Case position) {
        if (position.getNature() == Case.NatureTerrain.FORET) {
            return this.BaseVitesse / 2;
        } else {
            return this.BaseVitesse;
        }
    }

    public void setPosition(Case position) {
        if (position.getNature() == Case.NatureTerrain.EAU || position.getNature() == Case.NatureTerrain.ROCHE) {
            try {
                throw new RobotTerrainException("The robot can't be on the water or on rocks");
            } catch (RobotTerrainException e) {
                e.printStackTrace();
            }
        } else {
            this.Position = position;
        }
    }

    public Chenilles(Case position) {
        setPosition(position);
        this.Type = RobotType.CHENILLES;
        this.BaseVitesse = 60;
        this.Reservoir = 0;
        // 30 minutes
        this.TempsRemplissage = 5 * 60;
    }

}
