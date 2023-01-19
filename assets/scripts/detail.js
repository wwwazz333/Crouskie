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
    colorsContainer.removeAttribute('disabled');
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
    sizesContainer.removeAttribute('disabled');
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
        sizesContainer.setAttribute('disabled',true);
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
                    PopUp(3,"Erreur","Une erreur de requête est survenue, excusez nous du dérangement causé !");
                }
            });
        } 
    });
});

// Tailles
const sizes = sizesContainer.querySelectorAll("input"); 
sizes.forEach((el)=>{
    el.addEventListener("click",()=>{
        colorsContainer.setAttribute('disabled',true);
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
                    PopUp(3,"Erreur","Une erreur de requête est survenue, excusez nous du dérangement causé !");
                }
            });
        }
    });
});

form.addEventListener('submit',(ev)=>{
    if (!selectedColor || !selectedSize) {
        PopUp(2,"Attention","Veuillez choisir une couleur / taille pour votre produit !");
        ev.preventDefault();
    }
});

// Carousel

const carousel = document.getElementById('carousel');
const imageWrapper = carousel.querySelector('.image-wrapper');
const before = carousel.querySelector('.before');
const next = carousel.querySelector('.next');
const images = carousel.querySelectorAll('img');
var scrollPos = 0;

before.addEventListener('click',()=>{
    if (imageWrapper.scrollLeft == 0) {
        imageWrapper.scrollLeft = imageWrapper.scrollWidth
    }else{
        imageWrapper.scrollLeft -= imageWrapper.offsetWidth;
    }
});

next.addEventListener('click',()=>{
    if (Math.round(imageWrapper.scrollLeft) == (imageWrapper.scrollWidth - imageWrapper.offsetWidth)) {
        imageWrapper.scrollLeft = 0;
    }else{
        imageWrapper.scrollLeft += imageWrapper.offsetWidth;
    }
});