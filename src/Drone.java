import Exceptions.RobotMaxSpeedException;

public class Drone extends Robot {

    // definition of the abstract setVitesse method
    public void setVitesse(int vitesse) {
        // if the speed is greater than the 100, throw an exception
        if (vitesse > 100) {
            try {
                throw new RobotMaxSpeedException("The speed of the drone is greater than 100");
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
        return this.BaseVitesse;
    }

    public void setPosition(Case position) {
        this.Position = position;
    }

    public Drone(Case position) {
        setPosition(position);
        this.Type = RobotType.DRONE;
        this.BaseVitesse = 100;
        this.Reservoir = 0;
        // 30 minutes
        this.TempsRemplissage = 30 * 60;
    }

}
