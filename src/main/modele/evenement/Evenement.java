package main.modele.evenement;

public abstract class Evenement {
    protected int date;

    public Evenement(int date) {
        this.date = date;
    }

    public int getDate() {
        return this.date;
    }

    public abstract void execute();

    public String toString() {
        return "Evenement (" + this.date + ")";
    }
}
