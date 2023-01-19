<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_VIEWS . 'alert.php');
?>

<div class="flex row content">

    <div class="flex column gauche">

        <!-- Informations du compte -->
        <div class="card center shadow rounded coordonnees">
            <h3><?= COMPTE_ASSOCIE?></h3> 
            <form action="index.php?page=account" method="post" class="card flex row center rounded infospersos">
                <div class="flex row">
                    <p><strong><?= $user->getFirstName() ?> <?= $user->getLastName() ?></strong></p>
                    <p><strong><?= $user->getEmail() ?></strong></p>
                </div>
                <input type="submit" id="form-confirm" value="<?= MODIFIER ?>" class="valid circle">
            </form>
        </div>

        <!-- L'utilisateur donne son adresse de livraison -->
        <div class="card center shadow rounded livraison">
            <h3><?= ADRESSE_LIVRAISON ?></h3> 
            <form action="index.php?page=valider_commande" method="post" class="card flex column center rounded">
                <div class="flex row">
                    <input type="text" name="firstname" id="firstname" placeholder="<?= LASTNAME ?>" class="icon rounded shadow-small" style="width: 45%" required>
                    <input type="text" name="lastname" id="lastname" placeholder="<?= FIRSTNAME ?>" class="icon rounded shadow-small" style="width: 45%" required>
                </div>
                <div class="flex row">
                    <input type="text" name="address" id="adress" placeholder="<?= ADRESSE ?>" class="icon rounded shadow-small" style="width: 65%" required>
                    <input type="text" name="postcode" id="postcode" placeholder="<?= CODE_POSTAL ?>" class="icon rounded shadow-small" style="width: 25%" required>
                </div>
                <div class="flex row">
                    <input type="text" name="city" id="city" placeholder="<?= VILLE ?>" class="icon rounded shadow-small" style="width: 40%" required>
                    <input type="text" name="country" id="country" placeholder="<?= PAYS ?>" class="icon rounded shadow-small" style="width: 50%" required>
                </div>
                <input type="hidden" name="action" value="valider">
                <input type="submit" id="form-confirm" value="<?= ENREGISTRER_PAYER ?>" class="valid circle">
                <!-- <input type="submit" id="form-confirm" value="Enregistrer" class="valid circle center" style="margin-top: 1.5rem; margin-bottom: 0rem"> -->
            </form>
        </div>

    </div>


    <!-- Récapitulatif de la commande -->
    <div class="commande card shadow rounded">
        <h3><?= COMMANDE?></h3> 
        <center><hr width="100%" color="#7F7F7F" size="0.4" style="margin: 1rem 0rem"></center>
              
        <!-- Affichage des produits du panier -->
        <div class="center scroll">
            <?php if(!$isCartEmpty && $isLogged) { ?>
                <?php foreach ($infosProdsCart as $product) { ?>
                    <div class="flex row produits">
                        <img class='rounded shadow-small' src="<?= PATH_IMAGES . 'uploads/product-preview-'. $product['idproduct'] .'.png' ?>" 
                        alt="image-produit" width="150rem" height="150rem">
                        <div class="flex column">
                            <p><strong><?php $info = $product['nameprod']; echo "$info"; ?></strong></p>
                            <p><?= CART_TAILLE?> : <?php $info = $product['size']; echo "$info"; ?></p>
                            <p><?= CART_COULEUR?> : <?php $info = $product['color']; echo "$info"; ?></p>
                            <p><?= CART_QUANTITE?> : <?php $info = $product['quantitycart']; echo "$info"; ?></p>
                            <p><strong><?php $info = $product['pricetotal']; echo "$info"; ?> €</strong></p>
                        </div>
                    </div>
                    <center><hr width="100%" color="#7F7F7F" size="0.4" style="margin: 1rem 0rem"></center>
                <?php } ?>
        </div>
        <!-- Total à payer -->
        <?php } ?>
            <div class="flex row btw" style="margin: 1rem 0rem 2rem 0rem"><h3><?= TOTAL_A_PAYER?></h3><h3><?php echo "$montantTotal"; ?> €</h3></div>
    </div>
</div>



<?php require_once(PATH_VIEWS . 'footer.php') ?>
