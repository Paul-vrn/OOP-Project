package main.modele.robot;

import main.modele.Case;

public abstract class Robot {
    // Robots can be Drones or Chenilles or Pattes or Roues
    protected RobotType type;
    protected Case position;
    protected int reservoir;
    protected int capaciteReservoir;
    protected int baseVitesse;
    protected int tempsRemplissage;

    protected String imageUrl = "images/robot.gif";

    protected String name = "Robot";

    public Case getPosition() {
        return this.position;
    }

    public int getReservoir() {
        return this.reservoir;
    }

    public int getCapaciteReservoir() {
        return this.capaciteReservoir;
    }

    public void setVitesse(int vitesse) {
        this.baseVitesse = vitesse;
    }

    // la première méthode c'est pour retourner la vitesse sur la case actuelle
    public double getVitesse() {
        return getVitesse(this.position);
    }

    // la seconde c'est pour n'importe quelle case
    public double getVitesse(Case position) {
        return this.baseVitesse;
    }

    public abstract void setPosition(Case position);

    public abstract boolean canMoveTo(Case targetPosition);

    public String toString() {
        return "Robot " + this.type + " en " + this.position + " avec " + this.reservoir + "L";
    }

    public void print() {
        System.out.println(this);
    }

    public String getImage(){return this.imageUrl;}
    public String getName() { return name; }
}
