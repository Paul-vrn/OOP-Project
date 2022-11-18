package main.modele.evenement;

import main.controlleur.navigation.ChefRobot;
import main.modele.Incendie;
import main.modele.robot.Robot;

/**
 * Evenement de l'extinction d'un incendie
 */
public class EteindreEvent extends Evenement {

    private Incendie incendie; // incendie à éteindre

    /**
     * Constructeur de l'évènement d'extinction d'un incendie
     * 
     * @param dateStart date de début de l'évènement
     * @param duration  durée de l'évènement
     * @param robot     robot qui va éteindre l'incendie
     * @param incendie  incendie à éteindre
     */
    public EteindreEvent(int dateStart, int duration, Robot robot, Incendie incendie) {
        super(dateStart, duration, robot);
        this.incendie = incendie;
    }

    public EteindreEvent(int dateStart, Robot robot, Incendie incendie) {
        super(dateStart, 0, robot);
        int volume;
        if (incendie.getEauNecessaire() > robot.getReservoir()) {
            volume = robot.getReservoir();
        } else {
            volume = incendie.getEauNecessaire();
        }
        this.duration = (int) Math.ceil(volume/robot.getDebitVidage());
        this.incendie = incendie;
    }

    /**
     * Execute l'évènement
     */
    public void execute() {
        int n = ChefRobot.getInstance().n;
        if (duration >= n) {
            duration = duration - n;
            incendie.eteindre((int) Math.ceil(n * robot.getDebitVidage()));
            robot.emptyTank((int) Math.ceil(n * robot.getDebitVidage()));
        } else {
            incendie.eteindre((int) Math.ceil(duration * robot.getDebitVidage()));
            robot.emptyTank((int) Math.ceil(duration * robot.getDebitVidage()));
            if (robot.isEmpty()){
                incendie.setHandle(false);
            }
            robot.nextEvent();
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "EteindreEvent (" + incendie.toString() + ") duration=" + duration;
    }
}
