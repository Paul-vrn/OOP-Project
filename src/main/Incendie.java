package main;

public class Incendie {
    private Case position;
    private int eauNecessaire;

    public Incendie(Case position, int eauNecessaire) {
        this.position = position;
        this.eauNecessaire = eauNecessaire;
    }

    public Case getPosition() {
        return this.position;
    }

    public int getEauNecessaire() {
        return this.eauNecessaire;
    }

    public String toString() {
        return "Incendie (" + this.position.getLigne() + ", " + this.position.getColonne() + ") : "
                + this.eauNecessaire;
    }

    public void print() {
        System.out.println(this);
    }
}
