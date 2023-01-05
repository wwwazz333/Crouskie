<?php

if ($isLogged) {
    header('Location: index.php');
    exit();
}else{
    if (isset($_GET['log'])) {
        $alert = showAlert(2,"Attention","Vous devez être connecté(e) afin de poursuivre cette action !");
    }
}

if (isset($_POST['email'])) {
    require_once(PATH_MODELS . 'UtilisateurDAO.php');

    $email = htmlspecialchars($_POST['email']);

    $DAO = new UtilisateurDAO(DEBUG);
    if($DAO->isEmailExist($email)){
        header('Location: index.php?page=signin&email='.$email);
        exit();
    }else{
        header('Location: index.php?page=signup&email='.$email);
        exit();
    }

}

require_once(PATH_VIEWS . $page . '.php');