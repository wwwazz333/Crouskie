<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'ProductBought.php');

class ProductBoughtDAO extends DAO
{

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
    
    // get all the existing carted items
    public function getProductBoughtByCustomerId(int $id)
    {
        $product_bought = $this->queryAll("SELECT * FROM cmd c cross join productbought p where c.numorder=p.numorder and idcustomer = ?",
        array($id));
        return $product_bought == false ? false : $product_bought;
    }
    
}
