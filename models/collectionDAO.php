<?php
require_once(PATH_MODELS . 'DAO.php');

class collectionDAO extends DAO
{

    public function resultToCollectionArray(array $result){
        $collection = [];
        foreach($result as $collection){
            array_push($collection, 
                new Collection(
                    $collection['NAMECOLLECTION'],
                    $collection['IDCOLLECTION'],
                    $collection['PATHPICTURE']
                )
            );
        }
        return $collection;
    }
    
    // Obtenir tout les produits associé à un nom
    public function getCollectionsByName(string $name)
    {
        $result = $this->queryAll("SELECT * FROM collection WHERE NAMECOLLECTION LIKE ?", array('%' . $name . '%'));
        return $this->resultToCollectionArray($result);
    }
    //get all the existing collections
    public function getCollections(): array
    {
        $result = $this->queryAll("SELECT * FROM collection");
        return $this->resultToCollectionArray($result);
    }
    // Obtenir un produit par son ID
    public function getProductByID(int $id)
    {
        $collection = $this->queryRow("SELECT * FROM collection WHERE IDCOLLECTION = ?", array($id));
        return $collection == false ? false : $collection;
    }
}
