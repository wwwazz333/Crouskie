<?php

// Si l'utilisateur n'est pas connecté
if (!$isLogged) {
    header('Location: index.php?page=portal');
    exit();

} else {
    require_once(PATH_MODELS . 'CartDAO.php');
    $cartDAO = new CartDAO(DEBUG);
    $userId = $user->getIdUser();
    $cartPhp = $cartDAO->getCartByCustomerId($userId);

    // Si son panier est vide
    if ($cartPhp == null) {
        header('Location: index.php?page=cart');
        exit();
    }
    // Si l'utilisateur est connecté
    else {
        if (isset($_POST['valider'])){
            // Enregistrer modifications quantité produit du panier
            foreach ($infosProdsCart as $ligne) {
                $q = $_POST['hid-quantity'] ; // Récupérer quantité dans variable $q
                if ($q == 0) {
                    // Suppression du panier
                    $modif = $cartDAO->deleteProductFromCart($userId, $ligne['idproduct'], $ligne['color'], $ligne['idsize']);
                } else {
                    // Modification de la quantité
                    $modif = $cartDAO->setQuantityProductFromCart($q,$userId, $ligne['idproduct'], $ligne['color'], $ligne['idsize']);
                }
            }

            // Création de la commande
            $resultOrder = $commandeDAO->addCommande($userId);
            // On récupère l'idOrder pour plus tard 
            $idOrder = $commandeDAO->getNumLastCommande($userId)[0][0];
            $resultProducts = true;
            foreach ($cart as $productCart) {
                $color = $productCart->getColorCart();
                $size = $productCart->getSizeCart();
                $idProd = $productCart->getIdProd();
                $quantity = $productCart->getQuantityCart();
                $idCustomer = $productCart->getCustomerId();

                $result = $productBoughtDAO->buyProduct($color, $idProd, $size, $idOrder, $quantity, $idCustomer);
                if($result == 2){
                    $alert = showAlert(3, QUANTITE_INSUFFISANTE, PAS_ASSEZ_DE_STOCK);
                    break;
                }
                // Si un seul produit ne peux pas être acheter alors false
                $resultProducts = $resultProducts && $result;
            }

            //alertes pour savoir si la fonction marche
            if($resultProducts && $resultOrder){
                header('Location: index.php?page=cart&valider=1');
            }
            
            else if($resultProducts && !$resultOrder){
                $alert = showAlert(3, COMMANDE, "Commande");
            }
            else if(!$resultProducts && $resultOrder){
                $alert = showAlert(3, COMMANDE, "Result");
            }
            else{
                $alert = showAlert(3, COMMANDE, "$");
            }
        }
    }
}

require_once(PATH_VIEWS . 'valider_commande.php');