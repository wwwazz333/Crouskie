const input_search = document.getElementById('input-search');
const suggestionsContainer = document.querySelector('div.search > ul');
var search_timeout = null;

var suggestions = [];

input_search.addEventListener('input',(e)=>{
    if (search_timeout) {clearTimeout(search_timeout);}    
    search_timeout = setTimeout(()=>{
        if (input_search.value != "") {
            postJson('api.php',{"action":"search","name":input_search.value},(res)=>{
                if (res.success = true) {
                    suggestions = res.result;
                    displaySuggestions();
                }
            });
        }else{
            suggestions = [];
        }
        displaySuggestions();
    },200);
});

input_search.addEventListener('keydown',(e)=>{
    if (e.key == 'Enter') {
        window.location = input_search.value ? 
            `index.php?page=products&q=${input_search.value}` :
            'index.php?page=products'
    }
});

function displaySuggestions() {
    suggestionsContainer.innerHTML = '';
    if (suggestions.length > 0) {
        input_search.classList.add('suggestions');
        suggestionsContainer.classList.remove('hidden');
        suggestions.forEach(suggestion => {
            const suggestionElement = document.createElement('li');
            suggestionElement.innerText = suggestion.nameprod;
            suggestionElement.addEventListener('click',(_)=>{
                window.location.href = `index.php?page=detail&id=${suggestion.idprod}`;
            })
            suggestionsContainer.appendChild(suggestionElement);
        });
    }else{
        input_search.classList.remove('suggestions');
        suggestionsContainer.classList.add('hidden');
    }
}

// Sort and filter part

const nameSorterButton = document.getElementById('name-sorter');
const priceSorterButton = document.getElementById('price-sorter');
const productsParent = document.getElementById('products');
const products = productsParent.querySelectorAll('.product-item');
var nameSorter = 'asc';
var priceSorter = 'asc';

function sortByName(order) {
    const sortedProducts = _.sortBy(products, (product) => {
        return product.querySelector('p').textContent;
    });
    if (order === 'desc') {
        sortedProducts.reverse();
    }
    productsParent.innerHTML = "";
    sortedProducts.forEach(product => {
        productsParent.appendChild(product);
    });
}

function sortByPrice(order) {
    const sortedProducts = _.sortBy(products, (product) => {
        return parseFloat(product.querySelector('h2').textContent.replace('€',''));
    });
    if (order === 'desc') {
        sortedProducts.reverse();
    }
    productsParent.innerHTML = "";
    sortedProducts.forEach(product => {
        productsParent.appendChild(product);
    });
}

nameSorterButton.addEventListener('click',()=>{
    sortByName(nameSorter);
    if (nameSorter == 'asc') {
        nameSorter = 'desc';
    }else{
        nameSorter = 'asc';
    }
});

priceSorterButton.addEventListener('click',()=>{
    sortByPrice(priceSorter);
    if (priceSorter == 'asc') {
        priceSorter = 'desc';
    }else{
        priceSorter = 'asc';
    }
});

