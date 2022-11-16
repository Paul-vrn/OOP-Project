package main.modele.evenement;

import main.modele.robot.Robot;

public abstract class Evenement {


    protected int dateStart;

    protected int duration;

    protected Robot robot;


    public Evenement(int dateStart, int duration, Robot robot) {
        this.dateStart = dateStart;
        this.duration = duration;
        this.robot = robot;
    }

    public int getDate() {
        return this.dateStart;
    }

    public abstract void execute();

    public String toString() {
        return "Evenement (" + this.dateStart + ") : duration="+this.duration;
    }
}
