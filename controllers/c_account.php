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
require_once(PATH_MODELS . 'ProductBoughtDAO.php');
$commandeDAO = new CommandeDAO(DEBUG);
$userId = $user->getIdUser();
$commandes = $commandeDAO->getCommandeByCustomerId($userId);


// Si l'utilisateur n'a encore passé aucune commande
if($commandes == null) {
    $isCommandesEmpty = true;
// Sinon
} else {
    $isCommandesEmpty = false;

    // On récupère les infos sur les commandes
    foreach ($commandes as $commande) { 
        $id = $commande['numorder'];
        
        //print_r($cmd);

        // Récupération des information de chaque commande dans un tableau transmis à la vue
        list($date, $heure,) = explode(' ', $commande['dateorder']); 
        $listeCommande[$id] = [
            "date" => $date,
            "heure" => $heure,
            "numorder" => $commande['numorder']
        ];
    }

    
    // On récupère les infos sur les produits achetés de chaque commande
    $productBoughtDAO = new ProductBoughtDAO(DEBUG);
    $productsBoughts = $productBoughtDAO->getProductBoughtByCustomerId($userId);

    foreach ($productsBoughts as $productsBought) { 
        $id = $productsBought['numorder'];

        // Récupération des information de chaque produit acheté dans un tableau transmis à la vue
        $listeCommande[$id] = [
            "date" => $date,
            "heure" => $heure,
            "numorder" => $productsBought['numorder']
        ];
    }


}


// Vue
require_once(PATH_VIEWS . $page . '.php');

//print_r($productsBoughts);