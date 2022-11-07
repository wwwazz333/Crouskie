<?php
//  En tête de page
require_once(PATH_VIEWS . 'head.php');
require_once(PATH_VIEWS . 'header.php');
?>
<!--  Début de la page -->
<div class="flex row center search">
    <input type="text" name="search" id="input-search" class="rounded shadow-small" placeholder="Rechercher...">
</div>
<div class="flex row wrap products">

</div>
<!--  Fin de la page -->


<!--  Pied de page -->
<?php require_once(PATH_VIEWS.'footer.php')?>