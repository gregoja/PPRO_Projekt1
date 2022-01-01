const userRegister = document.querySelector("form[action=\"userRegistration\"]");
if(userRegister) userRegister.addEventListener("submit",register);

const userLogin = document.querySelector("form[action=\"userLogin\"]");
if(userLogin) userLogin.addEventListener("submit",login);