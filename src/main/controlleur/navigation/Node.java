package main.controlleur.navigation;

import main.modele.Case;

/**
 * Classe représentant un noeud pour calculer le plus court chemin.
 */
public class Node {
    private Case position;
    private double fScore;
    private double gScore;

    private Node parent;

    /**
     * Constructeur de la classe Node
     *
     * @param position la position du noeud
     */
    public Node(Case position) {
        this.position = position;
        this.fScore = Double.MAX_VALUE;
        this.gScore = Double.MAX_VALUE;
    }

    /**
     * Getter de la position du noeud
     *
     * @return la position du noeud
     */
    public Case getPosition() {
        return position;
    }

    /**
     * Setter de la position du noeud
     *
     * @param position la position du noeud
     */
    public void setPosition(Case position) {
        this.position = position;
    }

    /**
     * Getter du fScore du noeud
     *
     * @return le fScore du noeud
     */
    public double getfScore() {
        return fScore;
    }

    /**
     * Setter du fScore du noeud
     *
     * @param fScore le fScore du noeud
     */
    public void setfScore(double fScore) {
        this.fScore = fScore;
    }

    /**
     * Getter du gScore du noeud
     *
     * @return le gScore du noeud
     */
    public double getgScore() {
        return gScore;
    }

    /**
     * Setter du gScore du noeud
     *
     * @param gScore le gScore du noeud
     */
    public void setgScore(double gScore) {
        this.gScore = gScore;
    }

    /**
     * Getter du parent du noeud
     *
     * @return le parent du noeud
     */
    public Node getParent() {
        return this.parent;
    }

    /**
     * Setter du parent du noeud
     *
     * @param futurParent le parent du noeud
     */
    public void setParent(Node futurParent) {
        this.parent = futurParent;
    }

    /**
     * Méthode permettant de calculer la distance entre deux noeuds
     *
     * @param tile le noeud avec lequel on veut calculer la distance
     * @return la distance entre les deux noeuds
     */
    public int hCalculator(Case tile) {
        return Math.abs(tile.getColonne() - this.position.getColonne())
                + Math.abs(tile.getLigne() - this.position.getLigne());
    }

}