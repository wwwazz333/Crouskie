// DOM Elements

const form = document.querySelector("form");
const colorsContainer = document.getElementById("color");
const sizesContainer = document.getElementById("size");
const submit = document.getElementById("submit");
const productID = form.dataset.id;

var selectedColor = null;
var selectedSize = null;

var SizesInStock = null;
var ColorsInStock = null;

function refreshForm() {

    // Actualiser les couleurs en stock
    for(const color of colorsContainer.children){
        if (selectedSize) {
            if (ColorsInStock) {
                ColorsInStock.includes(color.value) ? 
                color.removeAttribute('disabled') : 
                color.setAttribute('disabled',true);
            }
        }else{
            color.removeAttribute('disabled');
        } 
    }

    // Actualiser les tailles en stock
    for(const size of sizesContainer.children){
        if (selectedColor) {
            if (SizesInStock) {
                SizesInStock.includes(parseInt(size.value)) ? 
                size.removeAttribute('disabled') :
                size.setAttribute('disabled',true);
            }  
        }else{
            size.removeAttribute('disabled');
        }
    }
}


// Couleurs
const colors = colorsContainer.querySelectorAll("input")
colors.forEach((el)=>{
    el.addEventListener("click",()=>{
        if (selectedColor == el.value) {
            el.checked = false;
            selectedColor = null;
            refreshForm();
        }else{
            selectedColor = el.value;
            postJson('api.php',{"action":"stock","id":productID,"color":selectedColor},(res)=>{
                if (res.success) {
                    SizesInStock = res.result;
                    refreshForm();
                }else{
                    console.log("Erreur de requête !");
                    //afficher message erreur
                }
            });
        } 
    });
});

// Tailles
const sizes = sizesContainer.querySelectorAll("input"); 
sizes.forEach((el)=>{
    el.addEventListener("click",()=>{
        if (selectedSize == el.value) {
            el.checked = false;
            selectedSize = null;
            refreshForm();
        }else{
            selectedSize = el.value;
            postJson('api.php',{"action":"stock","id":productID,"size":selectedSize},(res)=>{
                if (res.success) {
                    ColorsInStock = res.result;
                    refreshForm();
                }else{
                    console.log("Erreur de requête !");
                    //afficher message erreur
                }
            });
        }
    });
});

form.addEventListener('submit',(ev)=>{
    if (selectedColor && selectedSize) {
        postJson('api.php',{"action":"cart","id":productID,"size":selectedSize},(res)=>{
            if (res.success) {
                ColorsInStock = res.result;
                refreshForm();
            }else{
                console.log("Erreur de requête !");
                //afficher message erreur
            }
        });
    }
    ev.preventDefault();
});
