<?php
require_once(PATH_MODELS . 'collectionDAO.php');

$DAO = new CollectionDAO(DEBUG);
$lastCollection = $DAO->getLastCollection(); //for the DAO
$lastCollection = new Collection( //create an object from the DAO
    $lastCollection['namecollection'],
    $lastCollection['idcollection'],
    $lastCollection['pathpicture']
);
require_once(PATH_VIEWS . $page . '.php');