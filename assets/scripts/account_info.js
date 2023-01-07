/*---------------Page Info--------------- */

// Recuperer les elements
const form_info = document.getElementById("form-info");
const form_enregistrer = document.getElementById("form-enregistrer");

const nom = document.getElementById("nom");
const prenom = document.getElementById("prenom");
const email = document.getElementById("email");


// Changement du texte des boutons
form_info.addEventListener("click", function(){
    form_info.style.display = "none";
    form_enregistrer.style.display = "block";
    nom.removeAttribute('disabled');
    prenom.removeAttribute('disabled');
    email.removeAttribute('disabled');
    nom.classList.add('post-it');
    prenom.classList.add('post-it');
    email.classList.add('post-it');
})
form_enregistrer.addEventListener("click", function(){
    form_info.style.display = "block";
    form_enregistrer.style.display = "none";
    nom.setAttribute("disabled","disabled");
    prenom.setAttribute("disabled","disabled");
    email.setAttribute("disabled","disabled");
    nom.classList.remove('post-it');
    prenom.classList.remove('post-it');
    email.classList.remove('post-it');
})