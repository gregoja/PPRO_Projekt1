/*REVIEWS*/
const submitReviewPost = async (e) =>{
    e.preventDefault();
    const form = e.target;

    const text = form.elements.text.value;
    const stars = form.elements.stars.value;
    const data = { text: text, stars: stars, productId: productId };
    let result = await sendRequest("saveReview","POST",data);
    if(result.code == 11000){
        Swal.fire({
            icon: "info",
            title: "Chyba",
            text: "Jeden uživatel může mít pouze jednu recenzi k jednomu produktu!"
        })
    }else if(result.errors){
        Swal.fire({
            icon: "info",
            title: "Recenze nebyla přidána",
            text: "Upravte svou recenzi a odešlete ji znovu"
        })
    }else{
        Swal.fire({
            icon: "success",
            title: "Recenze úspěšně přidána",
        })
        .then(function() {
            location.href = `product?productId=${productId}`
        })
    }
}

const deleteReviewPost = async (e) => {
    e.preventDefault();
    const data  = productId;
    let result = await sendRequest("deleteReview","POST",data);
    if (result.ok) {
        Swal.fire({
            icon: "success",
            title: "Recenze úspěšně odebrána",
        }).then(function() {
            location.href = `product?productId=${productId}`
        })
    } else {
        Swal.fire({
            icon: "info",
            title: "Jejda, něco se pokazilo",
        })
    }
}