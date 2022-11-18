package main.modele.robot;

import main.controlleur.navigation.ChefRobot;
import main.modele.Case;
import main.modele.Carte;
import main.modele.evenement.Evenement;

import java.util.List;

/**
 * Classe abstraite représentant un robot
 */
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
    protected int timeUntilAvailable = 0;
    protected boolean allumee;

    protected String imageUrl = "images/robot.gif";

    protected String name = "Robot";

    /**
     * Constructeur de la classe Robot
     */
    protected Robot() {
        this.isOccupied = false;
        this.allumee = true;
    }

    /**
     * Setter de la position du robot
     * @param position la position du robot
     */
    public abstract void setPosition(Case position);

    /**
     * Getter de la position du robot
     * @return la position du robot
     */
    public Case getPosition() {
        return this.position;
    }

    /**
     * Getter du réservoir du robot
     * @return le réservoir du robot
     */
    public int getReservoir() {
        return this.reservoir;
    }

    /**
     * Méthode pour remplir le réservoir du robot de la capacité maximale
     */
    public void fillReservoir() {
        this.reservoir = this.capaciteReservoir;
    }

    /**
     * Setter de la vitess du robot
     * @param vitesse la vitesse du robot
     */
    public void setVitesse(int vitesse) {
        this.baseVitesse = vitesse;
    }



    /**
     * Getter de la vitesse du robot sur la case où il se trouve
     * @return la vitesse du robot
     */
    public double getVitesse() {
        return getVitesse(this.position);
    }
    /**
     * Getter de la vitesse du robot sur n'importe quelle case
     * @param position la position du robot
     * @return la vitesse du robot
     */
    public double getVitesse(Case position) {
        return this.baseVitesse;
    }

    /**
     * Getter de la vitess de base du robot
     * @return la vitesse de base du robot
     */
    public int getBaseVitesse() {
        return this.baseVitesse;
    }

    /**
     * Getter du temps de remplissage du robot
     * @return le temps de remplissage du robot
     */
    public int getTempsRemplissage() {
        return this.tempsRemplissage;
    }

    /**
     * Getter du débit de vidage du robot
     * @return le débit de vidage du robot
     */
    public double getDebitVidage() {
        return this.debitVidage;
    }

    /**
     * Getter du temps de déplacement du robot
     * @param carte la carte
     * @param position la position du robot
     * @return le temps de déplacement du robot
     */
    public int getTempsDeplacement(Carte carte, Case position) {
        return (int) Math.round(carte.getTailleCases() / (getVitesse(position) / 3.6));
    }

    /**
     * Getter du temps d'intervention du robot
     * @return le temps d'intervention du robot
     */
    public int getTempsIntervention() {
        return this.tempsIntervention;
    }

    /**
     * Getter indice de l'événement en cours
     * @return l'indice de l'événement en cours
     */
    public int getIndexEvenement() {
        return this.indexEvenement;
    }

    /**
     * Getter du type du robot
     * @return le type du robot
     */
    public RobotType getType() {
        return this.type;
    }

    /**
     * Retourne si le robot est occupé ou non
     * @return true si le robot est occupé, false sinon
     */
    public boolean isOccupied() {
        return this.isOccupied;
    }

    /**
     * Setter de l'occupation du robot
     * @param occupied true si le robot est occupé, false sinon
     */
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    /**
     * Méthode qui permet de savoir si le robot peut se déplacer à la case targetPosition
     * @param targetPosition la case cible
     * @return true si le robot peut se déplacer à la case targetPosition, false sinon
     */
    public abstract boolean canMoveTo(Case targetPosition);

    /**
     * Méthode qui permet de savoir si le robot peut être sur la case targetPosition
     * @param targetPosition la case cible
     * @return true si le robot peut être sur la case targetPosition, false sinon
     */
    public abstract boolean canRobotBeOnCase(Case targetPosition);

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    public String toString() {
        return "Robot " + this.type + " en " + this.position + " avec " + this.reservoir + "L, occupé="
                + this.isOccupied;
    }

    /**
     * Méthode qui vide le réservoir de qtEau
     *
     * @param qtEau la quantité d'eau à vider
     */
    public void emptyTank(int qtEau) {
        this.reservoir = Math.max(0, this.reservoir-qtEau);
    }

    /**
     * Méthode qui permet de savoir si le réservoire du robot est vide
     * @return true si le réservoir du robot est vide, false sinon
     */
    public boolean isEmpty() {
        return reservoir == 0;
    }

    /**
     * Méthode qui permet de savoir si le robot est allumé
     * @return true si le robot est allumé, false sinon
     */
    public boolean isAllumee() {
        return allumee;
    }

    /**
     * Setter de l'allumage du robot
     * @param allumee true si le robot est allumé, false sinon
     */
    public void setAllumee(boolean allumee) {
        this.allumee = allumee;
    }

    /**
     * Getter de l'image du robot
     * @return l'image du robot
     */
    public String getImage() {
        return this.imageUrl;
    }

    /**
     * Getter du nom du robot
     * @return le nom du robot
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode qui ajoute un event à la liste d'événements du robot
     * @param event l'événement à ajouter
     */
    public void addEvenement(Evenement event) {
        evenements.add(event);
    }

    /**
     * Méthode qui ajoute une liste d'événements à la liste d'événements du robot
     * @param events la liste d'événements à ajouter
     */
    public void addEvenements(List<Evenement> events) {
        this.evenements.addAll(events);
    }

    /**
     * Getter de la liste d'événements du robot
     * @return la liste d'événements du robot
     */
    public List<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Méthode qui passe à l'événement suivant, s'il n'y a plus d'événement, le robot n'est plus considéré comme occupé et il le notifie au chef robot
     */
    public void nextEvent() {
        if (indexEvenement < evenements.size()) {
            indexEvenement++;
        } else {
            this.isOccupied = false;
            ChefRobot.getInstance().notif = true;
        }
    }

    /**
     * Execute l'événement en cours
     */
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
