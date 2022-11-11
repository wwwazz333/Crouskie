<?php
// représente un produit vendu par crouskie
class Product{
    // properties
    private int $_id;
    private int $_collection_id;
    private string $_name;
    private string $_description;
    private float $_price;

    function __construct(int $id, int $collection_id, string $name, string $description, float $price)
    {
        $this->_id = $id;
        $this->_collection_id = $collection_id;
        $this->_name = $name;
        $this->_description = $description;
        $this->_price = $price;
    }

    // Getters and setters

    public function getId() : int
    {
        return $this->_id;
    }

    public function getCollectionId() : int
    {
        return $this->_collection_id;
    }

    public function getName() : string
    {
        return $this->_name;
    }

    public function getDescription() : string
    {
        return $this->_description;
    }

    public function getPrice() : float
    {
        return $this->_price;
    }
}