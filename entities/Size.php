<?php
/**
 * Cette classe représente la taille d'un produit
 */
class Size
{
    // ----------- Properties -------------

    private int $_id;
    private string $_name;

    /**
     * Constructeur de la classe Size
     * @param int $id L'identifiant de la taille
     * @param string $name Le nom de la taille
     */
    function __construct(int $id, string $name)
    {
        $this->_id = $id;
        $this->_name = $name;
    }

    // ----------- Getters -------------

    /**
     * Retourne l'identifiant de la taille
     * @return int Identifiant de la taille
     */
    public function getId() : int {
        return $this->_id;
    }

    /**
     * Retourne le nom de la taille
     * @return string Le nom de la taille
     */
    public function getName() : string {
        return $this->_name;
    }
}
