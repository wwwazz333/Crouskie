<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Collection.php');

/**
 * Ce DAO permet l'intéraction avec les collections de produits crouskies
 */
class CollectionDAO extends DAO
{
    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Collection)
     * @param array $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return array La liste des objets
     */
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
    
    /**
     * Obtenir tout les produits associé à un nom d'une collection
     * @param string $name Nom de la collection
     * @return array Liste de dictionnaires
     */
    public function getCollectionsByName(string $name)
    {
        $result = $this->queryAll("SELECT * FROM collection WHERE namecollection LIKE ?", array('%' . $name . '%'));
        return $this->resultToCollectionArray($result);
    }
    
    /**
     * Retourne la liste de toutes les collections existantes
     * @return array Liste de dictionnaires
     */
    public function getCollections(): array
    {
        $result = $this->queryAll("SELECT * FROM collection");
        return $this->resultToCollectionArray($result);
    }
    
    /**
     * Récupère la collection grâce à son identifiant
     * @param int $id L'identifiant de la collection
     * @return mixed False si non trouvée sinon un dictionnaire
     */
    public function getCollectionByID(int $id)
    {
        $collection = $this->queryRow("SELECT * FROM collection WHERE idcollection = ?", array($id));
        return $collection;
    }

    /**
     * Récupère la dernière collection sortie par crouskie
     * @return mixed False si aucune sinon un dictionnaire
     */
    public function getLastCollection(){
        $lastCollection = $this->queryRow("SELECT * FROM collection order by idcollection desc");
        return $lastCollection;
    }
}
