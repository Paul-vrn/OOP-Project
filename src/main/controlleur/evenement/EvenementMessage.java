package main.controlleur.evenement;

public class EvenementMessage extends Evenement {
    private String message;

    public EvenementMessage(int date, String message) {
        super(date);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void execute() {
        System.out.println(this.message);
    }

    @Override
    public String toString() {
        return "EvenementMessage (" + this.getDate() + ") : " + this.message;
    }

}
