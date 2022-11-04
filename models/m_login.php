<?php
require_once(PATH_MODELS . 'UtilisateurDAO.php');

function checkMail(string $email) : bool
{
    try {
        $DAO = new UtilisateurDAO(DEBUG);
        return $DAO->isEmailExist($email);
    } catch (Exception $ex) {
        return false;
    }
}