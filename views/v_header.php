<!DOCTYPE html>
<html>
	<head>
		<title><?= TITRE ?></title>
		<link rel="shortcut icon" href="<?= PATH_IMAGES?>favicon.ico" type="image/x-icon">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Language" content="<?= LANG ?>"/>
		<meta name="viewport" content="width=device-width; initial-scale=1; maximum-scale=1; user-scalable=0"/>
		<link rel="stylesheet" href="<?= PATH_CSS ?>main.css">

		<!-- Ajout css dynamique -->
		<?php
		if (isset($page) && is_file(PATH_CSS.$page."css")) {
			echo("<link rel=\"stylesheet\" href=\"".PATH_CSS.$page.".css\">");
		}
		?>
		
	</head> 
	<body>
		<!-- En-tête -->
		<!-- Version classique et non transparente à faire !-->
		<!-- Il faudra une condition pour vérifier la page demandant le header et changer si c'est accueil -->
		<header class="header flex row transparent">
			<div class="flex row">
				<a href="index.php">
					<!-- Il faut trouver une solution pour le changement de couleur dynamique -->
					<img src=<?= PATH_LOGOS."crouskie-text-filled-white.svg"?> height="50px" width="200px" alt="<?=LOGO?>">
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
					<img src=<?= PATH_ICONS."white-shopping-bag.svg"?> alt="<?=SHOPPINGBAG?>">
				</a> 
				<button class="rounded">Connexion</button>
			</div>
		</header>
		<!-- Vue -->
		
