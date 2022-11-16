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
    $nom = $user->getLastName();
    echo "<h1>$nom</h1>";
}

require_once(PATH_VIEWS . $page . '.php');
