<?php

class Color
{
    // Properties
    private int $_id;
    private string $_name;
    private string $_code;

    function __construct(int $id,string $name, string $code)
    {
        $this->_id = $id;
        $this->_name = $name;
        $this->_code = $code;
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

    public function getCode()
    {
        return $this->_code;
    }
}
