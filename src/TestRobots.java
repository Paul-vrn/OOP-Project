public class TestRobots {
    public static void main(String[] args) {
        Carte DroneMap = new Carte("carteDrones.map");
        // array of three robots
        Robot[] Robots = new Robot[3];
        Drone drone1 = new Drone(DroneMap.getCase(0, 0));
        Robots[0] = drone1;
        Robots[0].print();

        Chenilles chenilles1 = new Chenilles(DroneMap.getCase(1, 1));
        Robots[1] = chenilles1;
        Robots[1].print();

        chenilles1.setVitesse(60);
        chenilles1.setPosition(DroneMap.getCase(0, 0));
        Robots[1].print();
    }
}
