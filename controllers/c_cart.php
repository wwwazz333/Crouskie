<?php 
require_once(PATH_MODELS . 'CartDAO.php');

$cartDAO = new CartDAO(DEBUG);
// $userId = $user->getIdUser();
// $cart = $cartDAO->getCartByCustomerId($userId);






require_once(PATH_VIEWS . $page . '.php');
