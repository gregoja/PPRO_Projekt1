const login = async (e) => {
    e.preventDefault();
    let result = await sendRequestWithoutData("formRegisterUser","POST");
    if(result.ok) {
        alert('Úspěšně zaregistrováno. Hurá!');
        location.href = '/';
    } else {
        alert('Něco se pokazilo!');
        location.href = '/';
    }
}