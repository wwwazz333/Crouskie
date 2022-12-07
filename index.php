<?php
// Initialisation des paramètres du site
require_once('./config/configuration.php');
require_once(PATH_TEXTES.LANG.'.php');

// besoin de require avant de serialiser / deserialiser
require_once(PATH_ENTITY . 'User.php');
// Initialisation du système de session
session_name('crouskie');
session_start();

//vérification de la page demandée 
if(isset($_GET['page']))
{
  $page = htmlspecialchars($_GET['page']); // http://.../index.php?page=toto
  if(!is_file(PATH_CONTROLLERS.$_GET['page'].".php"))
  { 
    $page = '404'; //page demandée inexistante
  }
}
else{
  $page='accueil'; //page d'accueil du site - http://.../index.php
}

// Vérification connexion
// Ceci est temporaire !!
if (isset($_SESSION['account'])) {
  $isLogged = true;
  $user = unserialize($_SESSION['account']);
}else{
  $isLogged = false;
}

//appel du controller
require_once(PATH_CONTROLLERS.$page.'.php');
?>
