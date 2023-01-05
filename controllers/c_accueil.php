<?php
require_once(PATH_MODELS . 'CollectionDAO.php');

$DAO = new CollectionDAO(DEBUG);
$lastCollection = $DAO->getLastCollection(); //for the DAO
$lastCollection = new Collection( //create an object from the DAO
    $lastCollection['namecollection'],
    $lastCollection['idcollection'],
    $lastCollection['pathpicture']
);

// Vérification de tentative de création de compte
if (isset($_GET['acc'])) {
    if ($_GET['acc']) {
        $alert = showAlert(1,"Succès","Votre compte a bien été créé !");
    }
}

// Vérification si la personne a réussi à se connecter
if (isset($_GET['log'])) {
    $alert = showAlert(1,"Connexion","Vous êtes maintenant connecté !");
}

// appel à la vue
require_once(PATH_VIEWS . $page . '.php');