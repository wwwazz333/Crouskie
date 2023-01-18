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
    $chgValide = false;

    if (isset($_POST['old-password'])){
        //$alert = showAlert(1,MAUVAIS_MDP,MAUVAIS_MDP);

        // Nettoyage des champs
        $oldPassword = htmlspecialchars($_POST['old-password']);
        $newPassword = htmlspecialchars($_POST['new-password']);
        $confNewPassword = htmlspecialchars($_POST['conf-new-password']);
        
        // On vérifie que l'ancien mot de passe entré est le bon
        $check = $userDAO->checkPassword($id,$oldPassword);
        if($check) {
            
            // On vérifie la confirmation du mot de passe
            if($newPassword == $confNewPassword) {
                $change = $userDAO->changePassword($id,$newPassword);
                $chgValide = true;
                // Retour à la page de compte
                header('Location: index.php?page=account');
                exit();
            } else {
                $alert = showAlert(3,ERROR,MAUVAIS_MDP);
            }
            
        } else {
           $alert = showAlert(3,ERROR,MAUVAIS_MDP);
        }

    }

}

require_once(PATH_VIEWS . $page . '.php');