<?php
require_once(PATH_MODELS . 'DAO.php');
class UtilisateurDAO extends DAO
{
    public function isEmailExist(string $email)
    {
        $result = $this->queryRow("SELECT count(*) FROM customer WHERE mail_address = ?",array($email));
        return $result[0] == 1 ? true : false;
    }

}