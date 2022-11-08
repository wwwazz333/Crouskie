/*
    Projet SAE
    Crouskie WEB
    https://forge.univ-lyon1.fr/p2100126/crouskie-sa

    Elliot CASNA, Anne PASSELEGUE, Thomas WARRIER, Marc BERIOT, Virgile MENARD-BEDIANT
*/
// Premier formulaire
const form = document.querySelector('form');
const form_email = document.getElementById('email');
const form_submit = form.querySelector('input[type=submit]');


function checkForm(event) {
    postJson('api.php',{"action":"check","email":form_email.value},(res)=>{
        if (res.success) {
            
        }else{
            // Erreur requete api / Afficher message erreur
        }
    });
    event.preventDefault();
    return false
}
