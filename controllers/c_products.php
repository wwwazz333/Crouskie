<?php
require_once(PATH_MODELS . 'ProductDAO.php');

$DAO = new ProductDAO(DEBUG);
$products = $DAO->resultToProductsArray($DAO->getProducts());

require_once(PATH_VIEWS . $page . '.php');