// Recuperer les elements
const form_info = document.getElementById("form-info")
const form_enregistrer = document.getElementById("form-enregistrer")

const nom = document.getElementById("nom") 
const prenom = document.getElementById("prenom")
const email = document.getElementById("email")


// Changement du texte des boutons
form_info.addEventListener("click", function(){
    form_info.style.display = "none"
    form_enregistrer.style.display = "block"
    nom.removeAttribute('disabled')
    prenom.removeAttribute('disabled')
    email.removeAttribute('disabled')
})
form_enregistrer.addEventListener("click", function(){
    form_info.style.display = "block"
    form_enregistrer.style.display = "none"
    nom.setAttribute("disabled","disabled")
    prenom.setAttribute("disabled","disabled")
    email.setAttribute("disabled","disabled")
    alert('Vos informations ont bien été enregistrées')
})