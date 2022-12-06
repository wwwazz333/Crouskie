const form_info = document.getElementById("form-info")
const form_enregistrer = document.getElementById("form-enregistrer")




form_info.addEventListener("click", function(){
    form_info.style.display = "none"
    form_enregistrer.style.display = "block"
})

form_enregistrer.addEventListener("click", function(){
    form_info.style.display = "none"
    form_enregistrer.style.display = "block"
})