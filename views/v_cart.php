<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>

<?php
    // echo "$cart";
?>

<div class="card center shadow rounded content">

    <h3>Mon panier</h3> 
    <center><hr width="80%" color="black" size="0.5"></center>

    <!-- Affichage à régler  -->
    <?php foreach ($infosProdsCart as $product) { ?>
        
            <div class='flex column rounded shadow product-item'>
                <div class='flex column center'>
                    <!-- Il faudra rajouter la taille et la couleur du vêtement commandé dans le panier -->
                    <p>Nom : <?php $info = $product['NAMEPROD'] ; echo "$info"; ?></p>
                    <p>Quantité : <?php $info = $product['QUANTITYCART']; echo "$info"; ?></p>
                    <p>Prix : <?php $info = $product['PRICEPROD']; echo "$info"; ?> €</p>
                    <p>Prix total : <?php $info = $product['PRICETOTAL']; echo "$info"; ?> €</p>
                </div>
            </div>
        
    <?php } ?>

    <h2>Montant total à payer : <?php echo "$montantTotal"; ?> €</h2>

</div>


<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>