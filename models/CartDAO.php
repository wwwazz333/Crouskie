<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY.'Cart.php');

class collectionDAO extends DAO
{

    public function resultToCartArray(array $result){
        $cart = [];
        foreach($result as $cart){
            array_push($cart, 
                new Cart(
                    $cart['IDCUSTOMER'],
                    $cart['IDPROD'],
                    $cart['QUANTITYCART']
                )
            );
        }
        return $cart;
    }
    
      //get all the existing collections
      public function getCartByCustomerId(int $id)
      {
          $cart = $this->queryAll("SELECT * FROM cart where IDCUSTOMER = ?",array($id));
          return $cart == false ? false : $cart;
      }
      
}
