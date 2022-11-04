<?php
require_once(PATH_MODELS . 'UtilisateurDAO.php');

function checkMail(string $email) : bool
{
    $DAO = new UtilisateurDAO(DEBUG);
    return $DAO->isEmailExist($email);
}