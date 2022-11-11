<?php

// On regarde si l'utilisateur est déjà connecté
if ($isLogged) {
    header('Location: index.php');
    exit();
}

// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['password'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');

    $password = $_POST['password'];
    $email = $_POST['email'];
    $DAO = new UtilisateurDAO(DEBUG);

    $data = $DAO->getUser($email);
    if ($data) {
        if(password_verify($password,$data['password'])){
            $user = new User(
                $data["FIRST_NAME"],
                $data["LAST_NAME"],
                $data["MAIL_ADDRESS"],
            );
            $_SESSION['account'] = serialize($user);
            // redirection accueil temporaire
            header('Location: index.php');
            exit();
        }else{
            // Mot de passe incorrect
        }
    }else{
        // identifiant introuvable
    }    
}

// On vérifie que l'email est bien présente dans l'URL avant d'afficher la page de connexion
if (isset($_GET['email'])) {
    //On affiche la page
    require_once(PATH_VIEWS . $page . '.php');
}else{
    // Dans le cas contraire on le redirige vers la page portal pour lui redemander son email
    header('Location: index.php?page=portal');
    exit();
}