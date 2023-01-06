<?php
/**
 * Cette classe représente une couleur d'un produit
 */
class Color
{
    // ----------- Properties -------------

    private string $_name;
    private string $_code;

    /**
     * Constructeur de la classe Color
     * @param string $name le nom de la couleur
     * @param string $code le code informatique de la couleur (hex "#ffffff" ou bien "rgb(0,0,0)")
     */
    function __construct(string $name, string $code)
    {
        $this->_name = $name;
        $this->_code = $code;
    }

    // ----------- Getters -------------

    /**
     * Retourne le nom de la couleur
     * @return string le nom de la couleur
     */
    public function getName() : string {
        return $this->_name;
    }

    /**
     * Retourn le code de la couleur
     * @return string Le code de la couleur
     */
    public function getCode() : string {
        return $this->_code;
    }
}
