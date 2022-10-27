import java.util.ArrayList;

public class DonnesSimulation {

    private Carte carte;
    private ArrayList<Incendie> incendies;
    private ArrayList<Robot> robots;

    public DonnesSimulation(Carte carte, ArrayList<Incendie> incendies, ArrayList<Robot> robots) {
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

}
