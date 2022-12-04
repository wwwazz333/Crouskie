<?php
require_once(PATH_MODELS . 'CollectionDAO.php');
$DAO = new CollectionDAO(DEBUG);
$lastCollection = $DAO->getLastCollection(); //for the DAO
$lastCollection = new Collection( //create an object from the DAO
    $lastCollection['NAMECOLLECTION'],
    $lastCollection['IDCOLLECTION'],
    $lastCollection['PATHPICTURE']
);
require_once(PATH_VIEWS . $page . '.php');