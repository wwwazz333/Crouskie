<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');

?>
<!--  Début de la page -->
<div class="flex column center" id="collections">
    <?php foreach ($collections as $collection) { ?>
        <a href="index.php?page=collection_produit&id=<?= $collection->getCollectionId() ?>">
            <div class="collection-content">
                <img class="rounded" src="<?= $collection->getPathPicture() ?>" alt="background">
                <p><?= $collection->getName() ?></p>
            </div>
        </a>
    <?php } ?>
</div>
<!--  Fin de la page -->


<!--  Pied de page -->
<?php require_once(PATH_VIEWS . 'footer.php') ?>