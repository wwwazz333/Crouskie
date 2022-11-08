<?php
// représente un produit vendu par crouskie
class Collection{
    // properties
    private string $_name;
    private int $_collection_id;
    private string $_path_picture;
    
    
    
    function __construct__(int $name, int $_collection_id, string $_path_picture)
    {
        $this->_name = $name;
        $this->_collection_id = $_collection_id;
        $this->_path_picture = $_path_picture;
    }

    // Getters and setters

    
}