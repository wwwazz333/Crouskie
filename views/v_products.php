<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');

?>
<!--  Début de la page -->
<div class="flex column center">
    <div class="search">
        <input type="text" name="search" id="input-search" class="rounded shadow-small" autocomplete="off" 
        placeholder="<?=RECHERCHER?>" value="<?= isset($_GET['q']) ? $_GET['q'] : ''?>">
        <ul class="hidden"></ul>
    </div>
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
        <p><?=NO_PRODUCTS?></p>
    <?php }?>
</div>

<?php require_once(PATH_VIEWS . 'footer.php') ?>