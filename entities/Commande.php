<?php
class Commande
{
    // properties
    private string $_date_order;
    private int $_num_order;
    private int $_id_customer;

    function __construct(string $_date_order, int $_num_order, int $_id_customer)
    {
        $this->_date_order = $_date_order;
        $this->_num_order = $_num_order;
        $this->_id_customer = $_id_customer;
    }

    // getter
    function getDateOrder()
    {
        return $this->_date_order;
    }
    function getNumOrder()
    {
        return $this->_num_order;
    }
    function getIdCustomer()
    {
        return $this->_id_customer;
    }
}
