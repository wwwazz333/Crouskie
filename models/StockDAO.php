<?php
require_once(PATH_MODELS . 'DAO.php');

class StockDAO extends DAO
{
    public function getSizesAvaibleFor(int $idProd)
    {
        $result = $this->queryAll("SELECT idsize FROM stocked WHERE idprod = ?",array($idProd));
        // 
        /*
        Permet passer un resultat ressemblant à :
        
        [
            {
                "0":4
                "idsize":4
            },
            {
                "0":2
                "idsize":2
            },

        ]

        A ça :

        [4,2]
        
        */
        $result = array_map(fn($value): mixed => $value[0],$result);
        return $result;
    }

    public function getColorsAvaibleFor(int $idProd)
    {
        $result = $this->queryAll("SELECT namecolor FROM stocked WHERE idprod = ?",array($idProd));
        $result = array_map(fn($value): mixed => $value[0],$result);
        return $result;
    }

    public function getColorsWithSize(int $idProd,int $idSize)
    {
        $result = $this->queryAll("SELECT namecolor FROM stocked WHERE idprod = ? AND idsize = ?",array($idProd,$idSize));
        $result = array_map(fn($value): mixed => $value[0],$result);
        return $result;
    }

    public function getSizesWithColor(int $idProd,string $color)
    {
        $result = $this->queryAll("SELECT idsize FROM stocked WHERE idprod = ? AND namecolor = ?",array($idProd,$color));
        $result = array_map(fn($value): mixed => $value[0],$result);
        return $result;
    }
}
