<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'ProductBought.php');
require_once(PATH_ENTITY . 'Cart.php');

/**
 * Ce DAO représente l'intéraction avec les produits acheté
 */
class ProductBoughtDAO extends DAO
{
    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Product)
     * @param array $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return array La liste des objets
     */
    public function resultToProductBoughtsArray(array $result){
        $list = [];
        foreach($result as $product_bought){
            array_push($list, 
                new ProductBought(
                    $product_bought['idpp'],
                    $product_bought['namecolor'],
                    $product_bought['idprod'],
                    $product_bought['idsize'],
                    $product_bought['numorder'],
                    $product_bought['quantitybought']
                )
            );
        }
        return $list;
    }
    
    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Product)
     * @param array $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return $product_bought Si le produit a bien été trouvé
     */
    public function getProductBoughtByCustomerId(int $id)
    {
        $product_bought = $this->queryAll("SELECT * FROM cmd c cross join productbought p where c.numorder=p.numorder and idcustomer = ?",
        array($id));
        return $product_bought == false ? false : $product_bought;
    }

    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Product)
     * @param string $nameColor La couleur
     * @param int $idProd l'identifiant du produit
     * @param int $idSize l'identifiant de la taille
     * @param int $numOrder l'identifiant de la commande
     * @param int $quantityBought la quantité achetée
     * @param int $idCustomer l'identifiant du client
     * @return boolean Si le produit a bien été déplacé dans les ProductBought
     */
    public function buyProduct(string $nameColor, int $idProd, int $idSize, int $numOrder, int $quantityBought, int $idCustomer)
    {
        // Déplacer le produit du panier de l'utilisateur vers l'historique des produits achetés du site
        $product_bought = $this->queryBdd("INSERT INTO productbought (namecolor, idprod, idsize,numorder,quantitybought) VALUES (?,?,?,?,?) ",array(
            //idPP, NameColor, idProd, idSize, numOrder, QuantityBought
            $nameColor,
            $idProd,
            $idSize,
            $numOrder,
            $quantityBought

        ));

        // Décrémentation du stock du produit
        $productStockDelete = $this->queryBdd("UPDATE stocked SET quantitystocked = quantitystocked - ? WHERE idprod= ? AND idsize= ? AND namecolor= ?", array(
            $quantityBought,
            $idProd,
            $idSize,
            $nameColor
        ));

        // Supression du produit dans le panier de l'utilisateur
        $productCartDelete = $this->queryBdd("DELETE FROM cart WHERE idcustomer = $idCustomer AND idprod = ? AND idsize = ? AND namecolor= ?", array(
            $idProd,
            $idSize,
            $nameColor
        )); 
        return $product_bought && $productCartDelete && $productStockDelete;
    }
    
}
