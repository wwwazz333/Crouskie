<?php
/**
 * La clase Cart représente un produit du panier de l'utilisateur
 */
class Cart
{
    // ----------- Properties -------------

    private int $_customer_id;
    private int $_id_prod;
    private int $_quantity_cart;
    private String $_color;
    private int $_size;

    /**
     * Constructeur de la classe Cart
     * @param int $customer_id L'identifiant de l'utilisateur
     * @param int $id_prod L'identifiant du produit
     * @param int $quantity_cart La quantité du produit
     * @param string $color La couleur du produit
     * @param int $size L'identifiant de la taille
     */
    function __construct(int $customer_id, int $id_prod, int $quantity_cart, String $color, int $size)
    {
        $this->_customer_id = $customer_id;
        $this->_id_prod = $id_prod;
        $this->_quantity_cart = $quantity_cart;
        $this->_color = $color;
        $this->_size = $size;
    }

    // ----------- Getters -------------

    /**
     * Recupère l'identifiant de l'utilisateur
     * @return int L'identifiant du client
     */
    function getCustomerId() : int {
        return $this->_customer_id;
    }

    /**
     * Recupère l'identifiant du produit
     * @return int L'identifiant du produit
     */
    function getIdProd() : int {
        return $this->_id_prod;
    }

    /**
     * Recupère la quantité du produit du panier
     * @return int La quantité du produit
     */
    function getQuantityCart() : int {
        return $this->_quantity_cart;
    }

    /**
     * Recupère la couleur du produit
     * @return string Le nom de la couleur du produit
     */
    function getColorCart() : string {
        return $this->_color;
    }

    /**
     * Recupère l'identifiant de la couleur du produit
     * @return int L'identifiant de la couleur
     */
    function getSizeCart() : int {
        return $this->_size;
    }
}
