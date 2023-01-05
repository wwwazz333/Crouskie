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
            if ($isLogged) {
                
            }else{
                header('Location: index.php?page=portal');
            }
            $cart = new Cart(
                $user->getIdUser(),
                $product->getId(),
                1,
                $_POST['color'],
                $_POST['size']
            );
            print_r($DAO->addCart($cart));
        }

    }else{
        header('Location: index.php?page=404');
        exit();
    }
}

require_once(PATH_VIEWS . $page . '.php');