<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>

<?php
    // echo "$cart";
?>

<div class="card center shadow rounded content">

    <h2>Mon panier</h2> 

    <div class='flex row legende'>
        <div class='flex row'>
            <p>Article</p>
        </div>
        <div class='flex row center'>
            <p>Couleur</p>
            <p>Taille</p>
        </div>
        <div class='flex row'>
            <p>Prix</p>
            <p>Quantité</p>
        </div>
    </div>
    <center><hr width="90%" color="#565656" size="0.5"></center>

    <!-- Affichage à régler  -->
    <?php foreach ($infosProdsCart as $product) { ?>

        <div class='flex row liste-panier'>
            <!-- Il faudra rajouter la taille et la couleur du vêtement commandé dans le panier -->
            <div class='flex row'>
                <p><?php $info = $product['NAMEPROD'] ; echo "$info"; ?></p>
            </div>
            <div class='flex row center'>
                <p>Jaune</p>
                <p>M</p>
            </div>
            <div class='flex row'>
                <p><?php $info = $product['PRICEPROD']; echo "$info"; ?> €</p>
                <p><?php $info = $product['QUANTITYCART']; echo "$info"; ?></p>
                <iconify-icon icon="mdi:cards-heart-outline"></iconify-icon> <!-- Icone pour supprimer -->
            </div>
        </div>
        <center><hr width="90%" color="#565656" size="0.5"></center>
        
    <?php } ?>

    <h3>Montant total à payer : <?php echo "$montantTotal"; ?> €</h3>

    <form class="flex row wrap right contenu center">
        <input type="button" id="form-info" value="<?= PASSER_COMMANDE ?>" class="valid circle">
        <input type="button" id="form-enregistrer" value="<?= VIDER_PANIER ?>" class="valid circle">
    </form>

</div>


<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>