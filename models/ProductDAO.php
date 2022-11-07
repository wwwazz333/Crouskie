<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Product.php');

class ProductDAO extends DAO{
    // obtenir tous les produits
    public function getProducts() : array
    {
        $result = $this->queryAll("SELECT * FROM product");
        $products = [];
        foreach($result as $product){
            array_push($products, 
                new Product(
                    $product['idprod'],
                    $product['idcollection'],
                    $product['nameprod'],
                    $product['descriptionprod'],
                    $product['priceprod']
                )
            );
        }
        return $products;
    }

    public function getProductByID(int $id)
    {
        $product = $this->queryRow("SELECT * FROM product WHERE idprod = ?",array($id));
        return $product == false ? false : new Product(
            $product['idprod'],
            $product['idcollection'],
            $product['nameprod'],
            $product['descriptionprod'],
            $product['priceprod']
        );
    }
}