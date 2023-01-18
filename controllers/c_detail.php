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
            // Vérification de connexion
            if ($isLogged == false) {
                header('Location: index.php?page=portal&log');
                exit();
            }
            require_once(PATH_ENTITY . 'Cart.php');
            require_once(PATH_MODELS . 'CartDAO.php');
            require_once(PATH_MODELS . 'StockDAO.php');

            $userId = $user->getIdUser();
            $productId = $product->getId();
            $color = $_POST['color'];
            $size = $_POST['size'];

            // Vérification du stock
            $DAO = new StockDAO(DEBUG);
            if ($DAO->isProductInStock($productId, $color, $size)) {
                // Récupération du nombre d'article dans le panier
                $DAO = new CartDAO(DEBUG);
                $qty = $DAO->getQuantityProductFromCart($userId, $productId, $color, $size);
                if ($qty > 0) {
                    // Incrémenter le nombre car il y a déjà un produit identique
                    if (!$DAO->setQuantityProductFromCart($qty + 1, $userId, $productId, $color, $size)) {
                        $alert = showAlert(3,ERROR,AJOUT_PANIER_FAIL);
                    }
                }else{
                    // Ajouter au panier
                    if (!$DAO->addCart($userId, $productId, 1, $size, $color)) {
                        $alert = showAlert(3, ERROR,AJOUT_PANIER_FAIL);
                    }
                }
                // Actualiser le panier
                $cartCounter = $DAO->getCartCountByCustomerId($user->getIdUser());
            }else{
                $alert = showAlert(3,ERROR,PAS_ASSEZ_DE_STOCK);
            }
        }
    }else{
        header('Location: index.php?page=404');
        exit();
    }
}

require_once(PATH_VIEWS . $page . '.php');