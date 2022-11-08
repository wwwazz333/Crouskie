<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Product.php');

class ProductDAO extends DAO{

    public function resultToProductsArray(array $result){
        $products = [];
        foreach($result as $product){
            array_push($products, 
                new Product(
                    $product['IDPROD'],
                    $product['IDCOLLECTION'],
                    $product['NAMEPROD'],
                    $product['DESCRIPTIONPROD'],
                    $product['PRICEPROD']
                )
            );
        }
        return $products;
    }

    // obtenir tout les produits
    public function getProducts() : array
    {
        $result = $this->queryAll("SELECT * FROM product");
        return $result;
    }

    // Obtenir tout les produits associé à un nom
    public function getProductsByName(string $name){
        $result = $this->queryAll("SELECT * FROM product WHERE nameprod LIKE ?",array('%' . $name . '%'));
        return $result;
    }

    // Obtenir un produit par son ID
    public function getProductByID(int $id)
    {
        $product = $this->queryRow("SELECT * FROM product WHERE idprod = ?",array($id));
        return $product == false ? false : $product;
    }

    
}