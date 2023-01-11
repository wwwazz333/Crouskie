<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_VIEWS . 'alert.php');
?>

<!-- affichage menu -->
<div class="flex row content">
    <div class="flex column wrap right" id="menu">
        <h2> Bonjour <?= $user->getFirstName() ?> </h2>
        <a href="index.php?page=account&selected=info">
            <p id="info">Informations du compte</p>
        </a>
        <a href="index.php?page=account&selected=commande">
            <p id="commande">Mes commandes</p>
        </a>
        <a href="index.php?page=account&selected=legal">
            <p id="legal">Legal</p>
        </a>
        <a href="index.php?page=logout&selected=info">
            <p>Se déconnecter</p>
        </a>
    </div>

    <!-- Changement de couleur dans le menu indiquant la page -->
    <script>
        // transforme la variable selectedPage PHP en variable page JavaScript
        var page = <?php echo json_encode($selectedPage); ?>;
        // console.log(page)
        const textePage = document.getElementById(page)
        textePage.style.color = "#E10512"
        textePage.style.fontWeight = "bold";
    </script>

    <!-- require_once view $selectedpage -->
    <?php
    require_once(PATH_VIEWS . $page . "_" . $selectedPage . '.php');
    ?>
</div>

<?php require_once(PATH_VIEWS . 'footer.php') ?>