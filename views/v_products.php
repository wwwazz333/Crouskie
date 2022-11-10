<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>
<!--  Début de la page -->
<div class="flex row center search">
    <input type="text" name="search" id="input-search" class="rounded shadow-small" placeholder="Rechercher...">
</div>
<div class="flex row wrap products">
    <div class='flex column rounded' style='height:fit-content;'>
        <img class='rounded' src="<?= PATH_IMAGES . 'backgrounds/background.jpg' ?>" alt="background">
        <div class='flex row' style='padding-top:10px;background-color:transparent;'>
            <div class='flex column'>
                <p>Crouskie Hoodie</p>
                <p>45€</p>
            </div>
            <iconify-icon class='circle'  icon="akar-icons:shopping-bag"></iconify-icon>
        </div>
    </div>
</div>
<!--  Fin de la page -->


<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>