<?php

//Informations du compte: info
require_once(PATH_MODELS . 'UtilisateurDAO.php');

//créer une variable $selectedPage
if (isset($_GET['selected'])) {
    $selectedPage = $_GET['selected'];
} else {
    $selectedPage = "info";
}

if ($selectedPage == "info") {
    $DAO = new UtilisateurDAO(DEBUG);
    $data = $DAO->getUser($email);
}

require_once(PATH_VIEWS . $page . '.php');
