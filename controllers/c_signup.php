<?php
// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['email'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');
    $DAO = new UtilisateurDAO(DEBUG);

    // Nettoyage des champs
    $email = htmlspecialchars($_POST['email']);
    $pass = htmlspecialchars($_POST['password']);
    $firstname = htmlspecialchars($_POST['firstname']);
    $lastname = htmlspecialchars($_POST['lastname']);
    
    if($DAO->addUser(
        $email,
        password_hash($pass,PASSWORD_DEFAULT),
        $firstname,
        $lastname
    )){
        // Le compte a été créé
        // On récupère les informations manquante pour effectuer la connexion
        $data = $DAO->getUser($email);
        if ($data) {
            serializeUser($data["first_name"],$data["last_name"],$data["mail_address"],$data["idcustomer"]);
        }else{
            // Erreur de connexion
        }
        // redirection vers la page d'accueil
        header('Location: index.php?acc=1');
    }else{
        // Une erreur est survenue
        header("Location: index.php?page=signup&email=$email&acc=0");
    }
    exit();
}

// On vérifie que l'email est bien présente dans l'URL avant d'afficher la page
if (isset($_GET['email']) == false) {
    // Dans le cas ou elle n'existe pas on redirige vers le portail de connexion
    header('Location: index.php?page=portal');
    exit();
}

// Afficher une alerte si la création du compte a échouée
if (isset($_GET['acc'])) {
    $alert = showAlert(3,ERROR,FAIL_CREATE_ACCOUNT);
}

// Dans le cas contraire on affiche la vue
require_once(PATH_VIEWS . $page . '.php');