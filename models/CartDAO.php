<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Cart.php');

class CartDAO extends DAO
{

    public function resultToCartArray(array $result){
        $cart = [];
        foreach($result as $cart){
            array_push($cart, 
                new Cart(
                    $cart['idcustomer'],
                    $cart['idprod'],
                    $cart['quantitycart'],
                    $cart['color'],
                    $cart['size']
                )
            );
        }
        return $cart;
    }
    
    // get all the existing carted items
    public function getCartByCustomerId(int $id)
    {
        $cart = $this->queryAll("SELECT * FROM cart where idcustomer = ?", array($id));
        return $cart == false ? false : $cart;
    }
    
    // delete all the items from the cart of an user
    public function deleteCart(int $userId)
    {
        $result = $this->queryBdd("DELETE from cart where idcustomer = ?", array($userId));
        return $result;
    }

    // delete a defined product from the cart of an user
    public function deleteProductFromCart(int $userId, int $productId)
    {
        $result = $this->queryBdd("DELETE from cart where idcustomer = ? and idprod = ?", array($userId,$productId));
        return $result;
    }

    // increase quantity of 1 for a defined product from the cart of an user
    public function increaseQuantityProductFromCart(int $userId, int $productId)
    {
        $result = $this->queryBdd("UPDATE from cart set quantitycart = quantitycart + 1 where idcustomer = ? and idprod = ?", 
        array($userId,$productId));
        return $result;
    }

    // decrease quantity of 1 for a defined product from the cart of an user
    public function decreaseQuantityProductFromCart(int $userId, int $productId)
    {
        $result = $this->queryBdd("UPDATE from cart set quantitycart = quantitycart - 1 where idcustomer = ? and idprod = ?", 
        array($userId,$productId));
        return $result;
    }
    
}
