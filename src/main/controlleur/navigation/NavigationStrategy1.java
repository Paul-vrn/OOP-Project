package main.controlleur.navigation;

import main.controlleur.DonneesSimulation;
import main.modele.Incendie;
import main.modele.evenement.Evenement;
import main.modele.evenement.MoveEvent;
import main.modele.robot.Robot;
import main.modele.Carte;

import java.util.*;

/**
 * Stratégie de navigation 1
 */
public class NavigationStrategy1 implements NavigationStrategy {

    /**
     * calcule le plus court chemin entre un robot et un incendie
     * @param robot le robot
     * @param incendie l'incendie
     * @param donneesSimulation les données de la simulation
     * @return le chemin
     */
    @Override
    public Chemin plusCourtChemin(Robot robot, Incendie incendie, DonneesSimulation donneesSimulation) {
        Carte carte = donneesSimulation.getCarte();
        Node[][] nodeMap = new Node[carte.getNbLignes()][carte.getNbColonnes()];

        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closedNodes = new ArrayList<>();

        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                nodeMap[i][j] = new Node(carte.getCase(i, j));
            }
        }
        openNodes.add(nodeMap[robot.getPosition().getLigne()][robot.getPosition().getColonne()]);

        Node currentNode = nodeMap[robot.getPosition().getLigne()][robot.getPosition().getColonne()];
        Node minNode = currentNode;
        currentNode.setgScore(0);
        currentNode.setfScore(
                (double) currentNode.hCalculator(incendie) * carte.getTailleCases() / (robot.getBaseVitesse() / 3.6));// On met le noeud de départ à 0
        while (!openNodes.isEmpty()) {
            //System.out.println("openNodes.size() = " + openNodes.size() + " closedNodes.size() = " + closedNodes.size());
            // currentNode becomes the node in openNodes with the smalles fScore
            minNode = openNodes.get(0);
            for (Node node : openNodes) {
                //System.out.println(node.getPosition() + "   " + node.getfScore());
                if (node.getfScore() < minNode.getfScore()) {
                    minNode = node;
                }
            }
            //System.out.println("MinNode : " + minNode.getPosition());
            // currentNode = openNodes.get(0);
            // for (int i = 0; i < openNodes.size(); i++) {
            // if (currentNode.getfScore() < openNodes.get(i).getfScore()) {
            // System.out.println(openNodes.get(i).getPosition());
            // currentNode = openNodes.get(i);
            // }
            // }
            currentNode = minNode;
            // System.out.println("currentNodeZAZA = " + currentNode.getgScore());
            if (currentNode.getPosition() == incendie.getPosition()) {
                // crée une liste node pour le chemin le plus court
                List<Node> nodeChemin = new LinkedList<>();
                Node tempNode = nodeMap[incendie.getPosition().getLigne()][incendie.getPosition().getColonne()];
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

                return new Chemin(robot, incendie,
                        (int) nodeMap[incendie.getPosition().getLigne()][incendie.getPosition().getColonne()]
                                .getgScore(),
                        events);
            }

            double edgeTime = carte.getTailleCases() / (robot.getVitesse(currentNode.getPosition()) / 3.6);
            // System.out.println(edgeTime);
            if (currentNode.getPosition().getColonne() + 1 < carte.getNbColonnes() && robot
                    .canRobotBeOnCase(
                            nodeMap[currentNode.getPosition().getLigne()][currentNode.getPosition().getColonne() + 1]
                                    .getPosition())) {
                Node voisinDroite = nodeMap[currentNode.getPosition().getLigne()][currentNode.getPosition().getColonne()
                        + 1];

                if (!closedNodes.contains(voisinDroite) && !openNodes.contains(voisinDroite)) {
                    voisinDroite.setParent(currentNode);
                    openNodes.add(voisinDroite);
                    // System.out.println("VoisinDroite gscore avant changement = " +
                    // voisinDroite.getgScore());
                    // System.out.println("Current gscore = " + currentNode.getgScore());
                    voisinDroite.setgScore(currentNode.getgScore() + edgeTime);
                    // System.out.println("VoisinDroite gscore apres changement = " +
                    // voisinDroite.getgScore());
                    voisinDroite.setfScore(voisinDroite.getgScore()
                            + (((double) voisinDroite.hCalculator(incendie) * carte.getTailleCases())
                                    / (robot.getBaseVitesse() / 3.6)));
                } else {
                    if (voisinDroite.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinDroite.setParent(currentNode);
                        voisinDroite.setgScore(currentNode.getgScore() + edgeTime);
                        voisinDroite.setfScore(voisinDroite.getgScore()
                                + (((double) voisinDroite.hCalculator(incendie) * carte.getTailleCases())
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
                //System.out.println("VoisinGauche" + voisinGauche.getPosition());
                if (!closedNodes.contains(voisinGauche) && !openNodes.contains(voisinGauche)) {
                    openNodes.add(voisinGauche);
                    voisinGauche.setParent(currentNode);
                    voisinGauche.setgScore(currentNode.getgScore() + edgeTime);
                    voisinGauche.setfScore(voisinGauche.getgScore()
                            + (((double) voisinGauche.hCalculator(incendie) * carte.getTailleCases())
                            / (robot.getBaseVitesse() / 3.6)));

                } else {
                    if (voisinGauche.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinGauche.setParent(currentNode);
                        voisinGauche.setgScore(currentNode.getgScore() + edgeTime);
                        voisinGauche.setfScore(voisinGauche.getgScore()
                                + (((double) voisinGauche.hCalculator(incendie) * carte.getTailleCases())
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
                            + (((double) voisinHaut.hCalculator(incendie) * carte.getTailleCases())
                            / (robot.getBaseVitesse() / 3.6)));

                } else {
                    if (voisinHaut.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinHaut.setParent(currentNode);
                        voisinHaut.setgScore(currentNode.getgScore() + edgeTime);
                        voisinHaut.setfScore(voisinHaut.getgScore()
                                + (((double) voisinHaut.hCalculator(incendie) * carte.getTailleCases())
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
                            voisinBas.getgScore() + (((double) voisinBas.hCalculator(incendie) * carte.getTailleCases())
                                    / (robot.getBaseVitesse() / 3.6)));

                } else {
                    if (voisinBas.getgScore() > currentNode.getgScore() + edgeTime) {
                        voisinBas.setParent(currentNode);
                        voisinBas.setgScore(currentNode.getgScore() + edgeTime);
                        voisinBas.setfScore(voisinBas.getgScore()
                                + (((double) voisinBas.hCalculator(incendie) * carte.getTailleCases())
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
     * Va reremplir les chemins avec les robots non assignés et les incendies non
     * éteints
     *
     * @param donneesSimulation les données de la simulation
     */
    public void fillChemins(DonneesSimulation donneesSimulation) {
        for (Incendie incendie : donneesSimulation.getIncendies()) {
            if (!incendie.isEteint() && !incendie.isHandled()) {
                for (Robot robot : donneesSimulation.getRobots()) {
                    if (!robot.isEmpty() && !robot.isOccupied()) {
                        Chemin chemin = plusCourtChemin(robot, incendie, donneesSimulation);
                        if (chemin != null) {
                            ChefRobot.getInstance().chemins.add(chemin);
                        } else {
                            System.out.println("Le chemin entre le robot " + robot.getName() +" ("+ robot.getPosition().getLigne()
                                    + ":" + robot.getPosition().getColonne()+") et l'incendie ("
                                    + incendie.getPosition().getLigne() + ":" + incendie.getPosition().getColonne() + ") n'a pas pu être trouvé");
                        }
                    }
                }
            }
        }
    }

    /**
     * Une fois qu'on a viré les chemins obsolètes et ceux relient des robots vides
     * ou des incendies éteints
     * on redistribue les chemins restants aux robots non occupés et aux incendies
     * non éteints
     */
    public void distribution() {
        while (ChefRobot.getInstance().chemins.peek() != null){
            Chemin chemin = ChefRobot.getInstance().chemins.poll();
            assert chemin != null;
            System.out.println(chemin.getDuration() + " | " + chemin.getRobot().getName() +
                    " | incendie=" + chemin.getIncendie().getPosition().getLigne() +
                    ":" + chemin.getIncendie().getPosition().getColonne()
            );
            if (!chemin.getRobot().isOccupied() && !chemin.getIncendie().isHandled()) {
                chemin.getRobot().addEvenements(chemin.getEvents());
                chemin.getRobot().setOccupied(true);
                chemin.getIncendie().setHandle(true);
            }
        }
    }

}
