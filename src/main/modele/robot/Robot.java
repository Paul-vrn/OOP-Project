package main.modele.robot;

import main.modele.Case;
import main.modele.Carte;
import main.modele.evenement.Evenement;

import java.util.ArrayList;
import java.util.List;

public abstract class Robot {
    // Robots can be Drones or Chenilles or Pattes or Roues

    protected int id;
    protected RobotType type;
    protected Case position;
    protected int reservoir;
    protected int capaciteReservoir;

    protected int QuantiteEauParIntervention;
    protected int TempsIntervention;
    protected int baseVitesse;
    protected int tempsRemplissage;

    protected List<Evenement> evenements;
    protected int indexEvenement;

    protected boolean isOccupied;
    public int TimeUntilAvailable = 0;

    protected String imageUrl = "images/robot.gif";

    protected String name = "Robot";

    public Robot(int id) {
        this.id = id;
        this.isOccupied = false;
    }

    public abstract void setPosition(Case position);
    public Case getPosition() {
        return this.position;
    }

    public int getReservoir() {
        return this.reservoir;
    }

    public int getCapaciteReservoir() {
        return this.capaciteReservoir;
    }

    public void fillReservoir(){
        this.reservoir = this.capaciteReservoir;
        this.nextEvent();
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

    public int getIndexEvenement(){
        return this.indexEvenement;
    }

    public RobotType getType(){
        return this.type;
    }

    public boolean isOccupied(){return this.isOccupied;}
    public void setOccupied(boolean occupied){this.isOccupied = occupied;}
    public int getTimeUntilAvailable(){return TimeUntilAvailable;}

    public void setTimeUntilAvailable(int time){
        TimeUntilAvailable = time;
    }

    public void RemplirReservoir(){
        this.reservoir = this.capaciteReservoir;
    }


    public abstract boolean canMoveTo(Case targetPosition);

    public String toString() {
        return "Robot " + this.type + " en " + this.position + " avec " + this.reservoir + "L";
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


    public void addEvenement(Evenement event){
        evenements.add(event);
    }
    public void addEvenements(List<Evenement> events) {
        this.evenements.addAll(events);
    }

    public List<Evenement> getEvenements(){
        return evenements;
    }


    public void nextEvent(){
        if(indexEvenement < evenements.size()){
            indexEvenement++;
        }
    }

}
