<?php
require_once(PATH_MODELS . 'CollectionDAO.php');
$DAO = new CollectionDAO(DEBUG);
$lastCollection = $DAO->getLastCollection();

require_once(PATH_VIEWS . $page . '.php');