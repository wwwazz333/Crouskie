const passwordField = document.getElementById('password');
const passwordConfirmField = document.getElementById('confirm_password');

passwordConfirmField.addEventListener('input',(e)=>{
    if (passwordField.value != passwordConfirmField.value) {
        passwordConfirmField.setCustomValidity('Les mots de passes ne correspondent pas !');
    }else{
        passwordConfirmField.setCustomValidity('');
    }
})

document.querySelector('form').addEventListener('submit',(e)=>{
    if (passwordField.value != passwordConfirmField.value) {
        passwordConfirmField.setCustomValidity('Les mots de passes ne correspondent pas !');
        e.preventDefault();
    }
});