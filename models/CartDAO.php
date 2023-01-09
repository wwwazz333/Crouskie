<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Cart.php');

/**
 * Ce DAO permet la gestion du panier de l'utilisateur
 */
class CartDAO extends DAO
{

    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Cart)
     * @param array $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return array La liste des objets
     */
    public function resultToCartArray(array $result) : array{
        $list = [];
        foreach($result as $cart){
            array_push($list, 
                new Cart(
                    $cart['idcustomer'],
                    $cart['idprod'],
                    $cart['quantitycart'],
                    $cart['namecolor'],
                    $cart['idsize']
                )
            );
        }
        return $list;
    }
    
    /**
     * Retourne la liste des produits dans le panier de l'utilisateur
     * @param int $id L'identifiant de l'utilisateur
     * @return array Liste de dictionnaires
     */
    public function getCartByCustomerId(int $id) : array {
        $cart = $this->queryAll("SELECT * FROM cart where idcustomer = ?", array($id));
        return $cart;
    }
    
    /**
     * Permet d'ajouter un produit au panier de l'utilisateur
     * @param Cart $cart Un objet cart qui représente un produit du panier en PHP
     * @return bool True si l'opération a réussie sinon False
     */
    public function addCart(Cart $cart) : bool {
        $result = $this->queryBdd("INSERT INTO cart VALUES (?,?,?,?,?)", array(
            $cart->getCustomerId(),
            $cart->getIdProd(),
            $cart->getQuantityCart(),
            $cart->getSizeCart(),
            $cart->getColorCart(),
        ));
        return $result;
    }

    /**
     * Permet de supprimer tout les produits contenus dans le panier d'un utilisateur grâce à son identifiant
     * @param int $id L'identifiant de l'utilisateur
     * @return bool True si l'opération a réussie sinon False
     */
    public function deleteCart(int $id) : bool {
        $result = $this->queryBdd("DELETE from cart where idcustomer = ?", array($id));
        return $result;
    }

    /**
     * Permet de supprimer un produit précis du panier d'un utilisteurs grâce à leurs identifiant respectifs
     * @param int $userId L'identifiant de l'utilisateur
     * @param int $productId L'identifiant du produit
     * @return bool True si l'opération a réussie sinon False
     */
    public function deleteProductFromCart(int $userId, int $productId) : bool {
        $result = $this->queryBdd("DELETE from cart where idcustomer = ? and idprod = ?", array($userId,$productId));
        return $result;
    }

    /**
     * Permet de modifier le nombre d'un produit précis du panier d'un utilisteurs grâce à leurs identifiant respectifs
     * @param int $quantity La quantité du produit
     * @param int $userId L'identifiant de l'utilisateur
     * @param int $productId L'identifiant du produit
     * @param int $namecolor La couleur du produit
     * @param int $idsize La taille du produit
     * @return bool True si l'opération a réussie sinon False
     */
    public function setQuantityProductFromCart(int $quantity, int $userId, int $productId, int $namecolor, int $idsize) : bool {
        $result = $this->queryBdd("UPDATE from cart set quantitycart = ? where idcustomer = ? and idprod = ? and namecolor = ? and idsize = ?", 
        array($quantity,$userId,$productId,$namecolor,$idsize));
        return $result;
    }
    
}
