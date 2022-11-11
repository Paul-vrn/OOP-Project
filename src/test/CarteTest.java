package test;

import main.modele.Carte;
import main.simulateur.DonneesSimulation;
import main.simulateur.io.LecteurDonnees;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {
    public Carte carte;
    @BeforeEach
    void setUp() throws DataFormatException, FileNotFoundException {
        DonneesSimulation data = LecteurDonnees.getData("cartes/carteSujet.map");
        carte = data.getCarte();
    }

    @Test
    void getTailleCases() {
    }

    @Test
    void getNbLignes() {
    }

    @Test
    void getNbColonnes() {
    }

    @Test
    void getCases() {
    }

    @Test
    void getCase() {
    }

    @Test
    void print() {
    }

    @Test
    void print_board_style() {
    }
}