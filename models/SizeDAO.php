<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Size.php');

class SizeDAO extends DAO{

    public function resultToSizesArray(array $result){
        $sizes = [];
        foreach($result as $size){
            array_push($sizes, 
                new Size(
                    $size['idsize'],
                    $size['namesize']
                )
            );
        }
        return $sizes;
    }

    public function getSizesByProductID(int $id){
        $result = $this->queryAll("SELECT * FROM existingsize NATURAL JOIN cloth_size WHERE idprod = ?",array($id));
        return $result;
    }
}