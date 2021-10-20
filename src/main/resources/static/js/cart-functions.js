const createDOMItem = (item) => {
    const itemElement = document.createElement("div");
    itemElement.setAttribute("class","ShoppingCartItem");

    const itemImg = document.createElement("img");
    itemImg.setAttribute("src",`img/product/${item.pictureUrl}`);
    itemElement.appendChild(itemImg);

    const itemLink = document.createElement("a");
    itemLink.setAttribute("href",`/product?productId=${item.id}`);
    itemLink.setAttribute("class","kosikLink");
        const itemH3 = document.createElement("h3");
        itemH3.innerText = item.name;
        itemLink.appendChild(itemH3);
    itemElement.appendChild(itemLink)

    const itemParagraph = document.createElement("p");
    itemParagraph.setAttribute("class","krizek");
    itemParagraph.addEventListener("click",() => deleteItemFromCart(item.id));
    itemParagraph.innerText = "×";
    itemElement.appendChild(itemParagraph);

    const itemQuanityInput = document.createElement("input");
    itemQuanityInput.setAttribute("type","number");
    itemQuanityInput.setAttribute("class","shoppingCartInput");
    itemQuanityInput.setAttribute("name","pocet");
    itemQuanityInput.setAttribute("min","1");
    itemQuanityInput.setAttribute("value",item.amount);
    itemQuanityInput.setAttribute("placeholder","kusů");
    itemQuanityInput.addEventListener("change",() => {
        addItemToCart({id:item.id, ammount: itemQuanityInput.value})
        itemQuanityInput.nextElementSibling.innerText =
            `cena: ${Math.round(itemQuanityInput.value * item.price*100)/100} Kč`;
    });
    itemElement.appendChild(itemQuanityInput);

    const itemPriceDiv = document.createElement("div");
    itemPriceDiv.setAttribute("class","cena cenaCelek");
    itemPriceDiv.setAttribute("style","color: red");
    itemPriceDiv.innerText=`cena: ${Math.round(item.amount * item.price*100)/100}Kč`;
    itemElement.appendChild(itemPriceDiv);

    const itemCleanerDiv = document.createElement("div");
    itemCleanerDiv.setAttribute("class","cleaner");
    itemElement.appendChild(itemCleanerDiv);

    const itemHr = document.createElement("hr");
    itemElement.appendChild(itemHr);


    return itemElement
}
const createDOMInCart = async () => {
    const itemsFromCart = getItemsFromCart();

    const cartList = document.querySelector('.cartList')
    cartList.innerHTML = ''
    if(itemsFromCart.length !== 0){
        for (const [key, value] of Object.entries(itemsFromCart)) {
            const itemWithInfo = await sendRequestWithoutData(`/products?productId=${value.id}`,"GET");
            Object.assign(itemWithInfo, {amount : value.ammount});
            cartList.appendChild(createDOMItem(itemWithInfo))
        }
    }else{
        document.querySelector(".pokracovat a").classList.add("d-none");
        document.querySelector("#donutWorry").classList.remove("d-md-none");
    }

}