const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('productId');
let userReview = null;
/*if(token){
    document.querySelector(".reviewsDivWarning").classList.add("d-none");
    document.querySelector(".reviewsDiv").classList.remove("d-none");
}*/

const form = document.querySelector("#formAddToCart");
document.querySelector("#koupit").addEventListener("click", () => {
    const newProduct = {
        id: form.elements.productId.value,
        ammount: form.elements.ammount.value
    }
    addItemToCart(newProduct);
});

// jednoduchá funkce pro obrazku aktualizaci hvezdicek ke komentari.
document.querySelector("select").addEventListener("change",function(){
    let value = this.value;
    document.querySelector(".hvezdy").src="img/hvezdy_"+value+".png";
});

/*REVIEWS*/
/*if(token){
    fetchReviews();
}
const formReview = document.querySelector("#formReview");
formReview.addEventListener("submit",submitReviewPost);*/
