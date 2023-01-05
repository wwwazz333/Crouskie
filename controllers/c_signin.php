<?php

// On regarde si l'utilisateur est déjà connecté
if ($isLogged) {
    header('Location: index.php');
    exit();
}

// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['email'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');
    
    // Nettoyage des champs
    $email = htmlspecialchars($_POST['email']);
    $password = htmlspecialchars($_POST['password']);

    $DAO = new UtilisateurDAO(DEBUG);
    $data = $DAO->getUser($email);
    if ($data) {
        if(password_verify($password,$data['password'])){
            $user = new User(
                $data["first_name"],
                $data["last_name"],
                $data["mail_address"],
                $data["idcustomer"],
            );
            $_SESSION['account'] = serialize($user);
            // redirection accueil temporaire
            header('Location: index.php?log=1');
            exit();
        }else{
            // Mauvais mot de passe
            header("Location: index.php?page=signin&email=$email&fail");
            exit();
        }
    }else{
        header('Location: index.php?page=portal');
        exit();
    }    
}

// On vérifie que l'email est bien présente dans l'URL avant d'afficher la page de connexion
if (isset($_GET['email'])) { 
    $email = htmlspecialchars($_GET['email']); 
}

// Dans le cas contraire on le redirige vers la page portal pour lui redemander son email
if (!isset($email)) {
    header('Location: index.php?page=portal');
    exit();
}

// On vérifie si l'utilisateur s'est trompé de mot de passe
if (isset($_GET['fail'])) {
    $alert = showAlert(3,CONNEXION,MAUVAIS_MDP);
}

//On affiche la page de connexion
require_once(PATH_VIEWS . $page . '.php');