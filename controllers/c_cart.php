<?php 
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');
require_once(PATH_MODELS . 'SizeDAO.php');

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
        $sizeDAO = new SizeDAO(DEBUG);

        // Informations destinées à la vue
        $infosProdsCart = [];
        $montantTotal = 0;

        // On récupère le nom des produits du panier à partir de leur id 
        // Pour ensuite pouvoir les afficher dans la vue
        foreach ($cart as $productCart) { 
            $id = $productCart['idprod'];
            $product = $productDAO->getProductByID($id);
            
            $montantTotal = $montantTotal + $product['priceprod'] * $productCart['quantitycart'];

            // Il faudra rajouter la taille et la couleur du vêtement commandé dans le panier
            // $nomProduct = $product->getName();   // Ca fait une erreur j'ai pas compris pourquoi
            $size = $sizeDAO->getSizeBySizeId($productCart['idsize']);
            $infosProdsCart[$id] = [
                "nameprod" => $product['nameprod'],
                "color" => $productCart['color'],
                "size" => $size[0]['namesize'],
                "quantitycart" => $productCart['quantitycart'],
                "priceprod" => $product['priceprod'],
                "pricetotal" => $product['priceprod'] * $productCart['quantitycart']
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
