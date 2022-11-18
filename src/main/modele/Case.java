package main.modele;

import java.util.Objects;

/**
 * Classe représentant une case de la carte.
 */
public class Case {

    private int ligne; // ligne de la case
    private int colonne; // colonne de la case
    private NatureTerrain nature; // nature de la case

    /**
     * Constructeur de la classe Case
     * @param ligne la ligne de la case
     * @param colonne la colonne de la case
     * @param nature la nature de la case
     */
    public Case(int ligne, int colonne, NatureTerrain nature) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    /**
     * Constructeur de la classe Case
     * @param ligne la ligne de la case
     * @param colonne la colonne de la case
     */
    public Case(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = NatureTerrain.UNDEFINED;
    }

    /**
     * Setter du type de terrain de la case
     * @param nature le type de terrain
     */
    public void setNature(NatureTerrain nature) {
        this.nature = nature;
    }

    /**
     * Getter de la ligne de la case
     * @return la ligne de la case
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * Getter de la colonne de la case
     * @return la colonne de la case
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * Getter de la nature de la case
     * @return la nature de la case
     */
    public NatureTerrain getNature() {
        return this.nature;
    }

    /**
     * Retourne si le terrain est de type eau
     * @return true si le terrain est de type eau, false sinon
     */
    public boolean isEau() {
        return this.nature == NatureTerrain.EAU;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "main.modele.Case (" + this.ligne + ", " + this.colonne + ") : " + this.nature;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Case)) return false;
        Case aCase = (Case) o;
        return ligne == aCase.ligne && colonne == aCase.colonne;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne);
    }
}