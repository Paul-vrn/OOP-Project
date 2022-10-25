public class Incendie {
    private Case Position;
    private int EauNecessaire;

    public Incendie(Case Position, int EauNecessaire) {
        this.Position = Position;
        this.EauNecessaire = EauNecessaire;
    }

    public Case getPosition() {
        return this.Position;
    }

    public int getEauNecessaire() {
        return this.EauNecessaire;
    }
}
