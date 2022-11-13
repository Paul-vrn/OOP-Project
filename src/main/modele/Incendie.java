package main.modele;

public class Incendie {
    private Case position;
    private int eauNecessaire;

    private boolean is_handled;

    private boolean is_eteint;

    public Incendie(Case position, int eauNecessaire) {
        this.position = position;
        this.eauNecessaire = eauNecessaire;
        this.is_handled = false;
        this.is_eteint = false;
    }

    public Case getPosition() {
        return this.position;
    }

    public int getEauNecessaire() {
        return this.eauNecessaire;
    }

    public void Eteindre(int QuantiteEau){
        int res = this.eauNecessaire - QuantiteEau;
        if(res<=0){
            this.eauNecessaire = 0;
            this.is_eteint = true;
        }
        else{
            this.eauNecessaire = res;
        }
    }

    public boolean IsHandled(){
        return this.is_handled;
    }

    public boolean IsEteint(){
        return this.is_eteint;
    }

    public String toString() {
        return "Incendie (" + this.position.getLigne() + ", " + this.position.getColonne() + ") : "
                + this.eauNecessaire;
    }

    public void print() {
        System.out.println(this);
    }

    public String getImage() {
        return "images/fire.gif";
    }
}
