package main.controlleur.navigation;
import main.modele.Case;
import main.modele.Incendie;

/**
 * Classe représentant un noeud pour calculer le plus court chemin.
 */
public class Node{
        private Case position;
        private double fScore;
        private double gScore;

        private Node parent;

        public Node(Case position) {
            this.position = position;
            this.fScore = Double.MAX_VALUE;
            this.gScore= Double.MAX_VALUE;
        }

        public int comparaison(Node other) {
            if (this.fScore > other.fScore)
                return 1;
            if (this.fScore < other.fScore)
                return -1;
            return 0;
        }

        public Case getPosition() {
            return position;
        }

        public void setPosition(Case position) {
            this.position = position;
        }

        public double getfScore() {
            return fScore;
        }

        public void setfScore(double fScore) {
            this.fScore = fScore;
        }

        public double getgScore() {
            return gScore;
        }

        public void setgScore(double gScore) {
            this.gScore = gScore;
         }

        public Node getParent(){
            return this.parent;
        }

        public void setParent(Node futurParent){
            this.parent = futurParent;
        }

        public int hCalculator(Incendie incendie){
            return Math.abs(incendie.getPosition().getColonne() - this.position.getColonne()) + Math.abs(incendie.getPosition().getLigne() - this.position.getLigne());
        }

}