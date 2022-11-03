<!DOCTYPE html>
<html>
	<head>
		<title><?= TITRE ?></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Language" content="<?= LANG ?>"/>
		<meta name="viewport" content="width=device-width; initial-scale=1; maximum-scale=1; user-scalable=0"/>
		<link rel="stylesheet" href="<?= PATH_CSS ?>main.css">
	</head> 
	<body>
		<!-- En-tête -->
		<!-- Version classique et non transparente à faire !-->
		<header class="header flex row shadow">
			<div class="flex row">
				<a href="index.php">
					<!-- Il faut trouver une solution pour le changement de couleur dynamique -->
					<img src=<?= PATH_IMAGES."icons/Logocrouskie.svg"?> height="50px" width="200px" alt="<?=LOGO?>">
				</a>
			</div>
			<div class="flex row nav">
				<a href="index.php?page=collections">Collections</a>
				<a href="index.php?page=produits">Produits</a>
			</div>
			<div class="flex row">
				<!-- Bouton pour le panier -->
				<a href="index.php?page=panier">
					<img src=<?= PATH_IMAGES."/icons/shopping-bag.svg"?> alt="<?=SHOPPINGBAG?>">
				</a> 
				<button class="outlined rounded">Connexion</button>
			</div>
		</header>
		<!-- Vue -->
		
