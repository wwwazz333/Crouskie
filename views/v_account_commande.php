<div class="commandes shadow rounded" id="commandes-pas-vide">

    <!-- Affichage des commandes -->
    <?php if(!$isCommandesEmpty) { ?>
        <?php foreach ($listeCommande as $c) { ?>
            <details>
                <summary>Commande du <?php $info = $c['date']; echo "$info"; ?> à <?php $info = $c['heure']; echo "$info"; ?></summary>
                
                <!-- Affichage des produit de la commande traitée -->
                <?php foreach ($listeProductBought as $pb) { ?>

                    <?php if($pb['order'] == $c['numorder']) { ?>

                        <?php $info = $pb['name']; echo "$info"; ?> - 
                        couleur : <?php $info = $pb['color']; echo "$info"; ?> - 
                        taile : <?php $info = $pb['size']; echo "$info"; ?> - 
                        prix : <?php $info = $pb['price']; echo "$info"; ?> € - 
                        quantité : <?php $info = $pb['quantity']; echo "$info"; ?>
                        <br>
                    <?php } ?>
                <?php } ?>

            </details>
        <?php } ?>
    <?php } ?>
</div>

<div class="ccommandes shadow rounded" id="commandes-vide">
    <h1><?= COMMANDES_VIDE ?></h1>
</div>

<!-- Affichage selon si l'utilisateur a déjà fait des commandes ou non -->
<script>
        // transforme la variable isCommandesEmpty PHP en variable page JavaScript
        var estVide = <?php echo json_encode($isCommandesEmpty); ?>;
        const divCommandes = document.getElementById("commandes-pas-vide")
        const divCommandesVide = document.getElementById("commandes-vide")
        // gestion de l'affichage de la bonne div
        if (estVide) {
            divCommandes.style.display = "none"
            divCommandesVide.style.display = "block"
        } else {
            divCommandes.style.display = "block"
            divCommandesVide.style.display = "none"
        }
</script>


