<?php
require_once(PATH_MODELS . 'ProductDAO.php');

// On vérifie si une recherche est effectuée
$DAO = new ProductDAO(DEBUG);
if (isset($_GET['q'])) {
    $products = $DAO->resultToProductsArray($DAO->getProductsByName($_GET['q']));
}else{
    $products = $DAO->resultToProductsArray($DAO->getProducts());
}

require_once(PATH_VIEWS . $page . '.php');