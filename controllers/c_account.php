<?php

/************************ Page informations **************************/

// Informations du compte: info
require_once(PATH_MODELS . 'UtilisateurDAO.php');

// Créer une variable $selectedPage
if (isset($_GET['selected'])) {
    $selectedPage = $_GET['selected'];
} else {
    $selectedPage = "info";
}

/************************ Page commandes **************************/

// Si l'utilisateur n'est pas connecté
if (!$isLogged) {
    header('Location: index.php?page=portal');
    exit();

// Si l'utilisateur est connecté
} else {

    // Alerte validation changement de mot de passe
    // require_once(PATH_CONTROLLERS. 'changepwd.php');
    // if($chgValide) {
    //     $alert = showAlert(1,SUCCESS,CHANGEMENT_MDP_CONFIRMER);
    // }
    // $chgValide = false;

    require_once(PATH_MODELS . 'CommandeDAO.php');
    require_once(PATH_MODELS . 'ProductBoughtDAO.php');
    require_once(PATH_MODELS . 'ProductDAO.php');
    require_once(PATH_MODELS . 'SizeDAO.php');
    
    $userDAO = new UtilisateurDAO(DEBUG);
    if(isset($_POST['email']) && isset($_POST['prenom']) && isset($_POST['nom'])){
        $res = $userDAO->changeUserInfos($user->getIdUser(), $_POST['prenom'], $_POST['nom'], $_POST['email']); 
        if ($res) {
            //TODO pb de save dans la session
            $user = serializeUser( $_POST['prenom'], $_POST['nom'], $_POST['email'],$user->getIdUser());
            $alert = showAlert(1,SUCCESS,CHANGEMENT_INFORMATIONS_CONFIRMER);
        } else {
            $alert = showAlert(3,ERROR,CHANGEMENT_INFORMATIONS_ERREUR);
        }
    }
    

    $commandeDAO = new CommandeDAO(DEBUG);
    $userId = $user->getIdUser();
    $commandesPhp = $commandeDAO->getCommandeByCustomerId($userId); // Récupération des commandes de l'utilisateur -> type : objet php

    // Si l'utilisateur n'a encore passé aucune commande
    if($commandesPhp == null) {
        $isCommandesEmpty = true;

    // Sinon
    } else {
        $isCommandesEmpty = false;
        $commandes = $commandeDAO->resultToCommandesArray($commandesPhp); // Conversion du type objet php en type objet Commande

        // On récupère les infos sur les commandes
        foreach ($commandes as $commande) { 
        
            $id = $commande->getNumOrder();

            // Récupération des information de chaque commande dans un tableau transmis à la vue
            list($date, $heure,) = explode(' ', $commande->getDateOrder()); 
            $listeCommande[$id] = [
                "date" => $date,
                "heure" => $heure,
                "numorder" => $id
            ];
        }

        
        // On récupère les infos sur les produits achetés de chaque commande
        $productBoughtDAO = new ProductBoughtDAO(DEBUG);
        $productDAO = new ProductDAO(DEBUG);
        $sizeDAO = new SizeDAO(DEBUG);

        $productsBoughtsPhp = $productBoughtDAO->getProductBoughtByCustomerId($userId); // type objet php
        $productsBoughts = $productBoughtDAO->resultToProductBoughtsArray($productsBoughtsPhp); // type objet ProductBought
        
        foreach ($productsBoughts as $productsBought) { 
            $id = $productsBought->getIdpp();

            // Récupération du nom de la taille par son idsize
            $sizePhp = $sizeDAO->getFullSizeBySizeId($productsBought->getIdSize());
            $size = $sizeDAO->resultToSizesArray($sizePhp);
            // Récupération du nom du produit et de son prix par son id
            $productPhp = $productDAO->getProductByID($productsBought->getIdProd());
            $product = $productDAO->resultToProductsArray(["productPhp" => $productPhp]);

            // Récupération des information de chaque produit acheté dans un tableau transmis à la vue
            $listeProductBought[$id] = [
                "name" => $product[0]->getName(),
                "order" => $productsBought->getNumOrder(),
                "color" => $productsBought->getNameColor(),
                "size" => $size[0]->getName(),
                "quantity" => $productsBought->getQuantityBought(),
                "price" => $product[0]->getPrice()
            ];
        }
    }
}



/************************ Page légal **************************/


// Si l'utilisateur n'est pas connecté
if (!$isLogged) {
    header('Location: index.php?page=portal');
    exit();

// Si l'utilisateur est connecté
} else {
    if(isset($_POST['delete'])) {
        // On supprime le compte
        $userId = $user->getIdUser();
        $deleting = $userDAO->deleteUser($userId);
        // On supprime la session
        unset($_SESSION['account']);
        // On redirige vers la page d'accueil
        header("Location: index.php?log=0");
        exit();
    }

}


// Vue
require_once(PATH_VIEWS . $page . '.php');
