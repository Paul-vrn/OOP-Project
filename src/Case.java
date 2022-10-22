public class Case {

    // ENUMERATION DES DIFFERENTES NATURES DE TERRAIN
    enum NatureTerrain {
        EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT;
    }

    // ATTRIBUTS DES CASES
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    public Case(int ligne, int colonne, NatureTerrain nature) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    // DEFAULT CONSTRUCTOR
    public Case() {
    }

    public int getLigne() {
        return this.ligne;
    }

    public int getColonne() {
        return this.colonne;
    }

    public NatureTerrain getNature() {
        return this.nature;
    }

    public String toString() {
        return "Case (" + this.ligne + ", " + this.colonne + ") : " + this.nature;
    }

    public void print() {
        System.out.println(this.toString());
    }
}