<?php

/**
 * Permet d'afficher une alerte visuel à l'utilisateur
 * @param int $type Le type d'alerte ( 0 - Information , 1 - Succès , 2 - Warning , 3 - Erreur)
 * @param string $title Le titre de l'alerte
 * @param string $message Le message de l'alerte
 * @return array $alert les informations de l'alerte
 */
function showAlert(int $type, string $title, string $message)
{
    $alert = [];
    $alert[0] = $type;
    $alert[1] = $title;
    $alert[2] = $message;
    return $alert;
}
/**
 * Permet de serialiser les données de l'utilisateur au moment de la sauvegarde et de le sauvegarder.
 * @param string $first_name Le prénom de l'utilisateur
 * @param string $last_name Le nom de famille de l'utilisateur
 * @param string $email L'adresse email de l'utilisateur
 * @param int L'identifiant de l'utilisateur
 */
function serializeUser($first_name, $last_name, $mail_address, $idcustomer)
{
    $user = new User(
        $first_name,
        $last_name,
        $mail_address,
        $idcustomer
    );
    $_SESSION['account'] = serialize($user);
    return $user;
}
