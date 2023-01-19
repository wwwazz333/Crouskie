<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_VIEWS . 'alert.php');

?>
<div class="flex row content">
    <!-- Carousel pour visualiser les images -->
    <div id="carousel" class="rounded shadow-small">
        <iconify-icon class="nav before circle shadow-small" icon="material-symbols:navigate-before"></iconify-icon>
        <iconify-icon class="nav next circle shadow-small" icon="material-symbols:navigate-next"></iconify-icon>
        <div class="image-wrapper flex row">
            <?php foreach ($images as $image) { ?>
                <img src="<?=$image['pathpicture']?>" alt="<?=$image['altpicture']?>" srcset="">
            <?php } ?>
        </div>
    </div>
    <form action="" method="POST" class="flex column center card rounded shadow-small" data-id="<?= $product->getId()?>">
        <h2><?= $product->getName()?></h2>
        <p><?= $product->getDescription()?></p>
        <!-- Section des couleurs -->
        <!-- 
            Exemple : 
            <input type="radio" value="value1" name="color" class="circle shadow-small" style="background-color:#ff0000;" required>
            <input type="radio" value="value2" name="color" class="circle shadow-small" style="background-color:#00ff00;" required>
        -->
        <div class="flex column center fill-w" id="colors-container">
            <p><?=COULEUR?></p>
            <div class="scrollable-fieldset">
                <fieldset id="color" class="flex row">
                    <?php foreach ($colors as $color) { ?>
                        <!-- TODO : ajouter l'ID de la couleur pour pouvoir la transmettre au panier -->
                        <input type="radio" value="<?= $color->getName() ?>" title="<?= $color->getName() ?>" name="color" class="circle shadow-small" style="background-color:<?= $color->getCode() ?>;" required>
                    <?php } ?>
                </fieldset>
            </div>
        </div>
        <!-- Section des tailles -->
        <!-- 
            Exemple :
            <input type="radio" value="M" name="size" class="rounded shadow-small" data-content="M" required>
            <input type="radio" value="L" name="size" class="rounded shadow-small" data-content="L" required>
         -->
        
        <div class="flex column center fill-w" id="sizes-container">
            <p><?=TAILLE?></p>
            <div class="scrollable-fieldset">
                <fieldset id="size" class="flex row" required>
                    <?php foreach ($sizes as $size) { ?>
                        <input type="radio" value="<?= $size->getId() ?>" name="size" class="rounded shadow-small" data-content="<?= $size->getName() ?>" required>
                    <?php } ?>
                </fieldset>
            </div>
        </div>
        <!-- Section prix -->
        <div class="flex row even center fill-w price-container">
            <h2 id="price"><?= $product->getPrice()?>€</h2>
            <button id="submit" class="circle" type="submit"><?=AJOUTER_PANIER?></button>
        </div>
    </form>
</div>

<?php
require_once(PATH_VIEWS . 'footer.php');
?>