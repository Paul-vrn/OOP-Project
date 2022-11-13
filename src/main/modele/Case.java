package main.modele;

import java.util.Objects;

public class Case {

    // ATTRIBUTS DES CASES
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    public Case(int ligne, int colonne, NatureTerrain nature) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    public Case(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = NatureTerrain.UNDEFINED;
    }

    // DEFAULT CONSTRUCTOR
    public Case() {
    }

    public void setNature(NatureTerrain nature) {
        this.nature = nature;
    }

    public int getLigne() {
        return this.ligne;
    }

    public int getColonne() {
        return this.colonne;
    }

    public NatureTerrain getNature() {
        return this.nature;
    }

    @Override
    public String toString() {
        return "main.modele.Case (" + this.ligne + ", " + this.colonne + ") : " + this.nature;
    }

    public void print() {
        System.out.println(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Case)) return false;
        Case aCase = (Case) o;
        return ligne == aCase.ligne && colonne == aCase.colonne;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ligne, colonne);
    }


}