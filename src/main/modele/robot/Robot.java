package main.modele.robot;

import main.modele.Case;
import main.modele.Carte;

public abstract class Robot {
    // Robots can be Drones or Chenilles or Pattes or Roues
    protected RobotType type;
    protected Case position;
    protected int reservoir;
    protected int capaciteReservoir;

    protected int QuantiteEauParIntervention;
    protected int TempsIntervention;
    protected int baseVitesse;
    protected int tempsRemplissage;

    protected boolean ActionEnCours = false;
    public int AvailableAt = 0;

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

    public int getTempsDeplacement(Carte carte, Case position){
        return (int)Math.round(carte.getTailleCases()/(getVitesse(position)/3.6));
    }

    public int getTempsDeplacement(Carte carte){
        return getTempsDeplacement(carte, this.position);
    }

    public int getTempsIntervention(){
        return this.TempsIntervention;
    }

    public RobotType getType(){
        return this.type;
    }

    public boolean IsActionEnCours(){return this.ActionEnCours;}

    public int AvailableAt(){return AvailableAt;}

    public void ActionDebut(){this.ActionEnCours = true;}

    public void ActionFin(){this.ActionEnCours = false;}

    public void RemplirReservoir(){
        this.reservoir = this.capaciteReservoir;
    }

    public abstract void setPosition(Case position);

    public abstract boolean canMoveTo(Case targetPosition);

    public String toString() {
        return "Robot " + this.type + " en " + this.position + " avec " + this.reservoir + "L";
    }

    public void print() {
        System.out.println(this);
    }

    public int EmptyTank(){
        this.reservoir = this.reservoir - this.QuantiteEauParIntervention;
        return this.QuantiteEauParIntervention;
    }

    public boolean IsEmpty(){
        return reservoir==0;
    }

    public String getImage(){return this.imageUrl;}
    public String getName() { return name; }
}
