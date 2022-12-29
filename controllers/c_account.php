<?php

// Informations du compte: info
require_once(PATH_MODELS . 'UtilisateurDAO.php');

// Créer une variable $selectedPage
if (isset($_GET['selected'])) {
    $selectedPage = $_GET['selected'];
} else {
    $selectedPage = "info";
}

// Page commandes
require_once(PATH_MODELS . 'CommandeDAO.php');
$commandeDAO = new CommandeDAO(DEBUG);
$userId = $user->getIdUser();
$commandes = $commandeDAO->getCommandeByCustomerId($userId);


// Si l'utilisateur n'a encore passé aucune commande
if($commandes == null) {
    $isCommandesEmpty = true;
// Sinon
} else {
    $isCommandesEmpty = false;

    // On récupère les infos sur les commandes et
    // On récupère les infors sur les produits àcheté de chaque commande à partir de l'id de la commande
    // Pour ensuite pouvoir les afficher dans la vue
    foreach ($commandes as $commande) { 
        $id = $commande['numorder'];
        //$cmd = $commande->getCommandeByID($id);
        
        //print_r($cmd);

        // Récupération des information de chaque commande
        list($date, $heure,) = explode(' ', $commande['dateorder']); 
        $listeCommande[$id] = [
            "date" => $date,
            "heure" => $heure,
            "numorder" => $commande['numorder']
        ];
    }
}


// Vue
require_once(PATH_VIEWS . $page . '.php');
