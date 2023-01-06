<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Size.php');

/**
 * Ce DAO permet d'intéragir avec les tailles associées à un produit
 */
class SizeDAO extends DAO{

    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Size)
     * @param array $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return array La liste des objets
     */
    public function resultToSizesArray(array $result){
        $list = [];
        foreach($result as $size){
            array_push($list, 
                new Size(
                    $size['idsize'],
                    $size['namesize']
                )
            );
        }
        return $list;
    }

    /**
     * Retourne la liste des tailles existantes pour un produit grâce à son identifiant
     * @param int $id L'identifiant du produit
     * @return array Retourne une liste de dictionnaires
     */
    public function getSizesByProductID(int $id) : array {
        $result = $this->queryAll("SELECT * FROM existingsize NATURAL JOIN cloth_size WHERE idprod = ?",array($id));
        return $result;
    }

    /**
     * Retourne le nom de la taille grâce à son identifiant
     * @param int $idSize L'identifiant de la taille
     * @return mixed False si non existante sinon un dictionnaire
     */
    public function getSizeBySizeId(int $idSize) : array {
        $result = $this->queryRow("SELECT namesize FROM cloth_size WHERE idsize = ?",array($idSize));
        return $result;
    }

    /**
     * Retourne toutes les informations d'une taille donnée grâce à son identifiant
     * @param int $id L'identifiant de la taille
     * @return array Retourne une liste de dictionnaires
     */
    public function getFullSizeBySizeId(int $idSize) : array {
        $result = $this->queryAll("SELECT * FROM cloth_size WHERE idsize = ?",array($idSize));
        return $result;
    }
}