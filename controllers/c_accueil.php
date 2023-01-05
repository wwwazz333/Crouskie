<?php

// Récupération de la dernière collection sortie pour l'afficher dans la page d'accueil

require_once(PATH_MODELS . 'CollectionDAO.php');
$DAO = new CollectionDAO(DEBUG);
$lastCollection = $DAO->getLastCollection(); //for the DAO
$lastCollection = new Collection( //create an object from the DAO
    $lastCollection['namecollection'],
    $lastCollection['idcollection'],
    $lastCollection['pathpicture']
);

// Gestion des alertes potentielles

// Vérification de tentative de création de compte
if (isset($_GET['acc'])) {
    if ($_GET['acc']) {
        $alert = showAlert(1,"Succès","Votre compte a bien été créé !");
    }
}

// Vérification si la personne a réussi à se connecter / déconnecté
if (isset($_GET['log'])) {
    if ($_GET['log'] == 1) {
        $alert = showAlert(1,"Connexion","Vous êtes maintenant connecté !");
    }else{
        $alert = showAlert(1,"Déconnexion","Vous êtes maintenant déconnecté !");
    }
}

// appel à la vue
require_once(PATH_VIEWS . $page . '.php');