package main.modele.robot;

/**
 * Classe représentant une exception levée lorsqu'un robot tente de se déplacer à une vitesse supérieure à sa vitesse maximale
 */
public class RobotMaxSpeedException extends Exception {
    /**
     * Constructeur de la classe RobotMaxSpeedException
     *
     * @param message Le message d'erreur
     */
    public RobotMaxSpeedException(String message) {
        super(message);
    }
}
