<?php 
require_once(PATH_MODELS . 'CartDAO.php');
require_once(PATH_MODELS . 'CommandeDAO.php');
require_once(PATH_MODELS . 'ProductDAO.php');
require_once(PATH_MODELS . 'ProductBoughtDAO.php');
require_once(PATH_MODELS . 'StockDAO.php');
require_once(PATH_MODELS . 'SizeDAO.php');
require_once(PATH_ENTITY . 'User.php');

// Si l'utilisateur est connecté
// Récupération du panier de l'utilisateur
if($isLogged) {
    $commandeDAO = new CommandeDAO(DEBUG);
    $productBoughtDAO = new ProductBoughtDAO(DEBUG);
    $stockDAO = new StockDAO(DEBUG);
    $cartDAO = new CartDAO(DEBUG);
    $userId = $user->getIdUser();
    $cartPhp = $cartDAO->getCartByCustomerId($userId); // Récupération du panier de l'utilisateur -> type : objet php

    if (isset($_GET['vider'])){
        $alert = showAlert(1, VIDER_PANIER,PANIER_BIEN_VIDE);
    }else if(isset($_GET['valider'])){
        $alert = showAlert(1, PASSER_COMMANDE,PANIER_BIEN_VALIDE);
    }

    // Si le panier est vide
    if($cartPhp == null) {
        $isCartEmpty = true;
    // Sinon
    } else if ($cartPhp != null || $isLogged == true){
        $isCartEmpty = false;
        $cart = $cartDAO->resultToCartArray($cartPhp); // Conversion type objet php en type objet Product
        $compteur = 0;

        $productDAO = new ProductDAO(DEBUG);
        $sizeDAO = new SizeDAO(DEBUG);

        // Informations destinées à la vue
        $infosProdsCart = [];
        $montantTotal = 0;

        // On récupère le nom des produits du panier à partir de leur id 
        foreach ($cart as $productCart) { 
            $id = $productCart->getIdProd(); 
            $product = $productDAO->resultToProduct($productDAO->getProductByID($id)); // type objet Product
            
            $montantTotal = $montantTotal + $product->getPrice() * $productCart->getQuantityCart();

            // On va chercher le nom de la taille du produit avec l'idsize
            $sizePhp = $sizeDAO->getFullSizeBySizeId($productCart->getSizeCart());
            $size = $sizeDAO->resultToSizesArray($sizePhp);
            
            // Enregistrement des informations dans un tableau traité par la vue
            $infosProdsCart[$compteur] = [
                "compteur" => $compteur,
                "idproduct" => $id,
                "nameprod" => $product->getName(),
                "color" => $productCart->getColorCart(),
                "size" => $size[0]->getName(),
                "idsize" => $productCart->getSizeCart(),
                "quantitycart" => $productCart->getQuantityCart(),
                "priceprod" => $product->getPrice(),
                "pricetotal" => $product->getPrice() * $productCart->getQuantityCart()
            ];  
            $compteur ++; 
        }

        // Fonction pour vider le panier
        function viderPanier($cartDAO,$userId) {
            $cartDAO->deleteCart($userId);
            $isCartEmpty = true;
            return true;
        } 

        
        // Modification des quantités du panier
        if(isset($_POST['form-quantity'])){
            // Enregistrer modifications quantité produit du panier
            for($i=0;$i<count($infosProdsCart);$i++) {
                if(isset($_POST["hid-num"]) && $_POST["hid-num"] == $i){
                    $ligne = &$infosProdsCart[$i]; //on utilise les références
                    $quantity = $_POST['hid-quantity'] ; // Récupérer quantité dans variable $quantity
                    if ($quantity == 0) {
                        // Suppression du panier
                        $modif = $cartDAO->deleteProductFromCart($userId, $ligne['idproduct'], $ligne['color'], $ligne['idsize']);
                        unset($infosProdsCart[$i]); // peut del comme sa dans la liste car on break à la fin
                    } else {
                        // Modification de la quantité 
                        $modif = $cartDAO->setQuantityProductFromCart($quantity, $userId, $ligne['idproduct'], $ligne['color'], $ligne['idsize']);
                    }
                    $ligne["quantitycart"] = $quantity; // modifie affichage compter en face du produit
                    $cartCounter = $quantity; // modifie le compteur dans le header (petit sac)
                    break;
                }   
            }

            if(count($infosProdsCart) == 0){//verifie si le panier est vide
                $isCartEmpty = true;
            }
        }


        // Passer commande
        if(isset($_POST['action'])){
            switch ($_POST['action']){

                // On vide le panier
                case 'vider':
                    viderPanier($cartDAO, $userId);
                    header('Location: index.php?page=cart&vider=1');
                    break;

                // On valide la commande
                case 'valider':
                    
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
                    break;
            }

        }

    }
    

// Si l'utilisateur n'est pas connecté
} else {
    $isCartEmpty = true;
}


if(isset($_POST['action'])){
    $commande = $_POST['action'];
}


require_once(PATH_VIEWS . $page . '.php');
