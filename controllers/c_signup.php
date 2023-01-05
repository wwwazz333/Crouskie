<?php
// On regarde si il s'agit d'une tentative de connexion
if (isset($_POST['email'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');
    $DAO = new UtilisateurDAO(DEBUG);

    $email = $_POST['email'];
    $pass = $_POST['password'];
    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
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
            $user = new User(
                $data["first_name"],
                $data["last_name"],
                $data["mail_address"],
                $data["idcustomer"],
            );
            $_SESSION['account'] = serialize($user);
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
    $alert = showAlert(3,"Erreur","Une erreur est survenue lors de la création de votre compte !");
}

// Dans le cas contraire on affiche la vue
require_once(PATH_VIEWS . $page . '.php');