package main.simulateur.evenement;

public abstract class Evenement {
    private int date;

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

    public void print() {
        System.out.println(this);
    }
}
