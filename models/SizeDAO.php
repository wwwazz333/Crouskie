<?php
require_once(PATH_MODELS . 'DAO.php');
require_once(PATH_ENTITY . 'Size.php');

class SizeDAO extends DAO{

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

    public function getSizesByProductID(int $id){
        $result = $this->queryAll("SELECT * FROM existingsize NATURAL JOIN cloth_size WHERE idprod = ?",array($id));
        return $result;
    }

    public function getSizeBySizeId(int $idSize){
        $result = $this->queryAll("SELECT namesize FROM size WHERE idsize = ?",array($idSize));
        return $result;
    }

    public function getFullSizeBySizeId(int $idSize){
        $result = $this->queryAll("SELECT * FROM size WHERE idsize = ?",array($idSize));
        return $result;
    }
}