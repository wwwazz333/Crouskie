<?php 
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');

// Récupération du panier de l'utilisateur
$cartDAO = new CartDAO(DEBUG);
$userId = $user->getIdUser();
$cart = $cartDAO->getCartByCustomerId($userId);
$productDAO = new ProductDAO(DEBUG);

// Informations destinées à la vue
$infosProdsCart = [];
$montantTotal = 0;

// On récupère le nom des produits du panier à partir de leur id 
// Pour ensuite pouvoir les afficher dans la vue
foreach ($cart as $productCart) { 
    $id = $productCart['IDPROD'];
    $product = $productDAO->getProductByID($id);
    
    $montantTotal = $montantTotal + $product['PRICEPROD'] * $productCart['QUANTITYCART'];

    // Il faudra rajouter la taille et la couleur du vêtement commandé dans le panier
    // $nomProduct = $product->getName();   // Ca fait une erreur j'ai pas compris pourquoi
    $infosProdsCart[$id] = [
        "NAMEPROD" => $product['NAMEPROD'],
        "QUANTITYCART" => $productCart['QUANTITYCART'],
        "PRICEPROD" => $product['PRICEPROD'],
        "PRICETOTAL" => $product['PRICEPROD'] * $productCart['QUANTITYCART']
    ];
}

require_once(PATH_VIEWS . $page . '.php');
