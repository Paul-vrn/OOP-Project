package main;

/**
 * Classe représentant une carte du jeu.
 *
 *
 */
public class Carte {
    private int tailleCases;
    private int nbLignes;
    private int nbColonnes;
    private Case[][] cases;

    /**
     * Constructeur de la classe Carte.
     * @param nblignes nombre de lignes de la carte
     * @param nbColonnes nombre de colonnes de la carte
     * @param tailleCases taille des cases de la carte
     */
    public Carte(int nblignes, int nbColonnes, int tailleCases) {
        this.tailleCases = tailleCases;
        this.nbLignes = nblignes;
        this.nbColonnes = nbColonnes;
        this.cases = new Case[nblignes][nbColonnes];
        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                this.cases[i][j] = new Case(i, j, NatureTerrain.EAU);
            }
        }
    }

    /**
     * Constructeur de la classe Carte.
     * @param ligne ligne de la case
     * @param colonne colonne de la case
     * @param nature nature de la case
     */
    public void setNature(int ligne, int colonne, NatureTerrain nature) {
        this.cases[ligne][colonne].setNature(nature);
    }

    /**
     * Retourne la taille des cases de la carte.
     * @return taille des cases de la carte
     */
    public int getTailleCases() {
        return this.tailleCases;
    }

    /**
     * Retourne le nombre de lignes de la carte.
     * @return nombre de lignes de la carte
     */
    public int getNbLignes() {
        return this.nbLignes;
    }

    /**
     * Retourne le nombre de colonnes de la carte.
     * @return nombre de colonnes de la carte
     */
    public int getNbColonnes() {
        return this.nbColonnes;
    }

    /**
     * Retourne les cases de la carte.
     * @return matrice de cases de la carte
     */
    public Case[][] getCases() {
        return this.cases;
    }

    /**
     * Retourne la case à la ligne et colonne passées en paramètres.
     * @param ligne ligne de la case
     * @param colonne colonne de la case
     * @return case à la ligne et colonne passées en paramètres
     */
    public Case getCase(int ligne, int colonne) {
        return this.cases[ligne][colonne];
    }

    /**
     * Display the map.
     */
    public void print() {
        System.out.println("Nombre de lignes : " + this.nbLignes + "\n" + "Nombre de colonnes : " + this.nbColonnes
                + "\n" + "Taille des cases : " + this.tailleCases);
        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                this.cases[i][j].print();
            }
            System.out.println();
        }
    }

    /**
     * Retourne la représentation textuelle de la carte.
     */
    public void printBoardStyle() {
        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                System.out.print("[" + this.cases[i][j].getNature().toString().charAt(0) + "]");
            }
            System.out.println();
        }
    }

    // public Carte(String filename) {

    // try (Scanner scanner = new Scanner(new File("../cartes/" + filename))) {

    // // tant que la ligne courante du fichier ne commmence pas par un int on next
    // while (!scanner.hasNextInt()) {
    // scanner.nextLine();
    // }
    // this.nbLignes = scanner.nextInt();
    // this.nbColonnes = scanner.nextInt();
    // this.tailleCases = scanner.nextInt();

    // this.cases = new ArrayList<ArrayList<Case>>();

    // // boucle pour ignorer certaines lignes
    // String line = scanner.nextLine();
    // while (!line.equals("# l0")) {
    // line = scanner.nextLine();
    // }
    // line = scanner.nextLine();
    // for (int i = 0; i < this.nbLignes; i++) {
    // this.cases.add(new ArrayList<Case>());
    // for (int j = 0; j < this.nbColonnes; j++) {
    // switch (line.charAt(0)) {
    // case 'E':
    // this.cases.get(i).add(new Case(i, j, Case.NatureTerrain.EAU));
    // break;
    // case 'F':
    // this.cases.get(i).add(new Case(i, j, Case.NatureTerrain.FORET));
    // break;
    // case 'R':
    // this.cases.get(i).add(new Case(i, j, Case.NatureTerrain.ROCHE));
    // break;
    // case 'T':
    // this.cases.get(i).add(new Case(i, j, Case.NatureTerrain.TERRAIN_LIBRE));
    // break;
    // case 'H':
    // this.cases.get(i).add(new Case(i, j, Case.NatureTerrain.HABITAT));
    // break;
    // default:
    // break;
    // }
    // // passe à la prochaine ligne à chaque lecture de case
    // line = scanner.nextLine();
    // }
    // // saute une ligne pour passer celle commençant par #
    // line = scanner.nextLine();
    // }
    // // scanner.close();
    // // ajouter le code pour lire les pompiers et les incendies quand les
    // // classes auront été faites

    // } catch (FileNotFoundException e) {
    // System.out.println("File not found");
    // }
    // }

}
