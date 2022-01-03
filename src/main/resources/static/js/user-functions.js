const register = async (e) => {
    e.preventDefault();
    const form = e.target;
    const login = form.elements.login.value;
    const pass = form.elements.pass.value;
    const passAgain = form.elements.passAgain.value;
    let warningText;
    if (pass != passAgain) {
        Swal.fire({
            icon: "info",
            title: "Zadaná hesla nejsou stejná!",
        })
    } else {
        const data = {username: login, password: pass};
        let result = await sendRequest("userRegistration", "POST", data);
        if (result.ok) {
            Swal.fire({
                icon: "success",
                title: "Registrace byla úspěšná!",
            }).then(function () {
                location.href = `login`
            })
        } else {
            if (result.status == 409) {
                warningText = "Uživatelské jméno je již zaregistrováno!";
            } else {
                warningText = result.statusText;
            }

            Swal.fire({
                icon: "info",
                title: "Kód chyby: " + result.status + " - " + warningText,
            })
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
        Swal.fire({
            icon: "success",
            title: "Úspěšně přihlášeno!",
        }).then(function () {
            location.href = `/`
        })
    } else {
        Swal.fire({
            icon: "info",
            title: "Kombinace jména a hesla je neplatná!",
        })
    }
}