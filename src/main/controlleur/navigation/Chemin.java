package main.controlleur.navigation;

import main.modele.Case;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.robot.Robot;

import java.util.List;

public class Chemin {
    private Case start;
    private Case end;
    private Robot robot;
    private Incendie incendie;
    private int duration;
    private List<Evenement> events;

    public Chemin(Robot robot, Incendie incendie, int duration, List<Evenement> events) {
        this.robot = robot;
        this.incendie = incendie;
        this.duration = duration;
        this.events = events;
        this.start = robot.getPosition();
        this.end = incendie.getPosition();
    }

    public static class Comparators {
        public static final java.util.Comparator<Chemin> DURATION = (o1, o2) -> o1.duration - o2.duration;
    }

    /**
     * Getter de la case de départ
     * @return la case de départ
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * Getter de la case d'arrivée
     * @param robot le robot
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    /**
     * Getter de l'incendie
     * @return l'incendie
     */
    public Incendie getIncendie() {
        return incendie;
    }

    /**
     * Setter de l'incendie
     * @param incendie l'incendie
     */
    public void setIncendie(Incendie incendie) {
        this.incendie = incendie;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Getter de la liste des événements
     * @return la liste des événements
     */
    public List<Evenement> getEvents() {
        return events;
    }

    /**
     * Setter de la liste des événements
     * @param events la liste des événements
     */
    public void setEvents(List<Evenement> events) {
        this.events = events;
    }

    /**
     * Getter de la case de départ
     * @return la case de départ
     */
    public Case getStart() {
        return start;
    }

    /**
     * Setter de la case de départ
     * @param start la case de départ
     */
    public void setStart(Case start) {
        this.start = start;
    }

    /**
     * Getter de la case d'arrivée
     * @return la case d'arrivée
     */
    public Case getEnd() {
        return end;
    }

    /**
     * Setter de la case d'arrivée
     * @param end la case d'arrivée
     */
    public void setEnd(Case end) {
        this.end = end;
    }

    /**
     * Print pour tester
     */
    public void print() {
        System.out.println("Chemin de " + this.robot + " vers " + this.incendie);
        System.out.println("Durée : " + this.duration);
        System.out.println("Evenements : ");
        for (Evenement event : this.events) {
            System.out.println(event);
        }
    }
}
