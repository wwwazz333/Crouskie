<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Color.php');

/**
 * Ce DAO permet d'intéragir avec les couleurs relié à un produit
 */
class ColorDAO extends DAO{

    /**
     * Permet de convertir les requêtes PHP retournant plusieurs résultats en un tableau d'Objets (ici Color)
     * @param array $result Le tableau des résultats fournis par les requêtes SQL via le PHP
     * @return array La liste des objets
     */
    public function resultToColorsArray(array $result){
        $colors = [];
        foreach($result as $color){
            array_push($colors, 
                new Color(
                    $color['namecolor'],
                    $color['code']
                )
            );
        }
        return $colors;
    }

    /**
     * Récupère toutes les couleurs disponible (pas en stock) pour un produit donné
     * @param int $id Représente l'id du produit
     */
    public function getColorsByProductID(int $id){
        $result = $this->queryAll("SELECT * FROM existingcolor NATURAL JOIN color WHERE idprod = ?",array($id));
        return $result;
    }
}