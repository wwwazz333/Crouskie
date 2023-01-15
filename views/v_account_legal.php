<section id="legal-page" class="flex row wrap right contenu center legal">
	<div class="card flex column even shadow-small rounded">
		<h2 class="flex center"><?= PORTABILITE ?></h2>
		<p><?= EXPORTER_JSON ?></p>
		<a id="get_personal_data" class="flex button-download circle" href="index.php?page=data" download="personal_data"><?= TELECHARGER ?></a>
	</div>
	<div class="card flex column even shadow-small rounded">
		<h2 class="flex center"><?= MENTIONS_LEGALES ?></h2>
		<p><?= CONSULTER_MENTIONS ?></p>
		<a class="flex button-download circle" href="<?= PATH_DOCUMENTS ?>MentionsLegales_crouskie.pdf" download="MentionsLegales_crouskie.pdf"><?= TELECHARGER ?></a>
	</div>
	<div class="card flex column even shadow-small rounded">
		<h2 class="flex center"><?= CGU ?></h2>
		<p><?= CONSULTER_CGU ?></p>
		<a class="flex button-download circle" href="<?= PATH_DOCUMENTS ?>CGU_crouskie.pdf" download="CGU_crouskie.pdf"><?= TELECHARGER ?></a>
	</div>
</section>