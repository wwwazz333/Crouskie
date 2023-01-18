// Pour gestion click souris
var listePlus = document.getElementsByClassName("ic-plus")
var listeMoins = document.getElementsByClassName("ic-moins")
var listePoubelle = document.getElementsByClassName("ic-poubelle")

// Pour modifier les valeurs
var listeQuantité = document.getElementsByClassName("quantite")
var listeQuantityHidden = document.getElementsByClassName("inp-quantity-hidden")
var listeNumHidden = document.getElementsByClassName("inp-num-hidden")

// Pour validé après modification
var listeSubmitForm = document.getElementsByClassName("form-quantity")

console.log(listePlus)

for(let i=0; i<listePlus.length; i++) {
    // Actions pour les icones +
    listePlus[i].addEventListener('click', function () {
        
        listeQuantité[i].innerHTML = parseInt(listeQuantité[i].innerHTML) + 1 // modifie la quantité dans le front
        listeQuantityHidden[i].value = listeQuantité[i].innerHTML // modifie la quantité dans le back
        listeNumHidden[i].value = i;// quelle produit dans la cart on modifie
        console.log("plus ligne " + i + " -> " + listeQuantityHidden[i].value)
        
        listeSubmitForm[i].click()
    })
    // Actions pour les icones -
    listeMoins[i].addEventListener('click', function () {
        if (parseInt(listeQuantité[i].innerHTML) > 0) { 
            listeQuantité[i].innerHTML = parseInt(listeQuantité[i].innerHTML) - 1 // modifie la quantité dans le front
            listeQuantityHidden[i].value = listeQuantité[i].innerHTML // modifie la quantité dans le back
            listeNumHidden[i].value = i;// quelle produit dans la cart on modifie
        }
        console.log("moins ligne " + i + " -> " + listeQuantityHidden[i].value)
        listeSubmitForm[i].click()
    })
    // Actions pour les icones poubelle
    listePoubelle[i].addEventListener('click', function () {
        listeQuantité[i].innerHTML = 0 // modifie la quantité dans le front
        listeQuantityHidden[i].value = listeQuantité[i].innerHTML // modifie la quantité dans le back
        listeNumHidden[i].value = i;// quelle produit dans la cart on modifie

        console.log("poubelle ligne " + i + " -> " + listeQuantityHidden[i].value)
        listeSubmitForm[i].click()
    })
}