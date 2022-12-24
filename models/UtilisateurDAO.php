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
        $result = $this->queryBdd("INSERT INTO customer (first_name,last_name,mail_address,password) VALUES (?,?,?,?)",array(
            $firstName,$lastName,$email,$password
        ));
        return $result;
    }

    public function changeUserInfos(string $firstName,string $lastName, string $email, int $id){
        $result = $this->queryBdd("UPDATE customer SET firt_name = ?, last_name = ?, mail_address = ? where idcustomer = ?",array(
            $firstName,$lastName,$email,$id
        ));
        return $result;
    }
}