<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Product.php');

class ProductDAO extends DAO{

    public function resultToProductsArray(array $result){
        $products = [];
        foreach($result as $product){
            array_push($products, 
                new Product(
                    $product['idprod'],
                    $product['idcollection'] == null ? 'NULL' : $product['idcollection'],
                    $product['nameprod'],
                    $product['descriptionprod'],
                    $product['priceprod']
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

    public function getStockStatus(int $id) : bool
    {
        $stock = $this->queryAll("select count(*) from product natural join stocked where idprod = ?",array($id));
        if ($stock["count(*)"]>0){
            return true;
        }
        else{return false;}
    }

    
}