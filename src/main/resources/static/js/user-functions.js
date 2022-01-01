const register = async (e) => {
    e.preventDefault();     // TODO: zkontroluj, jesli heslo má správný tvar a jestli sedí podmínky
    const form = e.target;
    const login = form.elements.login.value;
    const pass = form.elements.pass.value;
    const passAgain = form.elements.passAgain.value;
    let warningText;
    if (pass != passAgain) {
        alert("Zadaná hesla nejsou stejná!");
    } else {
        const data = {username: login, password: pass};
        let result = await sendRequest("userRegistration", "POST", data);
        if (result.ok) {
            alert('Úspěšně zaregistrováno. Hurá!');
            location.href = '/';
        } else {
            if (result.status == 409) {
                warningText = "Uživatelské jméno je již zaregistrováno!";
            } else {
                warningText = result.statusText;
            }

            alert("Kód chyby: " + result.status + " - " + warningText);
        }
    }
}

const login = async (e) => {
    e.preventDefault();
    const form = e.target;
    const login = form.elements.login.value;
    const pass = form.elements.pass.value;

    const data = {username: login, password: pass};
    let result = await sendRequest("userLogin", "POST", data);
    if (result.ok) {
        alert('Úspěšně přihlášeno!');
        location.href = '/';
    } else {
        alert('Kombinace jména a hesla je neplatná!');
    }
}