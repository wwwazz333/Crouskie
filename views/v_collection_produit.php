<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');

?>
<!--  Début de la page -->

<div class="flex row wrap center" id="products">
    <?php foreach ($products as $product) { ?>
        <a href="index.php?page=detail&id=<?= $product->getId() ?>">
            <div class='flex column rounded shadow-small product-item'>
                <div class="image-container">
                    <img class='rounded shadow-small' src="<?= PATH_IMAGES . 'backgrounds/background.jpg' ?>" alt="background">
                    <div class="circle icon-button">
                        <iconify-icon icon="mdi:cards-heart-outline"></iconify-icon>
                    </div>
                </div>

                <div class='flex column center details'>
                    <p><?= $product->getName() ?></p>
                    <p><?= $product->getPrice() ?>€</p>
                </div>
            </div>
        </a>
    <?php } ?>
</div>
<!--  Fin de la page -->


<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>