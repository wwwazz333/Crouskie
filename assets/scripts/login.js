/*
    Projet SAE
    Crouskie WEB
    https://forge.univ-lyon1.fr/p2100126/crouskie-sa

    Elliot CASNA, Anne PASSELEGUE, Thomas WARRIER, Marc BERIOT, Virgile MENARD-BEDIANT
*/

const form = document.querySelector('form');
const form_email = document.getElementById('email');
const form_submit = form.querySelector('input[type=submit]');


function checkForm(event) {
    // Check e-mail from API
    if (form_email.value == 'elliot.casna@gmail.com') {
        console.log(form_email.value);
        return true;
    }else{
        event.preventDefault();
    }
    return false
}
