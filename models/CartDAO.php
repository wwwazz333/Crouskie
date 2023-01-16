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
        $cart = $this->queryAll("SELECT * FROM cart WHERE idcustomer = ?", array($id));
        return $cart;
    }

    /**
     * Retourne le nombre d'article dans le panier d'un utilisateur
     * @param int $id L'identifiant de l'utilisateur
     * @return int Le nombre d'articles dans le panier
     */
    public function getCartCountByCustomerId(int $id) : int
    {
        // NVL permet de mettre une valeur par défaut si le résulat d'une opération est nulle
        $count = $this->queryRow("SELECT NVL(sum(quantitycart),0) FROM cart WHERE idcustomer = ?",array($id));
        return $count[0];
    }
    
    /**
     * Permet d'ajouter un produit au panier de l'utilisateur
     * @param int $userId L'identifiant de l'utilisateur
     * @param int $productId L'identifiant du produit
     * @param int $quantityCart La quantité du produit
     * @param int $idsize La taille du produit
     * @param string $namecolor La couleur du produit
     * @return bool True si l'opération a réussie sinon False
     */
    public function addCart(int $userId, int $productId, int $quantityCart, int $idsize, string $namecolor) : bool {
        $result = $this->queryBdd("INSERT INTO cart VALUES (?,?,?,?,?)", array(
            $userId,$productId,$quantityCart,$idsize,$namecolor
        ));
        return $result;
    }

    /**
     * Permet de supprimer tout les produits contenus dans le panier d'un utilisateur grâce à son identifiant
     * @param int $id L'identifiant de l'utilisateur
     * @return bool True si l'opération a réussie sinon False
     */
    public function deleteCart(int $id) : bool {
        $result = $this->queryBdd("DELETE FROM cart WHERE idcustomer = ?", array($id));
        return $result;
    }

    /**
     * Permet de supprimer un produit précis du panier d'un utilisteurs grâce à leurs identifiant respectifs
     * @param int $userId L'identifiant de l'utilisateur
     * @param int $productId L'identifiant du produit
     * @param string $namecolor La couleur du produit
     * @param int $idsize La taille du produit
     * @return bool True si l'opération a réussie sinon False
     */
    public function deleteProductFromCart(int $userId, int $productId, string $namecolor, int $idsize) : bool {
        $result = $this->queryBdd("DELETE FROM cart WHERE idcustomer = ? AND idprod = ? AND namecolor = ? AND idsize = ?", 
        array($userId,$productId,$namecolor,$idsize));
        return $result;
    }

    /**
     * Permet de modifier le nombre d'un produit précis du panier d'un utilisteurs grâce à leurs identifiant respectifs
     * @param int $quantity La quantité du produit
     * @param int $userId L'identifiant de l'utilisateur
     * @param int $productId L'identifiant du produit
     * @param string $namecolor La couleur du produit
     * @param int $idsize La taille du produit
     * @return bool True si l'opération a réussie sinon False
     */
    public function setQuantityProductFromCart(int $quantity, int $userId, int $productId, string $namecolor, int $idsize) : bool {
        $result = $this->queryBdd("UPDATE cart SET quantitycart = ? WHERE idcustomer = ? AND idprod = ? AND namecolor = ? AND idsize = ?", 
        array($quantity,$userId,$productId,$namecolor,$idsize));
        return $result;
    }

    /**
     * Permet d'obtenir le nombre d'un produit précis du panier d'un utilisteurs grâce à leurs identifiant respectifs
     * @param int $userId L'identifiant de l'utilisateur
     * @param int $productId L'identifiant du produit
     * @param string $namecolor La couleur du produit
     * @param int $idsize La taille du produit
     * @return bool True si l'opération a réussie sinon False
     */
    public function getQuantityProductFromCart(int $userId, int $productId, string $namecolor, int $idsize) : int
    {
        $result = $this->queryRow("SELECT NVL(sum(quantitycart),0) FROM cart WHERE idcustomer = ? AND idprod = ? AND namecolor = ? AND idsize = ?",
        array($userId,$productId,$namecolor,$idsize));
        return $result[0];
    }
    
}
