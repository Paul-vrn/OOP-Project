package oldtest;

import main.simulateur.io.LecteurDonnees;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import main.simulateur.DonneesSimulation;

public class TestLecteurDonnees {

    // public static void main(String[] args) {
    // if (args.length < 1) {
    // System.out.println("Syntaxe: java test.TestLecteurDonnees <nomDeFichier>");
    // System.exit(1);
    // }

    // try {
    // LecteurDonnees.lire(args[0]);
    // } catch (FileNotFoundException e) {
    // System.out.println("fichier " + args[0] + " inconnu ou illisible");
    // } catch (DataFormatException e) {
    // System.out.println("\n\t**format du fichier " + args[0] + " invalide: " +
    // e.getMessage());
    // }
    // public static void main(String[] args) {
    // try {
    // LecteurDonnees.lire("cartes/carteSujet.map");
    // } catch (FileNotFoundException e) {
    // System.out.println("fichier " + args[0] + " inconnu ou illisible");
    // } catch (DataFormatException e) {
    // System.out.println("\n\t**format du fichier " + args[0] + " invalide: " +
    // e.getMessage());
    // }
    // }
    public static void main(String[] args) {
        // lis les donne avec getData
        try {
            DonneesSimulation data = LecteurDonnees.getData("cartes/desertOfDeath-20x20.map");
            data.getCarte().print();
            for (int i = 0; i < data.getIncendies().size(); i++) {
                data.getIncendies().get(i).print();
            }
            for (int i = 0; i < data.getRobots().size(); i++) {
                data.getRobots().get(i).print();
            }
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

    }

}
