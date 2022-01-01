const register = async (e) => {
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
            alert(result.statusText);
            location.href = '/';
        }
    }
}

const login = async (e) => {
    e.preventDefault();
    const form = e.target;
    const login = form.elements.login.value;
    const pass = form.elements.pass.value;

    const data = {username: login, password: pass};
    let result = await sendRequest("formLoginUser", "POST", data);
    if (result.ok) {
        alert('Úspěšně přihlášeno!');
        location.href = '/';
    } else {
        alert('Kombinace jména a hesla je neplatná!');
    }
}