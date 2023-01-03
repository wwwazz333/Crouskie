<?php 
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');
require_once(PATH_MODELS . 'SizeDAO.php');

// Si l'utilisateur est connecté
// Récupération du panier de l'utilisateur
if($isLogged) {
    $cartDAO = new CartDAO(DEBUG);
    $userId = $user->getIdUser();
    $cartPhp = $cartDAO->getCartByCustomerId($userId); // Récupération du panier de l'utilisateur -> type : objet php
    $cart = $cartDAO->resultToCartArray($cartPhp); // Conversion type objet php en type objet Product

    // Si le panier est vide
    if($cart == null) {
        $isCartEmpty = true;
    // Sinon
    } else if ($cart != null || $isLogged == true){
        $isCartEmpty = false;

        $productDAO = new ProductDAO(DEBUG);
        $sizeDAO = new SizeDAO(DEBUG);

        // Informations destinées à la vue
        $infosProdsCart = [];
        $montantTotal = 0;

        // On récupère le nom des produits du panier à partir de leur id 
        // Pour ensuite pouvoir les afficher dans la vue
        foreach ($cart as $productCart) { 
            $id = $productCart->getIdProd(); 
            $productPhp = $productDAO->getProductByID($id); // Récupération du produit par l'id -> type : objet php
            $product = $productDAO->resultToProductsArray(["productPhp" => $productPhp]); // Conversion type objet php en type objet Product
            
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
    }
    
} else {
    // Si l'utilisateur n'est pas connecté
    $isCartEmpty = true;
}

// Test pour vider le panier
// $isCartEmpty = viderPanier($cartDAO,$userId);

require_once(PATH_VIEWS . $page . '.php');
