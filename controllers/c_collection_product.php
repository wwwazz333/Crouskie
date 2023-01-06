<?php
require_once(PATH_MODELS . 'ProductDAO.php');
require_once(PATH_MODELS . 'CollectionDAO.php');

$DAO = new ProductDAO(DEBUG);
$DAOCollection = new CollectionDAO(DEBUG);
if (isset($_GET['id'])) {
    
    $products = $DAO->resultToProductsArray($DAO->getProductsByCollectionId($_GET['id']));
    $collection = $DAOCollection->getCollectionByID($_GET['id']);
    $collection = new Collection( //create an object from the DAO
        $collection['namecollection'],
        $collection['idcollection'],
        $collection['pathpicture']
    );
}

require_once(PATH_VIEWS . $page . '.php');
