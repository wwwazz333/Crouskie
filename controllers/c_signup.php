<?php

// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['email'])) {
    
}


// On vérifie que l'email est bien présente dans l'URL avant d'afficher la page
if (isset($_GET['email'])) {
    // Dans ce cas on affiche la page
    require_once(PATH_VIEWS . $page . '.php');
}else{
    // Dans le cas contraire on le redirige vers la page portal pour lui redemander son email
    header('Location: index.php?page=portal');
    exit();
}