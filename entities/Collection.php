<?php
// représente un produit vendu par crouskie
class Collection
{
    // properties
    private string $_name;
    private int $_collection_id;
    private string $_path_picture;



    function __construct(int $name, int $_collection_id, string $_path_picture)
    {
        $this->_name = $name;
        $this->_collection_id = $_collection_id;
        $this->_path_picture = $_path_picture;
    }

    // Getters and setters
    function getName(): string
    {
        return $this->_name;
    }
    function getCollectionId(): int
    {
        return $this->_collection_id;
    }
    function getPathPicture(): string
    {
        return $this->_path_picture;
    }
}
