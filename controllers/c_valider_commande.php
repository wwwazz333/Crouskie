<?php

// Si l'utilisateur n'est pas connecté
if (!$isLogged) {
    header('Location: index.php?page=portal');
    exit();

} else {
    require_once(PATH_MODELS . 'CartDAO.php');
    $cartDAO = new CartDAO(DEBUG);
    $userId = $user->getIdUser();
    $cartPhp = $cartDAO->getCartByCustomerId($userId);

    // Si son panier est vide
    if ($cartPhp == null) {
        header('Location: index.php?page=cart');
        exit();
    }
    // Si l'utilisateur est connecté
    else {
        require_once(PATH_CONTROLLERS . 'cart.php');
    }
}

require_once(PATH_VIEWS . 'valider_commande.php');