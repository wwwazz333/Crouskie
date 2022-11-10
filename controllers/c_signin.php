<?php

// On regarde si l'utilisateur est déjà connecté
if ($isLogged) {
    header('Location: index.php');
    exit();
}

// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['password'])) {
    $password = $_POST['password'];
    $email = $_GET['email'];
    $utilisateurDAO = new UtilisateurDAO(DEBUG);
    if($password == $utilisateurDAO->getPassword($email)){
        $isLogged==true;
    }
}


// On vérifie que l'email est bien présente dans l'URL avant d'afficher la page
if (isset($_GET['email'])) {
    //On affiche la page
    require_once(PATH_VIEWS . $page . '.php');
}else{
    // Dans le cas contraire on le redirige vers la page portal pour lui redemander son email
    header('Location: index.php?page=portal');
    exit();
}