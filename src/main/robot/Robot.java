package main.robot;

import main.Case;

public abstract class Robot {
    // Robots can be drones or tanks
    protected RobotType type;
    protected Case position;
    protected int reservoir;
    protected int capaciteReservoir;
    protected int baseVitesse;
    protected int tempsRemplissage;

    public Case getPosition() {
        return this.position;
    }

    public int getReservoir() {
        return this.reservoir;
    }

    public int getCapaciteReservoir() {
        return this.capaciteReservoir;
    }

    public abstract void setVitesse(int vitesse);

    // la première méthode c'est pour retourner la vitesse sur la case actuelle
    public abstract double getVitesse();

    // la seconde c'est pour n'importe quelle case
    public abstract double getVitesse(Case position);

    public abstract void setPosition(Case position);

    public abstract boolean canMoveTo(Case targetPosition);

    public String toString() {
        return "Robot " + this.type + " en " + this.position + " avec " + this.reservoir + "L";
    }

    public void print() {
        System.out.println(this);
    }

}
