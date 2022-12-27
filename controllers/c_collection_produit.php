<?php
require_once(PATH_MODELS . 'ProductDAO.php');

$DAO = new ProductDAO(DEBUG);

if (isset($_GET['id'])) {
    
    $products = $DAO->resultToProductsArray($DAO->getProductsByCollectionId($_GET['id']));
}

require_once(PATH_VIEWS . $page . '.php');
