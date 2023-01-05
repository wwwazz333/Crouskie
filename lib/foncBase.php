<?php

function showAlert(int $type, string $title, string $message)
{
    $alert = [];
    $alert[0] = $type;
    $alert[1] = $title;
    $alert[2] = $message;
}

