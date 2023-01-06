<?php
/**
 * Cette classe représente un produit vendu par crouskie
 */
class Collection
{
    // ----------- Properties -------------

    private string $_name;
    private int $_collection_id;
    private string $_path_picture;

    /**
     * Constructeur de la classe Collection
     * @param string $name Le nom de la collection
     * @param int $collection_id L'identifiant de la collection
     * @param string $path_picture L'url de l'image associée à la collection
     */
    function __construct(string $name, int $collection_id, string $path_picture)
    {
        $this->_name = $name;
        $this->_collection_id = $collection_id;
        $this->_path_picture = $path_picture;
    }

    // ----------- Getters -------------
    
    /**
     * Récupère le nom de la collection
     * @return string Le nom de la collection
     */
    function getName() : string {
        return $this->_name;
    }

    /**
     * Récupère l'identifiant de la collection
     * @return int L'identifiant de la collection
     */
    function getCollectionId() : int {
        return $this->_collection_id;
    }

    /**
     * Récupère l'url de l'image associée à la collection
     * @return string L'url de l'image associé à la collection
     */
    function getPathPicture() : string
    {
        return $this->_path_picture;
    }
}
