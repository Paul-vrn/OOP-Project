package main.robot;

import main.Case;

public abstract class Robot {
    // Robots can be drones or tanks
    protected RobotType Type;
    protected Case Position;
    protected int Reservoir;
    protected int CapaciteReservoir;
    protected int BaseVitesse;
    protected int TempsRemplissage;

    public Case getPosition() {
        return this.Position;
    }

    public int getReservoir() {
        return this.Reservoir;
    }

    public int getCapaciteReservoir() {
        return this.CapaciteReservoir;
    }

    public abstract void setVitesse(int vitesse);

    // la première méthode c'est pour retourner la vitesse sur la case actuelle
    public abstract int getVitesse();

    // la seconde c'est pour n'importe quelle case
    public abstract int getVitesse(Case position);

    public abstract void setPosition(Case position);

    public abstract boolean CanMoveTo(Case Target_position);

    public String toString() {
        return "Robot " + this.Type + " en " + this.Position + " avec " + this.Reservoir + "L";
    }

    public void print() {
        System.out.println(this);
    }

}
