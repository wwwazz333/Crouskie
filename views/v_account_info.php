<div class="flex column wrap right info">

    <form action="index.php?page=account" method="post" class="card flex column center shadow rounded">
        <h2>Nom :</h2>
        <input type="text" name="nom" id="nom" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getLastName()?>">
    </form>

    <form action="index.php?page=account" method="post" class="card flex column center shadow rounded">
        <h2>Prénom :</h2>
        <input type="text" name="prenom" id="prenom" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getFirstName()?>">
    </form>

    <form action="index.php?page=account" method="post" class="card flex column center shadow rounded">
        <h2>Adresse mail :</h2>
        <input type="email" name="email" id="email" class="icon rounded shadow-small" disabled="disabled" value="<?= $user->getEmail()?>">
    </form>


    <a href="index.php" class ="rounded">
        <h2>Changer son mot de passe</h2>
    </a>
</div>

