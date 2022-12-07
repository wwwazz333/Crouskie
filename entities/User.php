<?php

/*
Représentation objet d'un utilisateur du site web
*/
class User{
    // Propriétés d'un utilisateur
    private string $_first_name;
    private string $_last_name;
    private string $_email;
    private int $_id;

    // Je pense faire d'autres classes pour :
    // Commands
    // Favorites

    /*
    Constructeur de l'utilisateur
    */
    function __construct(string $first_name, string $last_name, string $email, $id){
        $this -> _first_name = $first_name;
        $this -> _last_name = $last_name;
        $this -> _email = $email;
        $this -> _id = $id;
    }

    /*============ Getters ============*/

    //Obtenir le prénom
    public function getFirstName()
    {
        return $this->_first_name;
    }

    //Obtenir le nom de famille
    public function getLastName()
    {
        return $this->_last_name;
    }
    
    //Obtenir l'email
    public function getEmail()
    {
        return $this->_email;
    }

    //Obtenir l'ID
    public function getIdUser()
    {
        return $this->_id;
    }
}
?>