package main;

import main.controlleur.Simulateur;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Classe principale du projet
 */
public class Application {

    /**
     * Default constructor
     */
    public Application() {}
    /**
     * Méthode principale
     *
     * @param args les arguments de la ligne de commande
     * @throws FileNotFoundException si le fichier de log n'est pas trouvé
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream("logs/out.txt");
        PrintStream err = new PrintStream("logs/err.txt");
        System.setOut(out);
        System.setErr(err);
        new Simulateur(args);
    }
}

