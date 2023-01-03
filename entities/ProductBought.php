<?php
// représente un produit vendu par crouskie
class ProductBought{
    // properties
    private int $_idpp;
    private string $_name_color;
    private int $_id_prod;
    private int $_id_size;
    private int $_num_order;
    private int $_quantity_bought;

    function __construct(int $idpp,string $name_color, int $id_prod, int $id_size, int $num_order, int $quantity_bought)
    {
        $this->_idpp = $idpp;
        $this->_name_color = $name_color;
        $this->_id_prod = $id_prod;
        $this->_id_size = $id_size;
        $this->_num_order = $num_order;
        $this->_quantity_bought = $quantity_bought;
    }

    // Getters and setters

    public function getIdpp() 
    {
        return $this->_idpp;
    }

    public function getNameColor() 
    {
        return $this->_name_color;
    }

    public function getIdProd() 
    {
        return $this->_id_prod;
    }

    public function getIdSize() 
    {
        return $this->_id_size;
    }

    public function getNumOrder() 
    {
        return $this->_num_order;
    }

    public function getQuantityBought() 
    {
        return $this->_quantity_bought;
    }

}