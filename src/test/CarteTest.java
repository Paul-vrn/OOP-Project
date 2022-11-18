package test;

import main.modele.Carte;
import main.controlleur.io.DonneesSimulation;
import main.controlleur.io.LecteurDonnees;
import main.modele.Case;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

class CarteTest {
    public static Carte carte;
    @BeforeEach
    void setUp() throws DataFormatException, FileNotFoundException {
        DonneesSimulation data = LecteurDonnees.getData("cartes/carteSujet.map");
        carte = data.getCarte();
    }

    @Test
    void getTailleCases() {
        assertEquals(carte.getTailleCases(), 10000);
    }

    @Test
    void getNbLignes() {
        assertEquals(carte.getNbLignes(), 8);
        assertNotEquals(carte.getNbLignes(), 9);
    }

    @Test
    void getNbColonnes() {
        assertEquals(carte.getNbColonnes(), 8);
        assertNotEquals(carte.getNbColonnes(), 9);
    }

    @Test
    void getCases() {
        assertNotNull(carte.getCases());
        assertEquals(carte.getCases().length, 8);
        assertEquals(carte.getCases()[0].length, 8);
    }

    @Test
    void getCase() {
        Case o = carte.getCase(0,0);
        assertNotNull(o);
    }
}