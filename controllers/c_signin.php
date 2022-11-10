<?php

// On regarde si l'utilisateur est déjà connecté
if ($isLogged) {
    header('Location: index.php');
    exit();
}

// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['email'])) {
    
}


// On vérifie que l'email est bien présente dans l'URL avant d'afficher la page
if (isset($_GET['email'])) {
    require_once(PATH_VIEWS . $page . '.php');
}else{
    // Dans le cas contraire en le redirige vers la page d'accueil
    header('Location: index.php?page=portal');
    exit();
}