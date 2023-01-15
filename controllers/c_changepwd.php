<?php

// Si l'utilisateur n'est pas connecté
if (!$isLogged) {
    header('Location: index.php?page=portal');
    exit();

} else {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');
    $userDAO = new UtilisateurDAO(DEBUG);
    $email = $user->getEmail();
    $id = $user->getIdUser();

    if (isset($_POST['old-password'])){
        //$alert = showAlert(1,MAUVAIS_MDP,MAUVAIS_MDP);

        // Nettoyage des champs
        $oldPassword = htmlspecialchars($_POST['old-password']);
        $newPassword = htmlspecialchars($_POST['new-password']);
        $confNewPassword = htmlspecialchars($_POST['conf-new-password']);
        
        // Retour à la page de compte
        header('Location: index.php?page=account');
        exit();
    }

}

require_once(PATH_VIEWS . $page . '.php');