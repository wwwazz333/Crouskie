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
    <?php }else{ ?>
        <p>Malheureusement, Il n'y a aucun produit avec ces critères.</p>
    <?php }?>
</div>

<?php require_once(PATH_VIEWS . 'footer.php') ?>