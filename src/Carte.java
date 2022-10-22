import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Carte extends Case {
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;
    private ArrayList<ArrayList<Case>> cases;

    public Carte(int tailleCases, int nblignes, int nbColonnes) {
        this.tailleCases = tailleCases;
        this.nbLignes = nblignes;
        this.nbColonnes = nbColonnes;
        this.cases = new ArrayList<ArrayList<Case>>();
        for (int i = 0; i < this.nbLignes; i++) {
            this.cases.add(new ArrayList<Case>());
            for (int j = 0; j < this.nbColonnes; j++) {
                this.cases.get(i).add(new Case(i, j, NatureTerrain.EAU));
            }
        }
    }

    public int getTailleCases() {
        return this.tailleCases;
    }

    public int getNbLignes() {
        return this.nbLignes;
    }

    public int getNbColonnes() {
        return this.nbColonnes;
    }

    public ArrayList<ArrayList<Case>> getCases() {
        return this.cases;
    }

    public Case getCase(int ligne, int colonne) {
        return this.cases.get(ligne).get(colonne);
    }

    public void print() {
        System.out.println("Nombre de lignes : " + this.nbLignes + "\n" + "Nombre de colonnes : " + this.nbColonnes
                + "\n" + "Taille des cases : " + this.tailleCases);
        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                this.cases.get(i).get(j).print();
            }
            System.out.println();
        }
    }

    public void print_board_style() {
        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                System.out.print("[" + this.cases.get(i).get(j).getNature().toString().charAt(0) + "]");
            }
            System.out.println();
        }
    }

    public Carte(String filename) {

        try (Scanner scanner = new Scanner(new File("../cartes/" + filename))) {

            // tant que la ligne courante du fichier ne commmence pas par un int on next
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
            }
            this.nbLignes = scanner.nextInt();
            this.nbColonnes = scanner.nextInt();
            this.tailleCases = scanner.nextInt();

            this.cases = new ArrayList<ArrayList<Case>>();

            String line = scanner.nextLine();
            while (!line.equals("# l0")) {
                line = scanner.nextLine();
            }
            line = scanner.nextLine();
            for (int i = 0; i < this.nbLignes; i++) {
                this.cases.add(new ArrayList<Case>());
                for (int j = 0; j < this.nbColonnes; j++) {
                    switch (line.charAt(0)) {
                        case 'E':
                            this.cases.get(i).add(new Case(i, j, NatureTerrain.EAU));
                            break;
                        case 'F':
                            this.cases.get(i).add(new Case(i, j, NatureTerrain.FORET));
                            break;
                        case 'R':
                            this.cases.get(i).add(new Case(i, j, NatureTerrain.ROCHE));
                            break;
                        case 'T':
                            this.cases.get(i).add(new Case(i, j, NatureTerrain.TERRAIN_LIBRE));
                            break;
                        case 'H':
                            this.cases.get(i).add(new Case(i, j, NatureTerrain.HABITAT));
                            break;
                        default:
                            break;
                    }
                    line = scanner.nextLine();
                }
                line = scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}
