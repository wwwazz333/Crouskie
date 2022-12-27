<?php
require_once(PATH_MODELS . 'ProductDAO.php');

$DAO = new ProductDAO(DEBUG);

if (isset($_GET['id'])) {
    $product = $DAO->getProductByID($_GET['id']);
    if ($product != false) {
        $product = $DAO->resultToProduct($product);
    }else{
        header('Location: index.php?page=404');
        exit();
    }
}

require_once(PATH_VIEWS . $page . '.php');