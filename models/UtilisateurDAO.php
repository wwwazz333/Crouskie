<?php
require_once(PATH_MODELS . 'DAO.php');
class UtilisateurDAO extends DAO
{
    public function getUser($email){
        $result = $this->queryRow("SELECT * FROM customer WHERE mail_address = ?",array($email));
        return $result;
    }

    public function isEmailExist(string $email)
    {
        $result = $this->getUser($email);
        return $result ? true : false;
    }

    public function addUser(string $email, string $password,string $firstName,string $lastName){
        $result = $this->queryBdd("INSERT INTO customer (first_name,last_name,mail_address,username,password) VALUES (?,?,?,?)",array(
            $lastName,$lastName,$firstName,$password
        ));
        return $result;
    }
}