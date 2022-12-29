<?php

// Informations du compte: info
require_once(PATH_MODELS . 'UtilisateurDAO.php');

// Créer une variable $selectedPage
if (isset($_GET['selected'])) {
    $selectedPage = $_GET['selected'];
} else {
    $selectedPage = "info";
}

// Page commandes
require_once(PATH_MODELS . 'CommandeDAO.php');

// Vue
require_once(PATH_VIEWS . $page . '.php');
