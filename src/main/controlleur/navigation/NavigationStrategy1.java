package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Incendie;
import main.modele.NatureTerrain;
import main.modele.evenement.EteindreEvent;
import main.modele.evenement.Evenement;
import main.modele.evenement.MoveEvent;
import main.modele.evenement.RemplirEvent;
import main.modele.robot.Robot;
import main.modele.robot.RobotType;
import main.modele.Carte;
import main.modele.Case;

import java.util.*;

/**
 * Stratégie de navigation 1
 */
public class NavigationStrategy1 implements NavigationStrategy {

    /**
     * Méthode qui permet de calculer le plus court chemin entre un robot est tous les points d'eau dont il peut avoir accès
     * @param robot le robot
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court
     */
    public Chemin plusCourtCheminEau(Robot robot, DonneesSimulation donneesSimulation) {
        Chemin tempCheminEau = null;
        Chemin cheminEau = null;
        boolean isAdjacentToWater;
        // loop through all the cases of the map
        for (int i = 0; i < donneesSimulation.getCarte().getNbLignes(); i++) {
            for (int j = 0; j < donneesSimulation.getCarte().getNbColonnes(); j++) {
                // if the case is a water case and the robot is a drone calculate shortest path
                if (donneesSimulation.getCarte().getCases()[i][j].getNature() == NatureTerrain.EAU && robot.getType() == RobotType.DRONE) {
                    tempCheminEau = plusCourtChemin(robot, donneesSimulation.getCarte().getCases()[i][j],
                            donneesSimulation);
                    if (tempCheminEau != null && (cheminEau == null || tempCheminEau.getDuration() < cheminEau.getDuration())) {
                        cheminEau = tempCheminEau;
                        // print la case d'arrivée de chemin
                    }
                }
                // else if the case is adjacent to an existing water case calculate shortest
                // path
                else if (robot.getType() != RobotType.DRONE) {
                    isAdjacentToWater = false;
                    if (
                            (i - 1 >= 0 && donneesSimulation.getCarte().getCases()[i - 1][j].getNature() == NatureTerrain.EAU)
                            || (i + 1 < donneesSimulation.getCarte().getNbLignes() && donneesSimulation.getCarte().getCases()[i + 1][j].getNature() == NatureTerrain.EAU)
                            || (j - 1 >= 0 && donneesSimulation.getCarte().getCases()[i][j - 1].getNature() == NatureTerrain.EAU)
                            || (j + 1 < donneesSimulation.getCarte().getNbColonnes() && donneesSimulation.getCarte().getCases()[i][j + 1].getNature() == NatureTerrain.EAU)

                    ) {
                        tempCheminEau = plusCourtChemin(robot, donneesSimulation.getCarte().getCases()[i][j], donneesSimulation);
                        isAdjacentToWater = true;
                    }
                    // print isAdjacentToWater
                    if (isAdjacentToWater && tempCheminEau != null && (cheminEau == null || tempCheminEau.getDuration() < cheminEau.getDuration())) {
                        cheminEau = tempCheminEau;
                    }
                }

            }
        }
        assert cheminEau != null;
        cheminEau.getEvents().add(new RemplirEvent(0, robot.getTempsRemplissage(), robot, donneesSimulation.getCarte()));
        return cheminEau;
    }

    /**
     * Méthode qui permet de calculer le plus court chemin entre un robot et un incendie
     * @param robot le robot
     * @param incendie l'incendie
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court
     */
    public Chemin plusCourtCheminIncendie(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation) {
        Chemin cheminIncendie = plusCourtChemin(robot, incendie.getPosition(), donneesSimulation);
        if (cheminIncendie != null) {
            cheminIncendie.setIncendie(incendie);
            cheminIncendie.getEvents().add(new EteindreEvent(0, robot, incendie));
        }
        return cheminIncendie;
    }

