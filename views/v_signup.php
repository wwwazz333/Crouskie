<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'alert.php');
?>
<!--  Début de la page -->
<img src="<?=PATH_LOGOS . "crouskie-geant.png"?>" alt="<?= CROUKIE_GEANT ?>" class="background">
<div class="flex column content center">
    <div class="logo">
        <a href="index.php">
            <!-- Il faut trouver une solution pour le changement de couleur dynamique -->
            <img src=<?= PATH_LOGOS . "crouskie-text-outlined-red.svg" ?> height="120px" width="414px" alt="<?= LOGO ?>">
        </a>
    </div>
    <div class="flex column center instruction">
        <!-- Enregistrer les textes dans les langs -->
        <h3><?=INSCRIPTION?></h3>
        <p><?=INSTRUCTIONS_SIGNUP?></p>
    </div>
    <form action="index.php?page=signup" method="post" class="card flex column center shadow rounded">
        <input type="hidden" name="email" id="email" value="<?= $_GET['email']?>">
        <a href="index.php?page=portal"><?=$_GET['email']?><iconify-icon icon="material-symbols:edit"></iconify-icon></a>
        <input type="password" name="password" id="password" placeholder="<?=CREATE_PASSWORD?>" class="icon rounded shadow-small">
        <input type="password" name="confirm_password" id="confirm_password" placeholder="<?=PASSWORD_CONFIRM?>" class="icon rounded shadow-small">
        <div class="flex row">
            <input type="text" name="firstname" id="firstname" placeholder="<?=FIRSTNAME?>" class="icon rounded shadow-small">
            <input type="text" name="lastname" id="lastname" placeholder="<?=LASTNAME?>" class="icon rounded shadow-small">
        </div>
        <input type="submit" id="form-confirm" value="<?= INSCRIPTION ?>" class="valid circle">
    </form>
    <a href="#" class="underlined"><?=GERER_COOKIES?></a>
</div>