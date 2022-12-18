<?php 
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');

// Si l'utilisateur est connecté
// Récupération du panier de l'utilisateur
if($isLogged) {
    $cartDAO = new CartDAO(DEBUG);
    $userId = $user->getIdUser();
    $cart = $cartDAO->getCartByCustomerId($userId);


    // Si le panier est vide
    if($cart == null) {
        $isCartEmpty = true;
    // Sinon
    } else if ($cart != null || $isLogged == true){
        $isCartEmpty = false;

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
                "COLOR" => $productCart['COLOR'],
                "SIZE" => $productCart['SIZE'],
                "QUANTITYCART" => $productCart['QUANTITYCART'],
                "PRICEPROD" => $product['PRICEPROD'],
                "PRICETOTAL" => $product['PRICEPROD'] * $productCart['QUANTITYCART']
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

