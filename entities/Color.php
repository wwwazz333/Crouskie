<?php

class Color
{
    // Properties
    private string $_name;
    private string $_code;

    function __construct(string $name, string $code)
    {
        $this->_name = $name;
        $this->_code = $code;
    }

    // getters

    public function getName()
    {
        return $this->_name;
    }

    public function getCode()
    {
        return $this->_code;
    }
}
