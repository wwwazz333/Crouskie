<?php 
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');

// Récupération du panier de l'utilisateur
$cartDAO = new CartDAO(DEBUG);
$userId = $user->getIdUser();
$cart = $cartDAO->getCartByCustomerId($userId);
// print_r($cartDAO->getCartByCustomerId(3));

$productDAO = new ProductDAO(DEBUG);

// Informations destinées à la vue
$infosProdsCart = [];
$montantTotal = 0;

// On récupère le nom des produits du panier à partir de leur id 
// Pour ensuite pouvoir les afficher dans la vue
foreach ($cart as $productCart) { 
    $id = $productCart['IDPROD'];
    $product = $productDAO->getProductByID($id);
    // print_r($product);
    // $nomProduct = $product->getName();   // Ca fait une erreur j'ai pas compris pourquoi
    $nomProduct = $product['NAMEPROD'];

    $infosProdsCart[$id] = [$product['NAMEPROD'], $productCart['QUANTITYCART'], $product['PRICEPROD']];
    $montantTotal += $product['PRICEPROD'];
}

require_once(PATH_VIEWS . $page . '.php');
