const login = async (e) => {
    e.preventDefault();     // TODO: zkontroluj, jesli heslo má správný tvar a jestli sedí podmínky
    const form = e.target;
    const login = form.elements.login.value;
    const pass = form.elements.pass.value;
    const passAgain = form.elements.passAgain.value;
    if (pass != passAgain) {
        alert("Zadaná hesla nejsou stejná!");
    } else {
        const data = {userId: 0, username: login, password: pass, administrator: 0, registrationTimestamp: null};
        let result = await sendRequest("formRegisterUser", "POST", data);
        if (result.ok) {
            alert('Úspěšně zaregistrováno. Hurá!');
            location.href = '/';
        } else {
            alert('Něco se pokazilo!');
            location.href = '/';
        }
    }
}