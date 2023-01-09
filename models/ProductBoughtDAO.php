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
     * @return mixed ProductBought Si le produit a bien été trouvé
     */
    public function getProductBoughtByCustomerId(int $id)
    {
        $product_bought = $this->queryAll("SELECT * FROM cmd c cross join productbought p where c.numorder=p.numorder and idcustomer = ?",
        array($id));
        return $product_bought == false ? false : $product_bought;
    }

    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Product)
     * @param Product $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return mixed Si le produit a bien été déplacé dans les ProductBought
     */
    public function buyProduct(string $nameColor, int $idProd, int $idSize, int $numOrder, int $quantityBought, int $idCustomer)
    {
        $quantity = $this->queryAll("SELECT quantitystocked FROM stocked WHERE $idProd = idprod");
        // si il reste des produits en stock
        if ($quantity > 0){
            // Ne pas oublier d'enlever du stock le produit 
            $product_bought = $this->queryBdd("INSERT INTO productbought (namecolor, idprod, idsize,numOrder,quantitybought) VALUES (?,?,?,?,?) ",array(
                //idPP, NameColor, idProd, idSize, numOrder, QuantityBought
                $nameColor,
                $idProd,
                $idSize,
                $numOrder,
                $quantityBought

            ));
            $product_delete = $this->queryBdd("DELETE FROM cart WHERE idcustomer = $idCustomer AND idprod = $idProd", array()); 
        }
        else{
            //raise error produit en rupture de stock
            return 2;
        }
        return $product_bought && $product_delete;
    }
    
}
