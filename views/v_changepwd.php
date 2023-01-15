<?php

//  En tête de page
require_once(PATH_VIEWS . 'head.php');
?>
<div class="flex column content center">
    <div class="logo">
        <a href="index.php">
            <!-- Il faut trouver une solution pour le changement de couleur dynamique -->
            <img src=<?= PATH_LOGOS . "crouskie-text-outlined-red.svg" ?> height="120px" width="414px" alt="<?= LOGO ?>">
        </a>
    </div>
    <div class="flex column center instruction">
        <!-- Enregistrer les textes dans les langs -->
        <h3><?= CONNEXION ?></h3>
        <p><?= CHANGER_MDP_TXT ?></p>
    </div>
    <form action="index.php?page=changepwd" method="POST" class="card flex column center shadow rounded">
        
        <input type="password" name="old-password" id="old-password" placeholder="<?= MDP_ACTUEL ?>" class="icon rounded shadow-small" required>
        <input type="password" name="new-password" id="new-password" placeholder="<?= MDP_NOUVEAU ?>" class="icon rounded shadow-small" required>
        <input type="password" name="conf-new-password" id="conf-new-password" placeholder="<?= MDP_NOUVEAU_CONF ?>" class="icon rounded shadow-small" required>
        
        <input type="submit" id="form-confirm" value="<?= VALIDER ?>" class="valid circle">
    </form>
</div>