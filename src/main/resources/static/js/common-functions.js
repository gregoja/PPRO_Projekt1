// získá obsah košíku z local Storage
const getItemsFromCart = () => {
    let items = []
    const productJSON = localStorage.getItem('cart')
    if (productJSON != null) {
        items = JSON.parse(productJSON)
    }
    return items
}

const cart = getItemsFromCart();
// přidá jeden produkt s určitým množstvím do košíku
const addItemToCart = (newProduct) => {
    // nachází se už produkt se stejným id v košíku?
    const productIndex = cart.findIndex((singleItem) => {
        return parseInt(newProduct.id) === parseInt(singleItem.id)
    })
    // produkt se tam nachází? => smaž!
    if (productIndex > -1) {
        cart.splice(productIndex, 1);
    }
    // urceno ke smazani nebo spatny/malicious input od uzivatele
    if(newProduct.ammount < 1 ) {
        saveCart(cart);
        let sumOfProducts = 0;
        cart.forEach(item => {
            sumOfProducts += Number.parseInt(item.ammount);
        });
        localStorage.setItem("sum",sumOfProducts);
        createDOMInCart();
        return
    }
    cart.push(newProduct);

    let sumOfProducts = 0;
    cart.forEach(item => {
        sumOfProducts += Number.parseInt(item.ammount);
    });
    localStorage.setItem("sum",sumOfProducts);

    saveCart(cart);

    document.querySelector(".cartItems").classList.toggle('cartItemChanged');
    window.scroll({top: 0, left: 0, behavior: 'smooth' });
    setTimeout(() => updateTotalInCartDOM(), 600)
}

const deleteItemFromCart = (id) => {
    addItemToCart({
        id: id,
        ammount: 0
    });
    updateTotalInCartDOM();
}

const saveCart = (items) => {
    localStorage.setItem('cart', JSON.stringify(items))
}

// nastaví hodnotu košíku
const updateTotalInCartDOM = () => {
    let cartItems = localStorage.getItem("sum");
    if(cartItems == null) cartItems = 0;
    document.querySelector(".cartItems").innerText=cartItems;
}

const deleteCart = () => {
    localStorage.removeItem('cart');
    localStorage.removeItem('sum');
}

// po kliknutí otevírá responzivniho menu. Pokud JS nepodporován, tak se o to postará noscript
document.querySelector(".burger p").addEventListener("click", () => {
    document.querySelector('.menu_neviditelne').classList.toggle('menu_viditelne');
});

// malé zlepšení responzivní navigace. Zvětší plochu odkazu z textu na celý prvek v responzivním menu
let odkazy = document.querySelectorAll(".burger li");
for (let i = 0; i < odkazy.length; i++){
    odkazy[i].addEventListener('click', function (){
        window.location = this.querySelector('a').getAttribute('href');
    });
}
// custom metoda, která zapouzdřuje fetch, tak aby se to dalo příjemněji používat
const sendRequest = async (endpoint,method,data,headers = { 'Content-Type': 'application/json',}) => {
    let response = await fetch(endpoint,{method: method, headers: headers, body: JSON.stringify(data)});
    return response;
    //return response.json();
}
// custom metoda, která zapouzdřuje fetch, tak aby se to dalo příjemněji používat. Obsahuje token
const sendRequestAuth = async (endpoint,method,data,token) => {
    let responseJSON = await sendRequest(endpoint,method,data,{
        Accept: 'application/json',
             'Content-Type': 'application/json',
             'Authorization': "Bearer " + token,
        })
    return responseJSON;
}

const sendRequestWithoutData = async (endpoint,method,headers = { 'Content-Type': 'application/json',}) => {
    let response = await fetch(endpoint,{method: method, headers: headers});
    return response;
    //return response.json();
}

const sendRequestWithoutDataAuth = async (endpoint,method,token) => {
    let responseJSON = await sendRequestWithoutData(endpoint,method,{
        Accept: 'application/json',
             'Content-Type': 'application/json',
             'Authorization': "Bearer " + token,
        })
    return responseJSON;
}

updateTotalInCartDOM();

const logoutUser = async () => {
    let result = await sendRequestWithoutData("logout", "POST");
    if (result.ok) {
        Swal.fire({
            icon: "success",
            title: "Byly jste odhlášeni",
        }).then(function () {
            location.reload();
        })
    } else {
        Swal.fire({
            icon: "info",
            title: "Jejda, něco se pokazilo",
        })
    }
}

const userLogout = document.querySelector("#buttonLogout");
if(userLogout) userLogout.addEventListener("click", logoutUser);

