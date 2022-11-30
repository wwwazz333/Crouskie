<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>
<!--  Début de la page -->
<div class="hero">
    <!-- alt dynamique à faire -->
    <img src="<?= PATH_IMAGES . 'backgrounds/background.jpg' ?>" alt="background">
    <div class='flex column'>
        <?php print_r($lastCollection); ?>
        <p><?=COLLECTION_DISCOVER?></p>
        <p>Crousk-hiver</p>
        <button class='rounded'><?=DISCOVER?></button>
    </div>


</div>
<!--  Fin de la page -->


<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>