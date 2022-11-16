<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>

<!-- affichage menu -->

<div class="flex column wrap right" id="menu">
    <a href="index.php?page=account&selected=info"> <p>Information du compte</p> </a>
    <a href="index.php?page=account"> <p>Mes commandes</p> </a>
    <a href="index.php?page=account"> <p>Mes favoris</p> </a>
</div>


<!-- require_once view $selectedpage -->
<?php
require_once(PATH_VIEWS . $page . "_" . $selectedPage . '.php');
?>
<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>