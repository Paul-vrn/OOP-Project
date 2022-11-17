package main.modele.robot;

import main.controlleur.navigation.ChefRobot;
import main.modele.Case;
import main.modele.Carte;
import main.modele.evenement.Evenement;

import java.util.List;

public abstract class Robot {
    // Robots can be Drones or Chenilles or Pattes or Roues

    protected RobotType type;
    protected Case position;
    protected int reservoir;
    protected int capaciteReservoir;

    protected int quantiteEauParIntervention;
    protected int tempsIntervention;
    protected int baseVitesse;
    protected int tempsRemplissage;

    protected double debitVidage;

    protected List<Evenement> evenements;
    protected int indexEvenement;

    protected boolean isOccupied;
    public int timeUntilAvailable = 0;

    protected String imageUrl = "images/robot.gif";

    protected String name = "Robot";

    protected Robot() {
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

    public void fillReservoir() {
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

    public int getBaseVitesse() {
        return this.baseVitesse;
    }

    public double getDebitVidage() {
        return this.debitVidage;
    }

    public int getTempsDeplacement(Carte carte, Case position) {
        return (int) Math.round(carte.getTailleCases() / (getVitesse(position) / 3.6));
    }

    public int getTempsDeplacement(Carte carte) {
        return getTempsDeplacement(carte, this.position);
    }

    public int getTempsIntervention() {
        return this.tempsIntervention;
    }

    public int getIndexEvenement() {
        return this.indexEvenement;
    }

    public RobotType getType() {
        return this.type;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public int getTimeUntilAvailable() {
        return timeUntilAvailable;
    }

    public void setTimeUntilAvailable(int time) {
        timeUntilAvailable = time;
    }

    public void remplirReservoir() {
        this.reservoir = this.capaciteReservoir;
    }

    public abstract boolean canMoveTo(Case targetPosition);

    public abstract boolean canRobotBeOnCase(Case targetPosition);

    public String toString() {
        return "Robot " + this.type + " en " + this.position + " avec " + this.reservoir + "L, occupé="
                + this.isOccupied;
    }

    public int emptyTank(int qtEau) {
        if (qtEau > this.reservoir) {
            int tmp = this.reservoir;
            this.reservoir = 0;
            return tmp;
        } else {
            this.reservoir -= qtEau;
            return qtEau;
        }
    }

    public boolean isEmpty() {
        return reservoir == 0;
    }

    public String getImage() {
        return this.imageUrl;
    }

    public String getName() {
        return name;
    }

    public void addEvenement(Evenement event) {
        evenements.add(event);
    }

    public void addEvenements(List<Evenement> events) {
        this.evenements.addAll(events);
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void nextEvent() {
        if (indexEvenement < evenements.size()) {
            indexEvenement++;
        } else {
            this.isOccupied = false;
            ChefRobot.getInstance().notif = true;
        }
    }

    public void execute() {
        if (indexEvenement < evenements.size()) {
            evenements.get(indexEvenement).execute();
        } else {
            System.out.println("No more events for this robot " + this.getName() + " " + this.getPosition());
            this.isOccupied = false;
            ChefRobot.getInstance().notif = true;
        }
    }

}
