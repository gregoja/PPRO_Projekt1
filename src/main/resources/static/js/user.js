const userRegister = document.querySelector("form[action=\"formRegisterUser\"]");
if(userRegister) userRegister.addEventListener("submit",register);

const userLogin = document.querySelector("form[action=\"formLoginUser\"]");
if(userLogin) userLogin.addEventListener("submit",login);