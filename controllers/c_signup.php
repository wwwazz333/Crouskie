<?php

// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['email'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');
    $DAO = new UtilisateurDAO(DEBUG);
    $res = $DAO->addUser($_POST['email'],
        password_hash($_POST['password'],PASSWORD_DEFAULT),
        $_POST['firstname'],
        $_POST['lastname']
    );
    header('Location: index.php');
    exit();
}

// On vérifie que l'email est bien présente dans l'URL avant d'afficher la page
if (isset($_GET['email']) == false) {
    // Dans le cas ou elle n'existe pas on redirige vers le portail de connexion
    header('Location: index.php?page=portal');
    exit();
}

// Dans le cas contraire on affiche la vue
require_once(PATH_VIEWS . $page . '.php');