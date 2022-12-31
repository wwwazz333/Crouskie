<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Color.php');

class ColorDAO extends DAO{

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

    public function getColorsByProductID(int $id){
        $result = $this->queryAll("SELECT * FROM existingcolor NATURAL JOIN color WHERE idprod = ?",array($id));
        return $result;
    }
}