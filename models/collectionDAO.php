<?php
    require_once(PATH_MODELS . 'DAO.php');

    class collectionDAO extends DAO{

        // Obtenir tout les produits associé à un nom
    public function getCollectionsByName(string $name){
        $result = $this->queryAll("SELECT * FROM collection WHERE NAMECOLLECTION LIKE ?",array('%' . $name . '%'));
        return $this->resultToProductsArray($result);
    }
        //get all the existing collections
        public function getCollections() : array{
            $result = $this->queryAll("SELECT * FROM collection");
            return $this->resultToProductsArray($result);
        }
    }