# projet-OOP

Réalisé par Paul Vernin, Marc Félix-Henry et Paul Bonmariage.

Ensimag 2A POO - TP 2022/23
============================

## Modèle MVC
On s'est inspiré du très connu modèle MVC (Model View Controller) pour structurer notre projet. Le modèle MVC est un modèle de conception logiciel qui sépare les données d'une application, la logique métier et la présentation. Il est composé de trois éléments : le modèle, la vue et le contrôleur.\
Dans notre cas la partie vue était déjà réalisé en grande partie à l'aide de la librairie GUI fournie par les enseignants.\
La partie modèle est représenté par l'ensemble des classes du package model.\
La partie contrôleur est représenté par l'ensemble des classes du package controller.

## Linting
Nous avons utilisé le linter SonarLint pour vérifier la qualité du code.\
Cela nous a permi de structurer notre code pour suivre les conventions de programmation en Java comme par exemple le nommage des variables, des fonctions, des classes, etc.\
Et surotut faire en sorte que chaque membre de l'équipe suive les mêmes conventions de programmation pour avoir un code plus lisible et plus facile à maintenir.

## Testing
Pour tester la partie modèle de l'application nous avons utilisé le framework JUnit afin de réaliser principalement des tests unitaires.

## Gestion plus court chemin
Nous avons utilisé l'algorithme de Dijkstra pour calculer le plus court chemin entre deux points.\
Nous avons utilisé une implémentation de la classe PriorityQueue fournie par la librairie Java Collections Framework.\
En s'inspirant du design pattern Strategy nous avons implémenté deux stratégies pour calculer le plus court chemin :\
(En apprendre plus sur le design pattern Strategy : https://refactoring.guru/design-patterns/strategy)\

Intellij IDEA
Logger
