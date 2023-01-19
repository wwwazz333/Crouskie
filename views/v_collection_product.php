<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');

?>
<!--  Début de la page -->

<h1><?=  $collection->getName()?></h1>
<div class="flex row wrap center" id="products">
    <?php foreach ($products as $product) { ?>
        <a href="index.php?page=detail&id=<?= $product->getId() ?>">
                <div class='flex column product-item'>
                    <div class="image-container">
                        <img src="<?= PATH_IMAGES . 'uploads/product-preview-'. $product->getId() .'.png' ?>" 
                            alt="image-produit" loading="lazy" class="shadow-small">
                    </div>
                    <div class='flex column fill-w details'>
                        <p><?= $product->getName() ?></p>
                        <h2>€<?= $product->getPriceString() ?></h2>
                    </div>
                </div>
            </a>
    <?php } ?>
</div>

<?php require_once(PATH_VIEWS . 'footer.php') ?>