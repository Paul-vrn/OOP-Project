package main.modele;

/**
 * Classe représentant une carte du jeu.
 */
public class Carte {
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;
    private Case[][] cases;

    /**
     * Constructeur de la classe Carte.
     * @param nblignes nombre de lignes de la carte
     * @param nbColonnes nombre de colonnes de la carte
     * @param tailleCases taille des cases de la carte
     */
    public Carte(int nblignes, int nbColonnes, int tailleCases) {
        this.tailleCases = tailleCases;
        this.nbLignes = nblignes;
        this.nbColonnes = nbColonnes;
        this.cases = new Case[nblignes][nbColonnes];
        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                this.cases[i][j] = new Case(i, j, NatureTerrain.EAU);
            }
        }
    }

    /**
     * Constructeur de la classe Carte.
     * @param ligne ligne de la case
     * @param colonne colonne de la case
     * @param nature nature de la case
     */
    public void setNature(int ligne, int colonne, NatureTerrain nature) {
        this.cases[ligne][colonne].setNature(nature);
    }

    /**
     * Retourne la taille des cases de la carte.
     * @return taille des cases de la carte
     */
    public int getTailleCases() {
        return this.tailleCases;
    }

    /**
     * Retourne le nombre de lignes de la carte.
     * @return nombre de lignes de la carte
     */
    public int getNbLignes() {
        return this.nbLignes;
    }

    /**
     * Retourne le nombre de colonnes de la carte.
     * @return nombre de colonnes de la carte
     */
    public int getNbColonnes() {
        return this.nbColonnes;
    }

    /**
     * Retourne les cases de la carte.
     * @return matrice de cases de la carte
     */
    public Case[][] getCases() {
        return this.cases;
    }

    /**
     * Retourne la case à la ligne et colonne passées en paramètres.
     * @param ligne ligne de la case
     * @param colonne colonne de la case
     * @return case à la ligne et colonne passées en paramètres
     */
    public Case getCase(int colonne, int ligne) {
        return this.cases[colonne][ligne];
    }
}
