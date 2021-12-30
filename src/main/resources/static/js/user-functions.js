const login = async (e) => {
    e.preventDefault();
    const form = e.target;
    const login = form.elements.login.value;
    const data = {login: login};
    let result = await sendRequest("formRegisterUser","POST", data);
    if(result.ok) {
        alert('Úspěšně zaregistrováno. Hurá!');
        location.href = '/';
    } else {
        alert('Něco se pokazilo!');
        location.href = '/';
    }
}