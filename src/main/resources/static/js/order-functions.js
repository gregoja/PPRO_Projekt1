const submitOrderForValidation = async (e) =>{
    e.preventDefault();
    const form = e.target;

    const email = form.elements.email.value;
    const phonePrefix = form.elements.phonePrefix.value;
    const phoneNumber = form.elements.phoneNumber.value;
    const name = form.elements.name.value;
    const surname = form.elements.surname.value;
    const street = form.elements.street.value;
    const houseNumber = form.elements.houseNumber.value;
    const city = form.elements.city.value;
    const zipCode = form.elements.zipCode.value;
    const state = form.elements.state.value;
    const deliveryDetails = { email: email, phonePrefix: phonePrefix, phoneNumber: phoneNumber, name: name,
        surname: surname, street: street, houseNumber: houseNumber, city:city, zipCode:zipCode,
        state:state, };
    const completeData = {deliveryDetails,cart}
    let result = await sendRequest("validateDeliveryDetails","POST",completeData);
    if(result.ok) location.href = 'summary';
}

const submitOrder = async (e) =>{
    e.preventDefault();
    let result = await sendRequestWithoutData("completeOrder","POST");
    if(result.ok) {
        deleteCart();
        alert('úspěšně obědnáno. Hurá')
        location.href = '/';
    }
}