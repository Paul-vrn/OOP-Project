# projet-OOP

Réalisé par Paul Vernin, Marc Félix-Henry et Paul Bonmariage.

Ensimag 2A POO - TP 2022/23
============================

- src: contient les classes fournies par les enseignants
  -> LecteurDonnees.java         : lit tous les elements d'un fichier de description de donnees (cases, incendies et robots) et les affiche.
                                   A vous de MODIFIER cette classe (ou en ecrire une nouvelle) pour creer les objets correspondants à vos propres classes
  -> test.TestLecteurDonnees.java     : lit un fichier de donnees et affiche son contenu
  -> test.TestInvader                 : cree un simulateur "mini Invaders" dans une fenetre graphique

- cartes: quelques exemples de fichiers de donnees

- bin/gui.jar: archive Java contenant les classes de l'interface graphique. Voir un exemple d'utilisation dans test.TestInvader.java

- doc: la documentation (API) des classes de l'interface graphique contenues dans gui.jar. Point d'entrée: index.html

- Makefile: quelques explications sur la compilation en ligne, notamment la notion de classpath et l'utilisation de gui.jar

## Travailler sur le projet

Installer SonnarLint pour faire du jolie code.  
Pour chaque nouvelle classe :
- Créer un fichier test correspondant dans le dossier test
- Faire la javadoc de la classe