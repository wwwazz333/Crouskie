	<body>
		<!-- En-tête -->
		<header class="header flex row <?= $page == 'accueil' ? 'transparent' : 'shadow'?>">
			<div>
				<a href="index.php">
					<img src=<?= $page == 'accueil' ? 
					PATH_LOGOS . "crouskie-text-filled-white.svg" : 
					PATH_LOGOS .  "crouskie-text-outlined-red.svg"?> 
					height="50px" width="200px" alt="<?= LOGO ?>">
				</a>
			</div>
			<div class="flex row nav">
				<!-- utiliser les balises ul et li -->
				<a href="index.php?page=collections"><?= MENU_COLLECTIONS ?></a>
				<a href="index.php?page=products"><?= MENU_PRODUITS ?></a>
			</div>
			<div class="flex row right">
				<!-- Bouton pour le panier -->
				<!-- Ajouter la class 'counter' pour afficher le nombre d'articles dans le panier -->
				<a href="index.php?page=panier" class="icon" data-number=0>
					<iconify-icon icon="akar-icons:shopping-bag"></iconify-icon>
				</a>
				<button onclick="window.location.assign('index.php?page=login');" class="rounded"><?= CONNEXION ?></button>
			</div>
		</header>
		<!-- Vue -->