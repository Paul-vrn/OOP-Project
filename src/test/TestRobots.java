package test;

import main.robot.*;
import main.Case;
import main.NatureTerrain;

public class TestRobots {
    public static void main(String[] args) {
        // array of three robots
        Robot[] Robots = new Robot[3];
        Case case1 = new Case(1, 1, NatureTerrain.EAU);
        Robot drone1 = new Drone(case1);
        Robots[0] = drone1;
        Robots[0].print();

        Case case2 = new Case(2, 2, NatureTerrain.FORET);
        Robot chenilles2 = new Chenilles(case2);
        Robots[1] = chenilles2;
        chenilles2.setVitesse(60);
        Robots[1].print();

    }
}
