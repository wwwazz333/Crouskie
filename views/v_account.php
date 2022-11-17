<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>

<!-- affichage menu -->
<div class="flex row content">
    <div class="flex column wrap right" id="menu">
        <h2> Bonjour <?= $user->getLastName() ?> </h2>
        <a href="index.php?page=account&selected=info">
            <p>Information du compte</p>
        </a>
        <a href="index.php?page=account">
            <p>Mes commandes</p>
        </a>
        <a href="index.php?page=account">
            <p>Mes favoris</p>
        </a>
    </div>


    <!-- require_once view $selectedpage -->
    <?php
    require_once(PATH_VIEWS . $page . "_" . $selectedPage . '.php');
    ?>
</div>
<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>