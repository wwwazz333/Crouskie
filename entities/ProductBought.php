<?php
// représente un produit vendu par crouskie
class ProductBought{

    // ----------- Properties -------------

    private int $_idpp;
    private string $_name_color;
    private int $_id_prod;
    private int $_id_size;
    private int $_num_order;
    private int $_quantity_bought;

    /**
     * Constructeur de la classe ProductBought
     * @param int $idpp L'identifiant du produit
     * @param string $name_color La couleur du produit
     * @param int $id_prod L'identifiant du produit
     * @param int $id_size L'identifiant de la taille du produit
     * @param int $num_order Le numéro de la commande
     * @param int $quantity_bought La quantité achetée
     */
    function __construct(int $idpp,string $name_color, int $id_prod, int $id_size, int $num_order, int $quantity_bought)
    {
        $this->_idpp = $idpp;
        $this->_name_color = $name_color;
        $this->_id_prod = $id_prod;
        $this->_id_size = $id_size;
        $this->_num_order = $num_order;
        $this->_quantity_bought = $quantity_bought;
    }

    // ----------- Getters -------------

    /**
     * Retourne l'identifiant du produit
     * @return int L'identifiant du produit
     */
    public function getIdpp() : int {
        return $this->_idpp;
    }

    /**
     * Retourne le nom de la couleur du produit
     * @return int Le nom de la couleur
     */
    public function getNameColor() : string {
        return $this->_name_color;
    }

    /**
     * Retourne l'identifiant du produit
     * @return int L'identifiant du produit
     */
    public function getIdProd() : int {
        return $this->_id_prod;
    }

    /**
     * Retourne l'identifiant de la taille du produit
     * @return int L'identifiant de la taille
     */
    public function getIdSize() : int {
        return $this->_id_size;
    }

    /**
     * Retourne le numéro de commande
     * @return int Le numéro de commande
     */
    public function getNumOrder() : int {
        return $this->_num_order;
    }

    /**
     * Retourne le nombre de produit
     * @return int Le nombre de produit
     */
    public function getQuantityBought() : int {
        return $this->_quantity_bought;
    }
}