<?php
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'StockDAO.php');
require_once(PATH_MODELS . 'CommandeDAO.php');
require_once(PATH_MODELS . 'ProductBoughtDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');
// Si l'utilisateur n'est pas connecté
if (!$isLogged) {
    header('Location: index.php?page=portal');
    exit();

} else {
    if(isset($_POST['action'])){
        $stockDAO = new StockDAO(DEBUG);
        $cartDAO = new CartDAO(DEBUG);
        $commandeDAO = new CommandeDAO(DEBUG);
        $productDAO = new ProductDAO(DEBUG);
        $productBoughtDAO = new ProductBoughtDAO(DEBUG);
        $userId = $user->getIdUser();
        $cartPhp = $cartDAO->getCartByCustomerId($userId);
        
        // Si son panier est vide
        if ($cartPhp == null) {
            header('Location: index.php?page=cart');
            exit();
        }
        // Si l'utilisateur est connecté
        else {
            // if (isset($_POST['valider'])){
                // Enregistrer modifications quantité produit du panier
                // foreach ($infosProdsCart as $ligne) {
                //     $q = $_POST['hid-quantity'] ; // Récupérer quantité dans variable $q
                //     if ($q == 0) {
                //         // Suppression du panier
                //         $modif = $cartDAO->deleteProductFromCart($userId, $ligne['idproduct'], $ligne['color'], $ligne['idsize']);
                //     } else {
                //         // Modification de la quantité
                //         $modif = $cartDAO->setQuantityProductFromCart($q,$userId, $ligne['idproduct'], $ligne['color'], $ligne['idsize']);
                //     }
                // }
                $cart = $cartDAO->resultToCartArray($cartPhp); // Conversion type objet php en type objet Product
                $enoughQuantity = true;
                $defaultProd;
                
                //On regarde si les produits sont toujours en stock
                foreach($cart as $productCart){
                    if(!$stockDAO->isProductInStockWithQuantity( $productCart->getIdProd(), $productCart->getColorCart(), $productCart->getSizeCart(), $productCart->getQuantityCart())){
                        $enoughQuantity = false;
                        $defaultProd = $productCart;
                    }
                }


                if($enoughQuantity){
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
                        // Si un seul produit ne peux pas être acheter alors false
                        $resultProducts = $resultProducts && $result;
                    }
                }

                if(!$enoughQuantity){
                    $textToDisplay = $product->getName() . ' ' . $defaultProd->getColorCart()  . ' '  . PAS_ASSEZ_DE_STOCK;
                    $product = $productDAO->resultToProduct($productDAO->getProductByID($id));
                    $alert = showAlert(3, QUANTITE_INSUFFISANTE, $textToDisplay);

                }
                else{
                    // Alertes pour savoir si la fonction marche
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
}

require_once(PATH_VIEWS . 'valider_commande.php');