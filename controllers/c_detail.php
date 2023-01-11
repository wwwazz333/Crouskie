<?php
require_once(PATH_MODELS . 'ProductDAO.php');

$DAO = new ProductDAO(DEBUG);

if (isset($_GET['id'])) {
    // Récupération des informations du produit
    $product = $DAO->getProductByID($_GET['id']);
    if ($product != false) {
        require_once(PATH_MODELS . 'SizeDAO.php');
        require_once(PATH_MODELS . 'ColorDAO.php');
        
        // Convertion en Objet PHP
        $product = $DAO->resultToProduct($product);

        // Récupération des tailles existantes
        $DAO = new SizeDAO(DEBUG);
        $sizes = $DAO->getSizesByProductID($product->getId());
        $sizes = $DAO->resultToSizesArray($sizes);

        // Récupération des couleurs existantes;
        $DAO = new ColorDAO(DEBUG);
        $colors = $DAO->getColorsByProductID($product->getId());
        $colors = $DAO->resultToColorsArray($colors);

        // Ajout au panier 
        if (isset($_POST['color']) && isset($_POST['size'])) {
            require_once(PATH_ENTITY . 'Cart.php');
            require_once(PATH_MODELS . 'CartDAO.php');
            
            $DAO = new CartDAO(DEBUG);
            if ($isLogged == false) {
                header('Location: index.php?page=portal&log');
                exit();
            }
            $cart = new Cart(
                $user->getIdUser(),
                $product->getId(),
                1,
                $_POST['color'],
                $_POST['size']
            );
            
            // TODO : Ajouter le support de la langue
            if ($DAO->addCart($cart)) {
                $alert = showAlert(1,SUCCESS,AJOUT_PANIER_REUSSI);
            }else{
                $alert = showAlert(3,ERROR,AJOUT_PANIER_FAIL);
            }
        }
    }else{
        header('Location: index.php?page=404');
        exit();
    }
}

require_once(PATH_VIEWS . $page . '.php');