package main.modele;

/**
 * Classe de gestion des incendies
 */
public class Incendie {

    private Case position; // la position de l'incendie
    private int eauNecessaireStart; // permet de garder en mémoire la quantité d'eau nécessaire au départ (sert à l'affichage)
    private int eauNecessaire; // la quantité d'eau nécessaire pour éteindre l'incendie
    private String image = "images/fire.gif"; // l'image de l'incendie
    private boolean isHandled; // permet de savoir si l'incendie est éteint ou non

    /**
     * Constructeur de la classe Incendie
     *
     * @param position      la position de l'incendie
     * @param eauNecessaire la quantité d'eau nécessaire pour éteindre l'incendie
     */
    public Incendie(Case position, int eauNecessaire) {
        this.position = position;
        this.eauNecessaire = eauNecessaire;
        this.eauNecessaireStart = eauNecessaire;
        this.isHandled = false;
    }

    /**
     * Getter de la position de l'incendie
     *
     * @return la position de l'incendie
     */
    public Case getPosition() {
        return this.position;
    }

    /**
     * Getter de la quantité d'eau nécessaire pour éteindre l'incendie
     *
     * @return la quantité d'eau nécessaire pour éteindre l'incendie
     */
    public int getEauNecessaire() {
        return this.eauNecessaire;
    }

    /**
     * Méthode permettant de mettre à jour la quantité d'eau nécessaire pour éteindre l'incendie
     *
     * @param quantiteEau la quantité d'eau utilisée pour éteindre l'incendie
     */
    public void eteindre(int quantiteEau) {
        this.eauNecessaire = Math.max(this.eauNecessaire - quantiteEau, 0);
    }

    /**
     * Méthode qui permet de savoir si l'incendie est pris en charge par un robot
     *
     * @return true si l'incendie est pris en charge par un robot, false sinon
     */
    public boolean isHandled() {
        return this.isHandled;
    }

    /**
     * Setter de l'attribut isHandled
     *
     * @param handle true si l'incendie est pris en charge par un robot, false sinon
     */
    public void setHandle(boolean handle) {
        this.isHandled = handle;
    }

    /**
     * Getter de l'eau nécessaire au départ
     *
     * @return l'eau nécessaire au départ
     */
    public int getEauNecessaireStart() {
        return eauNecessaireStart;
    }

    /**
     * Méthode qui permet de savoir si l'incendie est éteint
     *
     * @return true si l'incendie est éteint, false sinon
     */
    public boolean isEteint() {
        return this.eauNecessaire == 0;
    }

    /**
     * toString de la classe Incendie
     *
     * @return la position de l'incendie et la quantité d'eau nécessaire pour l'éteindre
     */
    public String toString() {
        return "Incendie (" + this.position.getLigne() + ", " + this.position.getColonne() + ") : "
                + this.eauNecessaire;
    }

    /**
     * Getter de l'image de l'incendie
     *
     * @return l'image de l'incendie
     */
    public String getImage() {
        return image;
    }

}
