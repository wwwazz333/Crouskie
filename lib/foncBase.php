<?php
/**
 * Permet d'afficher une alerte visuel à l'utilisateur
 * @param int $type Le type d'alerte ( 0 - Information , 1 - Succès , 2 - Warning , 3 - Erreur)
 * @param string $title Le titre de l'alerte
 * @param string $message Le message de l'alerte
 */
function showAlert(int $type, string $title, string $message)
{
    $alert = [];
    $alert[0] = $type;
    $alert[1] = $title;
    $alert[2] = $message;
    return $alert;
}