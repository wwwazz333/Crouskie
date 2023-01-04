<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');

?>
<!--  Début de la page -->
<div class="flex row center search">
    <input type="text" name="search" id="input-search" class="rounded shadow-small" 
    placeholder="<?=RECHERCHER?>" value="<?= isset($_GET['q']) ? $_GET['q'] : ''?>">
</div>
<div class="flex row wrap center" id="products">
    <?php if(count($products) > 0) { ?>
        <?php foreach ($products as $product) { ?>
            <a href="index.php?page=detail&id=<?= $product->getId() ?>">
                <div class='flex column product-item'>
                    <div class="image-container">
                        <img src="<?= PATH_IMAGES . 'backgrounds/background.jpg' ?>" alt="background">
                    </div>
                    <div class='flex column fill-w details'>
                        <p><?= $product->getName() ?></p>
                        <h2>€<?= $product->getPriceString() ?></h2>
                    </div>
                </div>
            </a>
        <?php } ?>
    <?php }else{ ?>
        <p>Malheureusement, Il n'y a aucun produit avec ces critères.</p>
    <?php }?>
</div>

<?php require_once(PATH_VIEWS . 'footer.php') ?>