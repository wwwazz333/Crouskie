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

print_r($commandes);

// On récupère le nom des produits de chaque commande à partir de l'id de la commande
// Pour ensuite pouvoir les afficher dans la vue
foreach ($commandes as $commande) { 
    $id = $commande['numorder'];
    $cmd = $commande->getCommandeByID($id);
    
    print_r($cmd);

    // IRécupération des commandes
    $infosProdsCommande[$id] = [
        "dateorder" => $cmd['dateorder'],
        "numorder" => $cmd['numorder']
    ];
}


// Vue
require_once(PATH_VIEWS . $page . '.php');
