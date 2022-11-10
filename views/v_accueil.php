<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>
<!--  Début de la page -->
<div class="hero">
    <!-- alt dynamique à faire -->
    <img src="<?= PATH_IMAGES . 'backgrounds/background.jpg' ?>" alt="background">

    <p>Découvrez la nouvelle collection Crousk-hiver</p>
    <button class='rounded'>Découvrir</button>

</div>
<!--  Fin de la page -->


<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>