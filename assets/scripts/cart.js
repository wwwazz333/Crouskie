// Pour gestion click souris
var listePlus = document.getElementsByClassName("ic-plus")
var listeMoins = document.getElementsByClassName("ic-moins")
var listePoubelle = document.getElementsByClassName("ic-poubelle")

// Pour modifier les valeurs
var listeQuantité = document.getElementsByClassName("quantite")
var listeHidden = document.getElementsByClassName("inp-hidden")

// Pour validé après modification
var listeSubmitForm = document.getElementsByClassName("form-quantity")

console.log(listePlus)

for(let i=0; i<listePlus.length; i++) {
    // Actions pour les icones +
    listePlus[i].addEventListener('click', function () {
        listeQuantité[i].innerHTML = parseInt(listeQuantité[i].innerHTML) + 1
        listeHidden[i].value = listeQuantité[i].innerHTML
        console.log("plus ligne " + i + " -> " + listeHidden[i].value)
        
        listeSubmitForm[i].click()
    })
    // Actions pour les icones -
    listeMoins[i].addEventListener('click', function () {
        if (parseInt(listeQuantité[i].innerHTML) > 0) {
            listeQuantité[i].innerHTML = parseInt(listeQuantité[i].innerHTML) - 1
            listeHidden[i].value = listeQuantité[i].innerHTML
        }
        console.log("moins ligne " + i + " -> " + listeHidden[i].value)
        listeSubmitForm[i].click()
    })
    // Actions pour les icones poubelle
    listePoubelle[i].addEventListener('click', function () {
        listeQuantité[i].innerHTML = 0
        listeHidden[i].value = listeQuantité[i].innerHTML
        console.log("poubelle ligne " + i + " -> " + listeHidden[i].value)
        listeSubmitForm[i].click()
    })
}