<?php
/**
 * Cette classe représente une commandé passée par un client
 */
class Commande
{
    // ----------- Properties -------------

    private string $_date_order;
    private int $_num_order;
    private int $_id_customer;

    /**
     * Constructeur de la classe Commande
     * @param string $date
     */
    function __construct(string $date_order, int $num_order, int $id_customer)
    {
        $this->_date_order = $date_order;
        $this->_num_order = $num_order;
        $this->_id_customer = $id_customer;
    }

    // ----------- Getters -------------
    
    /**
     * Retourne la date de la commande
     * @return string La date de la commande
     */
    function getDateOrder() : string {
        return $this->_date_order;
    }

    /**
     * Retourne le numéro de la commande
     * @return int Le numéro de la commande
     */
    function getNumOrder() : int {
        return $this->_num_order;
    }

    /**
     * Retourne le numéro du client qui a passé la commande
     * @return int L'indentifiant de l'utilisateur
     */
    function getIdCustomer() : int {
        return $this->_id_customer;
    }
}
