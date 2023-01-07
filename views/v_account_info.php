<div class="flex column">

    <form id="account-form" action="index.php?page=account" method="post" >
        <div class="flex row wrap right contenu">
            <div class="card flex row center shadow-small rounded">
                <h2>Nom :</h2>
                <input type="text" name="nom" id="nom" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getLastName() ?>">
            </div>

            <div class="card flex row center shadow-small rounded">
                <h2>Prénom :</h2>
                <input type="text" name="prenom" id="prenom" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getFirstName() ?>">
            </div>

            <div class="card flex row center shadow-small rounded">
                <h2>Adresse mail :</h2>
                <input type="email" name="email" id="email" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getEmail() ?>">
            </div>

        </div>
        <div class="flex row wrap right contenu center">
            <input type="button" id="form-info" value="<?= CHANGER_INFORMATIONS ?>" class="valid circle">
            <input type="submit" id="form-enregistrer" value="<?= ENREGISTRER_INFORMATIONS ?>" class="valid circle">
            <a href="index.php?page=changepwd" id="mdp">
                <input type="button" id="form-mdp" value="<?= CHANGER_MDP ?>" class="valid circle">
            </a>
        </div>
    </form>


    <?php
    if (isset($page) && is_file(PATH_SCRIPTS . $page . ".js")) {
        echo ("<script src=\"" . PATH_SCRIPTS . $page . "_" . $selectedPage . ".js\"></script>");
    }
    ?>
</div>