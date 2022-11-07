	<body>
		<!-- En-tête -->
		<!-- Version classique et non transparente à faire !-->
		<!-- Il faudra une condition pour vérifier la page demandant le header et changer si c'est accueil -->
		<header class="header flex row transparent">
			<div>
				<a href="index.php">
					<!-- Il faut trouver une solution pour le changement de couleur dynamique -->
					<img src=<?= PATH_LOGOS . "crouskie-text-filled-white.svg" ?> height="50px" width="200px" alt="<?= LOGO ?>">
				</a>
			</div>
			<div class="flex row nav">
				<!-- utiliser les balises ul et li -->
				<a href="index.php?page=collections">Collections</a>
				<a href="index.php?page=produits">Produits</a>
			</div>
			<!-- classe right temporaire le temps de trouver un nom / convention de noms pour le css -->
			<div class="flex row right">
				<!-- Bouton pour le panier -->
				<a href="index.php?page=panier" class="icon counter" data-number=0>
					<!-- Couleur dynamique à faire !-->
					
					<iconify-icon icon="akar-icons:shopping-bag"></iconify-icon>
				</a>
				<button onclick="window.location.assign('index.php?page=login');" class="rounded">Connexion</button>
			</div>
		</header>
		<!-- Vue -->