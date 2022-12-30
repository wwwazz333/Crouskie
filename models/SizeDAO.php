<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Size.php');

class SizeDAO extends DAO{

    public function resultToProductsArray(array $result){
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


    public function getProductColors(int $id){
        $result = $this->queryAll("SELECT * FROM cloth_size WHERE idsize = ?",array($id));
        return $result;
    }
}