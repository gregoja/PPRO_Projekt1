const createDOMProduct = (product) => {
    console.log(product);
    const productElement = document.createElement("div");
    productElement.setAttribute("class","card col-sm-12 col-md-6 col-xl-4");
    productElement.setAttribute("style","width: 18rem");

    const productSpan = document.createElement("span");
    productSpan.setAttribute("class","badge badge-pill badge-danger")
    // Pokud je v databázi tag, tak se vykreslý. Pokud tam není, tak se vykreslý NULLový spam, ale jako neviditelný. Důležité kvůli vzhledu. Zatracené CSS :(
    if(product.tagName){
        productSpan.setAttribute("style",`background-color: ${product.tagColor}`);
        productSpan.textContent = product.tagName;
    }else{
        productSpan.setAttribute("style",`visibility:hidden`);
        productSpan.textContent = "NULL";
    }
    productElement.appendChild(productSpan);

    const productImage = document.createElement("img");
    productImage.setAttribute("class","card-img-top");
    productImage.setAttribute("src",`img/product/${product.pictureUrl}`);
    productImage.setAttribute("span","productPicture");
    productElement.appendChild(productImage);

    const textDiv = document.createElement("div");
    textDiv.setAttribute("class","card-body");
        // uvnirt textDiv, který ma za cíl seskupit části productElementu, které jsou pod obrázkem
        const textHeadline = document.createElement("h5");
        textHeadline.setAttribute("class","card-title");
        textHeadline.textContent = product.name;
        textDiv.appendChild(textHeadline);

        const textParagraph = document.createElement("p");
        textParagraph.setAttribute("class","card-text");
        textParagraph.textContent = `${product.price} Kč`;
        textDiv.appendChild(textParagraph);

        const textButtonLink = document.createElement("a");
        textButtonLink.setAttribute("class","btn btn-primary zmensit");
        textButtonLink.setAttribute("href",`product?productId=${product.productId}`);
        textButtonLink.textContent = "Zobrazit";
        textDiv.appendChild(textButtonLink);

    productElement.appendChild(textDiv);

    return productElement
}

const fetchProducts = async () => {
    console.log("jsem tady")
    try{
        let products = await sendRequestWithoutData("/products","GET")
        products = await products.json()
        const productsDiv = document.querySelector('.productList section')
        productsDiv.innerHTML = ''
        products.forEach( (product) => { productsDiv.appendChild(createDOMProduct(product)) })
    }catch(e){
        console.log(error)
    }
}