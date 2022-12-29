<div class="commandes shadow rounded">
    
    <!-- Affichage des commandes -->
    <?php foreach ($listeCommande as $c) { ?>
        <details>
            <summary>Commande du <?php $info = $c['date']; echo "$info"; ?> à <?php $info = $c['heure']; echo "$info"; ?></summary>
            Nom du pull - couleur - taille - prix : XX € - quantité : XX
        </details>

    <?php } ?>

</div>
