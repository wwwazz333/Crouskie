<?php

class Size
{
    // properties

    private int $_id;
    private string $_name;

    function __construct(int $id, string $name)
    {
        $this->_id = $id;
        $this->_name = $name;
    }

    // getters

    public function getId()
    {
        return $this->_id;
    }

    public function getName()
    {
        return $this->_name;
    }
}
