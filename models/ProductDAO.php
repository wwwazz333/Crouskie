<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Product.php');

class ProductDAO extends DAO{

    public function resultToProductsArray(array $result){
        $list = [];
        foreach($result as $product){
            array_push($list, 
                new Product(
                    $product['idprod'],
                    $product['idcollection'] == null ? 'NULL' : $product['idcollection'],
                    $product['nameprod'],
                    $product['descriptionprod'],
                    $product['priceprod']
                )
            );
        }
        return $list;
    }

    public function resultToProduct(array $result){
        return new Product(
            $result['idprod'],
            $result['idcollection'] == null ? 'NULL' : $result['idcollection'],
            $result['nameprod'],
            $result['descriptionprod'],
            $result['priceprod']
        );
    }

    // obtenir tout les produits
    public function getProducts() : array
    {
        $result = $this->queryAll("SELECT * FROM product");
        return $result;
    }
    public function getProductsByCollectionId(int $id) : array
    {
        $result = $this->queryAll("SELECT * FROM product WHERE idcollection = ?",array($id));
        return $result;
    }

    // Obtenir tout les produits associé à un nom
    public function getProductsByName(string $name) : array
    {
        $result = $this->queryAll("SELECT * FROM product WHERE nameprod LIKE ?",array('%' . $name . '%'));
        return $result;
    }

    // Obtenir un produit par son ID
    public function getProductByID(int $id) // : mixed
    {
        $product = $this->queryRow("SELECT * FROM product WHERE idprod = ?",array($id));
        return $product == false ? false : $product;
    }

    public function getProductImages(int $id) : array
    {
        $result = $this->queryAll("SELECT pathpicture, altpicture FROM picture WHERE idprod = ?",array($id));
        return $result;
    }


    // Temporaire car je pense qu'il faudra le changer de DAO
    public function getStockStatus(int $id) : bool
    {
        $stock = $this->queryAll("SELECT count(*) FROM product NATURAL JOIN stocked WHERE idprod = ?",array($id));
        if ($stock["count(*)"]>0){
            return true;
        }
        else{return false;}
    }
    
}