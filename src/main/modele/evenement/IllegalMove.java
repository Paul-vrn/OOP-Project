package main.modele.evenement;

/**
 * Classe représentant une exception levée lorsqu'un robot tente de se déplacer sur une case interdite
 */
public class IllegalMove extends Exception {
    /**
     * Constructeur de la classe IllegalMove
     *
     * @param message Le message d'erreur
     */
    public IllegalMove(String message) {
        super(message);
    }
}
