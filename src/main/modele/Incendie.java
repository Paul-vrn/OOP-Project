package main.modele;

public class Incendie {

    private int id;
    private Case position;
    private int eauNecessaireStart; // permet de garder en mémoire la quantité d'eau nécessaire au départ (sert à l'affichage)
    private int eauNecessaire;

    private boolean isHandled;


    public Incendie(int id, Case position, int eauNecessaire) {
        this.id = id;
        this.position = position;
        this.eauNecessaire = eauNecessaire;
        this.eauNecessaireStart = eauNecessaire;
        this.isHandled = false;
    }

    public Case getPosition() {
        return this.position;
    }

    public int getEauNecessaire() {
        return this.eauNecessaire;
    }

    public void eteindre(int quantiteEau){
        int res = this.eauNecessaire - quantiteEau;
        if(res<=0){
            this.eauNecessaire = 0;
        }
        else{
            this.eauNecessaire = res;
        }
    }

    public boolean isHandled(){
        return this.isHandled;
    }
    public void setHandle(boolean handle){
        this.isHandled = handle;
    }

    public int getEauNecessaireStart() {
        return eauNecessaireStart;
    }
    public boolean isEteint(){
        return this.eauNecessaire == 0;
    }

    public String toString() {
        return "Incendie (" + this.position.getLigne() + ", " + this.position.getColonne() + ") : "
                + this.eauNecessaire;
    }

    public String getImage() {
        return "images/fire.gif";
    }
}
