<?php

// Informations du compte: info
require_once(PATH_MODELS . 'UtilisateurDAO.php');

// Créer une variable $selectedPage
if (isset($_GET['selected'])) {
    $selectedPage = $_GET['selected'];
} else {
    $selectedPage = "info";
}


/************************ Page commandes **************************/

require_once(PATH_MODELS . 'CommandeDAO.php');
require_once(PATH_MODELS . 'ProductBoughtDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');
require_once(PATH_MODELS . 'SizeDAO.php');
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
    $productDAO = new ProductDAO(DEBUG);
    $sizeDAO = new SizeDAO(DEBUG);

    foreach ($productsBoughts as $productsBought) { 
        $id = $productsBought['idpp'];

        // Récupération du nom de la taille par son idsize
        $size = $sizeDAO->getSizeBySizeId($productsBought['idsize']);
        // Récupération du nom du produit et de son prix par son id
        $product = $productDAO->getProductByID($productsBought['idprod']);

        // Récupération des information de chaque produit acheté dans un tableau transmis à la vue
        $listeProductBought[$id] = [
            "name" => $product['nameprod'],
            "order" => $productsBought['numorder'],
            "color" => $productsBought['namecolor'],
            "size" => $size[0]['namesize'],
            "quantity" => $productsBought['quantitybought'],
            "price" => $product['priceprod']
        ];
    }


}


// Vue
require_once(PATH_VIEWS . $page . '.php');
