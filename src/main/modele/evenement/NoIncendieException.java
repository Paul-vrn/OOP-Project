package main.modele.evenement;

/**
 * Classe représentant une exception levée lorsqu'il n'y a plus d'incendie à éteindre
 */
public class NoIncendieException extends Exception{
    /**
     * Constructeur de la classe NoIncendieException
     *
     * @param message Le message d'erreur
     */
    public NoIncendieException(String message){
        super(message);
    }
}
