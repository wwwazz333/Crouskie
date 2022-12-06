<?php
require_once(PATH_MODELS . 'CollectionDAO.php');

$DAO = new CollectionDAO(DEBUG);
$collections = $DAO->getCollections();

require_once(PATH_VIEWS . $page . '.php');