<?php
if ($isLogged) {
    header("Content-Type: application/json");
    $data = array();
    $data["mail"]=$user->getEmail();
    $data["lastName"]=$user->getLastName();
    $data["firstName"]=$user->getFirstName();
    echo json_encode($data);
}else{
    header('Location: index.php?page=portal&log');
}