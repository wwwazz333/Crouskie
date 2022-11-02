<!DOCTYPE html>
<html>
	<head>
		<title><?= TITRE ?></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="Language" content="<?= LANG ?>"/>
		<meta name="viewport" content="width=device-width; initial-scale=1; maximum-scale=1; user-scalable=0"/> 
	</head> 
	<body>
		<!-- En-tête -->
		<header class="header flex row">
			<div class="flex row">
			<a href="index.php"><img src="Logocrouskie.svg" height="55px" width="230px" alt="<?=LOGO?>"></a>
			</div>
			<div class="flex row nav">
				<a href="index.php?page=collections">Collections</a>
				<a href="index.php?page=produits">Produits</a>
			</div>
			<div class="flex row">
				<!-- Bouton pour le panier -->
				<a href="index.php?page=panier"><img src="shopping-bag.svg" alt="<?=SHOPPINGBAG?>"></a> 
				<button>Connexion</button>
			</div>
		</header>
		<!-- Vue -->
		
