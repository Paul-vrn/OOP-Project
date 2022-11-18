package main.modele.evenement;

/**
 * Classe représentant une exception levée lorsqu'un robot tente de se remplir sur une case interdite
 */
public class IllegalFillException extends Exception{
    /**
     * Constructeur de la classe IllegalFillException
     *
     * @param message Le message d'erreur
     */
    public IllegalFillException(String message){
        super(message);
    }
}
