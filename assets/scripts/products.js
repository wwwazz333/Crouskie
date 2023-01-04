const input_search = document.getElementById('input-search');
var search_timeout = null;
input_search.addEventListener('input',(e)=>{
    if (search_timeout) {clearTimeout(search_timeout);}    
    search_timeout = setTimeout(()=>{
        if (input_search.value != "") {
            // A finir
            postJson('api.php',{"action":"search","name":input_search.value},(res)=>console.log(res));
        }
    },500);
});
input_search.addEventListener('keydown',(e)=>{
    if (e.key == 'Enter') {
        window.location = input_search.value ? 
            `index.php?page=products&q=${input_search.value}` :
            'index.php?page=products'
    }
});