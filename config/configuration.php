<?php
 
const DEBUG = true; // production : false; dev : true

// Accès base de données

const BD_HOST = 'menardbediant.fr:8002';
const BD_DBNAME = 'crouskie';
const BD_USER = 'root';
const BD_PWD = 'thesaurus-cranberry-reptile';

// Décommentez les lignes du dessous si vous voulez utiliser la base local de votre PC

// const BD_HOST = 'localhost';
// const BD_DBNAME = 'crouskie';
// const BD_USER = 'root';
// const BD_PWD = '';


// Langue du site
const LANG ='ES-es';
// const LANG = 'EN-en';

// Paramètres du site : nom de l'auteur ou des auteurs
const AUTEUR = ''; 

//dossiers racines du site
define('PATH_CONTROLLERS','./controllers/c_');
define('PATH_ENTITY','./entities/');
define('PATH_ASSETS','./assets/');
define('PATH_LIB','./lib/');
define('PATH_MODELS','./models/');
define('PATH_VIEWS','./views/v_');
define('PATH_TEXTES','./languages/');

//sous dossiers
define('PATH_CSS', PATH_ASSETS.'css/');
define('PATH_SCRIPTS', PATH_ASSETS.'scripts/');

define('PATH_IMAGES', PATH_ASSETS.'images/');
define('PATH_LOGOS', PATH_IMAGES.'logos/');
define('PATH_ICONS', PATH_IMAGES.'icons/');
define('PATH_BACKGROUNDS', PATH_IMAGES.'backgrounds/');

// fichiers