const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('productId');
let userReview = null;

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
const formReview = document.querySelector("form[action=\"saveReview\"]");
if(formReview) formReview.addEventListener("submit",submitReviewPost);

const deleteReview = document.querySelector("input[name=\"deleteReview\"]");
if(deleteReview) deleteReview.addEventListener("click", deleteReviewPost);
