<?php 
require_once(PATH_MODELS . 'CartDAO.php');

$cartDAO = new CartDAO(DEBUG);
$userId = $user->getId();
$cart = $cartDAO->getCartByCustomerId($userId);






require_once(PATH_VIEWS . $page . '.php');
