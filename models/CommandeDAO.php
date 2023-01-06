<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Commande.php');

/**
 * Ce DAO permet d'intéragir avec les commandes d'un utilisateur
 */
class CommandeDAO extends DAO
{
    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Commande)
     * @param array $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return array La liste des objets
     */
    public function resultToCommandesArray(array $result){
        $list = [];
        foreach($result as $commande){
            array_push($list, 
                new Commande(
                    $commande['dateorder'],
                    $commande['numorder'],
                    $commande['idcustomer']
                )
            );
        }
        return $list;
    }
    
    /**
     * Récupère toutes les commandes d'un utilisateur grâce à son identifiant
     * @param int $id L'identifiant de l'utilisateur
     * @return mixed Liste de dictionnaires 
     */
    public function getCommandeByCustomerId(int $id)
    {
        $commande = $this->queryAll("SELECT * FROM cmd where idcustomer = ? order by dateorder desc", array($id));
        return $commande;
    }

    public function addCommande($date,int $numorder,int $idCustomer,){
        $commande = $this->queryBdd("INSERT INTO cmd VALUES (?,?,?)", array($date,$numorder,$idCustomer));
    }
    
}