    /**
     * Méthode qui permet de calculer le plus court chemin entre un robot et une case
     * @param robot le robot
     * @param caseArrivee la case d'arrivée
     * @param donneesSimulation les données de la simulation
     * @return le chemin le plus court
     */
    @Override
    public Chemin plusCourtChemin(Robot robot, Case caseArrivee, DonneesSimulation donneesSimulation) {
        Carte carte = donneesSimulation.getCarte();
        Node[][] nodeMap = new Node[carte.getNbLignes()][carte.getNbColonnes()];

        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closedNodes = new ArrayList<>();

        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                nodeMap[i][j] = new Node(carte.getCases()[i][j]);
            }
        }
        openNodes.add(nodeMap[robot.getPosition().getLigne()][robot.getPosition().getColonne()]);

        Node currentNode = nodeMap[robot.getPosition().getLigne()][robot.getPosition().getColonne()];
        Node minNode = currentNode;
        currentNode.setgScore(0);
        currentNode.setfScore(
                (double) currentNode.hCalculator(caseArrivee) * carte.getTailleCases()
                        / (robot.getBaseVitesse() / 3.6));// On
        // met
        // le
        // noeud
        // de
        // départ
        // à
        // 0
        while (!openNodes.isEmpty()) {
            // System.out.println("openNodes.size() = " + openNodes.size() + "
            // closedNodes.size() = " + closedNodes.size());
            // currentNode becomes the node in openNodes with the smalles fScore
            minNode = openNodes.get(0);
            for (Node node : openNodes) {
                // System.out.println(node.getPosition() + " " + node.getfScore());
                if (node.getfScore() < minNode.getfScore()) {
                    minNode = node;
                }
            }
            currentNode = minNode;
            // print current node
            if (currentNode.getPosition() == caseArrivee) {

                // crée une liste node pour le chemin le plus court
                List<Node> nodeChemin = new LinkedList<>();
                Node tempNode = nodeMap[caseArrivee.getLigne()][caseArrivee.getColonne()];
                while (tempNode.getPosition() != robot.getPosition()) {
                    nodeChemin.add(0, tempNode);
                    tempNode = tempNode.getParent();
                }
                // crée une liste d'evenement pour le chemin le plus court
                double tempDuration = carte.getTailleCases() / (robot.getVitesse() / 3.6);
                List<Evenement> events = new LinkedList<>();
                for (int i = 0; i < nodeChemin.size(); i++) {
                    events.add(new MoveEvent(0, (int) tempDuration, robot, nodeChemin.get(i).getPosition()));
                    tempDuration = carte.getTailleCases() / (robot.getVitesse(nodeChemin.get(i).getPosition()) / 3.6);
                }
                return new Chemin(robot, caseArrivee,
                        (int) nodeMap[caseArrivee.getLigne()][caseArrivee.getColonne()]
                                .getgScore(),
                        events);
            }

            double edgeTime = carte.getTailleCases() / (robot.getVitesse(currentNode.getPosition()) / 3.6);
            if (currentNode.getPosition().getColonne() + 1 < carte.getNbColonnes() && robot
                    .canRobotBeOnCase(
                            nodeMap[currentNode.getPosition().getLigne()][currentNode.getPosition().getColonne() + 1]
                                    .getPosition())) {
                Node voisinDroite = nodeMap[currentNode.getPosition().getLigne()][currentNode.getPosition().getColonne()
                        + 1];

                if (!closedNodes.contains(voisinDroite) && !openNodes.contains(voisinDroite)) {
                    voisinDroite.setParent(currentNode);
                    openNodes.add(voisinDroite);
                    voisinDroite.setgScore(currentNode.getgScore() + edgeTime);
                    voisinDroite.setfScore(voisinDroite.getgScore()
                            + (((double) voisinDroite.hCalculator(caseArrivee) * carte.getTailleCases())
                            / (robot.getBaseVitesse() / 3.6)));
                } else {
                    if (voisinDroite.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinDroite.setParent(currentNode);
                        voisinDroite.setgScore(currentNode.getgScore() + edgeTime);
                        voisinDroite.setfScore(voisinDroite.getgScore()
                                + (((double) voisinDroite.hCalculator(caseArrivee) * carte.getTailleCases())
                                / (robot.getBaseVitesse() / 3.6)));
                        if (closedNodes.contains(voisinDroite)) {
                            closedNodes.remove(voisinDroite);
                            openNodes.add(voisinDroite);
                        }
                    }
                }
            }
            if (currentNode.getPosition().getColonne() - 1 >= 0 && robot
                    .canRobotBeOnCase(
                            nodeMap[currentNode.getPosition().getLigne()][currentNode.getPosition().getColonne() - 1]
                                    .getPosition())) {
                Node voisinGauche = nodeMap[currentNode.getPosition().getLigne()][currentNode.getPosition().getColonne()
                        - 1];
                if (!closedNodes.contains(voisinGauche) && !openNodes.contains(voisinGauche)) {
                    openNodes.add(voisinGauche);
                    voisinGauche.setParent(currentNode);
                    voisinGauche.setgScore(currentNode.getgScore() + edgeTime);
                    voisinGauche.setfScore(voisinGauche.getgScore()
                            + (((double) voisinGauche.hCalculator(caseArrivee) * carte.getTailleCases())
                            / (robot.getBaseVitesse() / 3.6)));

                } else {
                    if (voisinGauche.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinGauche.setParent(currentNode);
                        voisinGauche.setgScore(currentNode.getgScore() + edgeTime);
                        voisinGauche.setfScore(voisinGauche.getgScore()
                                + (((double) voisinGauche.hCalculator(caseArrivee) * carte.getTailleCases())
                                / (robot.getBaseVitesse() / 3.6)));
                        if (closedNodes.contains(voisinGauche)) {
                            closedNodes.remove(voisinGauche);
                            openNodes.add(voisinGauche);
                        }
                    }
                }
            }
            if (currentNode.getPosition().getLigne() - 1 >= 0 && robot
                    .canRobotBeOnCase(
                            nodeMap[currentNode.getPosition().getLigne() - 1][currentNode.getPosition().getColonne()]
                                    .getPosition())) {
                Node voisinHaut = nodeMap[currentNode.getPosition().getLigne() - 1][currentNode.getPosition()
                        .getColonne()];
                if (!closedNodes.contains(voisinHaut) && !openNodes.contains(voisinHaut)) {
                    openNodes.add(voisinHaut);
                    voisinHaut.setParent(currentNode);
                    voisinHaut.setgScore(currentNode.getgScore() + edgeTime);
                    voisinHaut.setfScore(voisinHaut.getgScore()
                            + (((double) voisinHaut.hCalculator(caseArrivee) * carte.getTailleCases())
                            / (robot.getBaseVitesse() / 3.6)));

                } else {
                    if (voisinHaut.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinHaut.setParent(currentNode);
                        voisinHaut.setgScore(currentNode.getgScore() + edgeTime);
                        voisinHaut.setfScore(voisinHaut.getgScore()
                                + (((double) voisinHaut.hCalculator(caseArrivee) * carte.getTailleCases())
                                / (robot.getBaseVitesse() / 3.6)));
                        if (closedNodes.contains(voisinHaut)) {
                            closedNodes.remove(voisinHaut);
                            openNodes.add(voisinHaut);
                        }
                    }
                }
            }
            if (currentNode.getPosition().getLigne() + 1 < carte.getNbLignes() && robot
                    .canRobotBeOnCase(
                            nodeMap[currentNode.getPosition().getLigne() + 1][currentNode.getPosition().getColonne()]
                                    .getPosition())) {
                Node voisinBas = nodeMap[currentNode.getPosition().getLigne() + 1][currentNode.getPosition()
                        .getColonne()];
                if (!closedNodes.contains(voisinBas) && !openNodes.contains(voisinBas)) {
                    openNodes.add(voisinBas);
                    voisinBas.setParent(currentNode);
                    voisinBas.setgScore(currentNode.getgScore() + edgeTime);
                    voisinBas.setfScore(
                            voisinBas.getgScore()
                                    + (((double) voisinBas.hCalculator(caseArrivee) * carte.getTailleCases())
                                    / (robot.getBaseVitesse() / 3.6)));

                } else {
                    if (voisinBas.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinBas.setParent(currentNode);
                        voisinBas.setgScore(currentNode.getgScore() + edgeTime);
                        voisinBas.setfScore(voisinBas.getgScore()
                                + (((double) voisinBas.hCalculator(caseArrivee) * carte.getTailleCases())
                                / (robot.getBaseVitesse() / 3.6)));
                        if (closedNodes.contains(voisinBas)) {
                            closedNodes.remove(voisinBas);
                            openNodes.add(voisinBas);
                        }
                    }
                }
            }
            openNodes.remove(currentNode);
            closedNodes.add(currentNode);
        }
        // pas de solution
        return null;
    }

    /**
     * Méthode qui permet de re-assigner des chemins aux robots qui sont inoccupés ou qui sont vides
     * dans le cas où il y a encore des incendies à éteindre
     *
     * @param donneesSimulation les données de la simulation
     */
    public void fillChemins(DonneesSimulation donneesSimulation) {
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            if (!incendie.isEteint() && !incendie.isHandled()) {
                for (Robot robot : donneesSimulation.getRobots()) {
                    if (!robot.isOccupied()) {
                        Chemin chemin;
                        if (robot.isEmpty()) {
                            chemin = plusCourtCheminEau(robot, donneesSimulation);
                            if (chemin == null) { // si aucun chemin n'est possible, le robot s'éteint
                                robot.setAllumee(false);
                            } else {
                                robot.setOccupied(true); // on met Occupied() maintenant pour éviter de recalculer le chemin vers l'eau pour chaque incendie
                                robot.addEvenements(chemin.getEvents());
                            }
                        } else {
                            chemin = plusCourtCheminIncendie(robot, incendie, donneesSimulation);
                        }
                        if (chemin != null) {
                            ChefRobot.getInstance().chemins.add(chemin);
                        }
                    }
                }
            }
        }
    }

    /**
     * Méthode qui distribue les chemins aux robots dans l'ordre de la priority queue (en fonction de la durée)
     */
    public void distribution() {
        while (ChefRobot.getInstance().chemins.peek() != null) {
            Chemin chemin = ChefRobot.getInstance().chemins.poll();
            assert chemin != null;
            if (!chemin.getRobot().isOccupied() && !chemin.getIncendie().isHandled()) {
                chemin.getRobot().addEvenements(chemin.getEvents());
                chemin.getRobot().setOccupied(true);
                chemin.getIncendie().setHandle(true);
            }
        }
    }

}
