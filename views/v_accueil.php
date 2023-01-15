<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
require_once(PATH_VIEWS . 'alert.php');
?>
<!--  Début de la page -->
<div class="hero">
    <!-- alt dynamique à faire -->
    <img src="<?= $lastCollection->getPathPicture() ?>" alt="background">
    <div class='flex column'>
        <p><?=COLLECTION_DISCOVER?></p>
        <p>Crousk-hiver</p>
        <a href='index.php?page=collection_product&id=<?= $lastCollection->getCollectionId() ?>' class='rounded'><?=DISCOVER?></a>
    </div>
</div>

<?php require_once(PATH_VIEWS . 'footer.php') ?>