<img src="https://cdn.discordapp.com/attachments/671746450861981768/1065564364230365225/logo.png" alt="Logo Crouskie" style="zoom:25%; float:left;" />

[TOC]

# Récupérer le code source

Vous devez d'abord récupérer le code source.

Pour cela, vous devez cloner le dépôt disponible à l'adresse `git@forge.univ-lyon1.fr:p2100126/crouskie-sa.git` ou `https://forge.univ-lyon1.fr/p2100126/crouskie-sa.git`.



# Organisation du projet

## BD

Dans le dossier `bd`, il y a 2 fichiers:

- `crouskie.sql` : ll contient toute la structure de la base de données que vous pouvez importer sur `phpmyadmin`. 
- `contrainte.sql` : Il contient certaines contraintes sur des colonnes (`tag`, `color`, `cloth_size`). Ces contraintes ne sont pas exportées / importées par défaut par `phpmyadmin`. Il faut donc copier-coller le contenu de ce fichier dans l'onglet “SQL” de `phpmyadmin`. Ces contraintes sont nécessaires au bon fonctionnement du logiciel de back-office.

## Les Librairies

Dans le dossier `java-lib`.

Le logiciel de back-office utilise certaines librairies, pour :

- Communiquer avec la base de données (`mysql-connector`)
- Communiquer avec le serveur (`httpclient`)
- Formater des données (`gson`)
- Faire des logs (`log4j`)
- Un meilleur affichage (`flatlaf`)

## Le Projet

Ce projet est un projet `Apache NetBeans`. Le dossier `CrouskieBackOffice` est celui qui contient tous le projet `Apache NetBeans`. 



# Installation

1. Cloner le dépôt.
2. Ouvrir `Apache NetBeans` (version utilisé pour le développement : 16). 
3. Ouvrir le projet (`File > Open Project`).
4. Dans l’onglet `Projects`,  dérouler le projet (`CrouskieBackOffice`).
5. Il y a 2 sous-dossiers dans le projet :
   - Source Packages (le code source)
   - Libraries (les librairies)

6. Il est important d'importer les librairies correctement, pour cela, faire un clic droit sur `Libraries` puis clic gauche sur `Add JAR/Folder`.
7. Naviguer jusqu’au dossier `java-lib`.
8. Sélectionner tous son contenue.
9. Puis Validé (bouton `Ouvrir`)

