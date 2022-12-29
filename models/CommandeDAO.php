<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY.'Commande.php');

class CommandeDAO extends DAO
{

    public function resultToCartArray(array $result){
        $commande = [];
        foreach($result as $commande){
            array_push($commande, 
                new Commande(
                    $cart['dateorder'],
                    $cart['numorder'],
                    $cart['idcustomer']
                )
            );
        }
        return $commande;
    }
    
    // get all the existing carted items
    public function getCommandeByCustomerId(int $id)
    {
        $commande = $this->queryAll("SELECT * FROM cmd where idcustomer = ?", array($id));
        return $commande == false ? false : $cart;
    }
    
}
