<?php

// On vérifie si l'utilisateur n'est pas déjà connecté
if ($isLogged) {
    header('Location: index.php');
    exit();
}else{
    // On affiche les alertes possibles
    if (isset($_GET['log'])) {
        $alert = showAlert(2,"Attention","Vous devez être connecté(e) afin de poursuivre cette action !");
    }
}

// On vérifie si l'utilisateur à rentré son email dans le formulaire
if (isset($_POST['email'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');

    // On nettoie les champs sensibles
    $email = htmlspecialchars($_POST['email']);

    $DAO = new UtilisateurDAO(DEBUG);
    // On rédirige sur la page concernée
    if($DAO->isEmailExist($email)){
        header('Location: index.php?page=signin&email='.$email);
        exit();
    }else{
        header('Location: index.php?page=signup&email='.$email);
        exit();
    }
}

// Appel à la vue
require_once(PATH_VIEWS . $page . '.php');