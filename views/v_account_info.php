<div class="flex column">

    <div class="flex row wrap right contenu">

        <form action="index.php?page=account" method="post" class="card flex row center shadow rounded">
            <h2>Nom :</h2>
            <input type="text" name="nom" id="nom" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getLastName()?>">
        </form>

        <form action="index.php?page=account" method="post" class="card flex row center shadow rounded">
            <h2>Prénom :</h2>
            <input type="text" name="prenom" id="prenom" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getFirstName()?>">
        </form>

        <form action="index.php?page=account" method="post" class="card flex row center shadow rounded">
            <h2>Adresse mail :</h2>
            <input type="email" name="email" id="email" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getEmail()?>">
        </form>


    </div>

    <form class="flex row wrap right contenu center">
    <input type="button" id="form-info" value="<?= CHANGER_INFORMATIONS ?>" class="valid circle">
    
    <input type="button" id="form-mdp" value="<?= CHANGER_MDP ?>" class="valid circle">
    </form>

</div>