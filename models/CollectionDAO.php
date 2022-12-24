<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY.'Collection.php');

class CollectionDAO extends DAO
{
    public function resultToCollectionArray(array $result){
        $collections = [];
        foreach($result as $collection){
            array_push($collections, 
                new Collection(
                    $collection['namecollection'],
                    $collection['idcollection'],
                    $collection['pathpicture']
                )
            );
        }
        return $collections;
    }
    
    // Obtenir tout les produits associé à un nom
    public function getCollectionsByName(string $name)
    {
        $result = $this->queryAll("SELECT * FROM collection WHERE namecollection LIKE ?", array('%' . $name . '%'));
        return $this->resultToCollectionArray($result);
    }
    //get all the existing collections
    public function getCollections(): array
    {
        $result = $this->queryAll("SELECT * FROM collection");
        return $this->resultToCollectionArray($result);
    }
    // Obtenir un produit par son ID
    public function getCollectionsByID(int $id)
    {
        $collection = $this->queryRow("SELECT * FROM collection WHERE idcollection = ?", array($id));
        return $collection == false ? false : $collection;
    }
    public function getLastCollection(){
        $lastCollection = $this->queryRow("SELECT * FROM collection order by idcollection desc");
        return $lastCollection;
    }
}
