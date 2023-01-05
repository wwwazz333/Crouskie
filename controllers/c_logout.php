<?php
// On supprime la session
unset($_SESSION['account']);
// On redirige vers la page d'accueil
header("Location: index.php?log=0");
exit();