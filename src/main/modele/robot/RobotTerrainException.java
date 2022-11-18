package main.modele.robot;

/**
 * Classe représentant une exception levée lorsqu'un robot tente de se déplacer sur une case interdite
 */
public class RobotTerrainException extends Exception {
    /**
     * Constructeur de la classe RobotTerrainException
     *
     * @param message Le message d'erreur
     */
    public RobotTerrainException(String message) {
        super(message);
    }
}
