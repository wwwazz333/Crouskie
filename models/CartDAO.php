<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY.'Cart.php');

class CartDAO extends DAO
{

    public function resultToCartArray(array $result){
        $cart = [];
        foreach($result as $cart){
            array_push($cart, 
                new Cart(
                    $cart['IDCUSTOMER'],
                    $cart['IDPROD'],
                    $cart['QUANTITYCART'],
                    $cart['COLOR'],
                    $cart['SIZE']
                )
            );
        }
        return $cart;
    }
    
    // get all the existing carted items
    public function getCartByCustomerId(int $id)
    {
        $cart = $this->queryAll("SELECT * FROM cart where IDCUSTOMER = ?", array($id));
        return $cart == false ? false : $cart;
    }
    
    // delete all the items from the cart of an user
    public function deleteCart(int $userId)
    {
        $result = $this->queryBdd("DELETE from cart where IDCUSTOMER = ?", array($userId));
        return $result;
    }

    // delete a defined product from the cart of an user
    public function deleteProductFromCart(int $userId, int $productId)
    {
        $result = $this->queryBdd("DELETE from cart where IDCUSTOMER = ? and IDPROD = ?", array($userId,$productId));
        return $result;
    }
}
