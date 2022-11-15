package main;

import main.controlleur.Simulateur;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = new PrintStream("logs/out.txt");
        PrintStream err = new PrintStream("logs/err.txt");
        System.setOut(out);
        System.setErr(err);
        new Simulateur(args);
    }
}

