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

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Incendie getIncendie() {
        return incendie;
    }

    public void setIncendie(Incendie incendie) {
        this.incendie = incendie;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Evenement> getEvents() {
        return events;
    }

    public void setEvents(List<Evenement> events) {
        this.events = events;
    }

    public Case getStart() {
        return start;
    }

    public void setStart(Case start) {
        this.start = start;
    }

    public Case getEnd() {
        return end;
    }

    public void setEnd(Case end) {
        this.end = end;
    }
}
