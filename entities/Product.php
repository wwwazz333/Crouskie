<?php
/**
 * Cette classe représente un produit vendu par crouskie
 */
class Product{

    // ----------- Properties -------------

    private int $_id;
    private int $_collection_id;
    private string $_name;
    private string $_description;
    private float $_price;

    /**
     * Constructeur de la classe Product
     * @param int $id L'identifiant du produit
     * @param int $id L'identifiant de la collection associée au produit
     * @param string $name Le nom du produit
     * @param string $description La description brève du produit
     * @param float $price le prix du produit en €
     */
    function __construct(int $id,int $collection_id, string $name, string $description, float $price)
    {
        $this->_id = $id;
        $this->_collection_id = $collection_id;
        $this->_name = $name;
        $this->_description = $description;
        $this->_price = $price;
    }

    // ----------- Getters -------------

    /**
     * Retourne l'identifiant du produit
     * @return int L'identifiant du produit
     */
    public function getId() : int {
        return $this->_id;
    }

    /**
     * Retourne l'identifiant du de la collection associée au produit
     * @return int L'identifiant du de la collection associée au produit
     */
    public function getCollectionId() : int {
        return $this->_collection_id;
    }

    /**
     * Retourne le nom du produit
     * @return string Le nom du produit
     */
    public function getName() : string {
        return $this->_name;
    }

    /**
     * Retourne la description du produit
     * @return string La description du produit
     */
    public function getDescription() : string
    {
        return $this->_description;
    }

    /**
     * Retourne le prix en euro du produit
     * @return float Le prix en euro du produit
     */
    public function getPrice() : float
    {
        return $this->_price;
    }

    /**
     * Retourne le prix en euro du produit avec un format 00.00
     * @return string Le prix en euro du produit
     */
    public function getPriceString() : string
    {
        return number_format((float)$this->_price, 2, '.', '');
    }

}