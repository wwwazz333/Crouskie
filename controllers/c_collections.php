<?php
require_once(PATH_MODELS . 'CollectionDAO.php');

// Récupération des collections
$DAO = new CollectionDAO(DEBUG);
$collections = $DAO->getCollections();

require_once(PATH_VIEWS . $page . '.php');