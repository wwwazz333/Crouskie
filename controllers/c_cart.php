<?php 
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');
require_once(PATH_MODELS . 'SizeDAO.php');
require_once(PATH_ENTITY . 'User.php');

// Si l'utilisateur est connecté
// Récupération du panier de l'utilisateur
if($isLogged) {
    $cartDAO = new CartDAO(DEBUG);
    $userId = $user->getIdUser();
    $cartPhp = $cartDAO->getCartByCustomerId($userId); // Récupération du panier de l'utilisateur -> type : objet php

    if (isset($_GET['vider'])){
        $alert = showAlert(1, VIDER_PANIER,PANIER_BIEN_VIDE);
    }

    // Si le panier est vide
    if($cartPhp == null) {
        $isCartEmpty = true;
    // Sinon
    } else if ($cartPhp != null || $isLogged == true){
        $isCartEmpty = false;
        $cart = $cartDAO->resultToCartArray($cartPhp); // Conversion type objet php en type objet Product

        $productDAO = new ProductDAO(DEBUG);
        $sizeDAO = new SizeDAO(DEBUG);

        // Informations destinées à la vue
        $infosProdsCart = [];
        $montantTotal = 0;

        // On récupère le nom des produits du panier à partir de leur id 
        foreach ($cart as $productCart) { 
            $id = $productCart->getIdProd(); 
            $productPhp = $productDAO->getProductByID($id); // type objet php
            $product = $productDAO->resultToProductsArray(["productPhp" => $productPhp]); // type objet Product
            
            $montantTotal = $montantTotal + $product[0]->getPrice() * $productCart->getQuantityCart();

            // On va chercher le nom de la taille du produit avec l'idsize
            $sizePhp = $sizeDAO->getFullSizeBySizeId($productCart->getSizeCart());
            $size = $sizeDAO->resultToSizesArray($sizePhp);
            
            // Enregistrement des informations dans un tableau traité par la vue
            $infosProdsCart[$id] = [
                "nameprod" => $product[0]->getName(),
                "color" => $productCart->getColorCart(),
                "size" => $size[0]->getName(),
                "quantitycart" => $productCart->getQuantityCart(),
                "priceprod" => $product[0]->getPrice(),
                "pricetotal" => $product[0]->getPrice() * $productCart->getQuantityCart()
            ];   
        }

        // Vider le panier
        function viderPanier($cartDAO,$userId) {
            $cartDAO->deleteCart($userId);
            $isCartEmpty = true;
            return true;
        } 

        if(isset($_POST['action'])){
            switch ($_POST['action']){
                case 'vider':
                    viderPanier($cartDAO, $userId);
                    header('Location: index.php?page=cart&vider=1');
                    break;
                case 'valider':
                    $alert = showAlert(1, PASSER_COMMANDE,PANIER_BIEN_VALIDE);
                    break;
            }

        }
    }
    
} else {
    // Si l'utilisateur n'est pas connecté
    $isCartEmpty = true;
}

// Test pour vider le panier

if(isset($_POST['action'])){
    $commande = $_POST['action'];
}
// $isCartEmpty = viderPanier($cartDAO,$userId);

require_once(PATH_VIEWS . $page . '.php');
