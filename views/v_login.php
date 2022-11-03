<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
?>
<!--  Début de la page -->
<img src="<?=PATH_LOGOS . "crouskie-geant.png"?>" alt="crouskie géant" class="background">
<div class="flex column content">
    <div>
        <a href="index.php">
            <!-- Il faut trouver une solution pour le changement de couleur dynamique -->
            <img src=<?= PATH_LOGOS . "crouskie-text-outlined-red.svg" ?> height="120px" width="414px" alt="<?= LOGO ?>">
        </a>
    </div>
    <div class="flex column">
        <!-- Enregistrer les textes dans les langs -->
        <h3>Connexion / Inscription</h3>
        <p>Saisissez votre e-mail pour vous<b>connecter ou créer un compte</p>
    </div>
    <form class="card flex column shadow rounded">
        <input type="email" name="email" id="email" placeholder="E-mail" class="icon rounded shadow" data-icon="<?= PATH_ICONS . "email-outline.svg"?>">
    </form>
</div>

<!--  Fin de la page -->