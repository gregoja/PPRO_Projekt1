/*REVIEWS*/
const submitReviewPost = async (e) =>{
    e.preventDefault();
    const form = e.target;

    const text = form.elements.text.value;
    const stars = form.elements.stars.value;
    const data = { text: text, stars: stars, product: productId };

    let result = await sendRequestAuth("reviews","POST",data,token);
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
        .then(fetchReviews)
    }
}

// recenze stejně jako všechny produkty na úvodní stránce jsou načítány klientem.
const fetchReviews = async () => {
    try{
        const data = await sendRequestWithoutDataAuth(`/reviews?productId=${productId}`,"GET",token)
        renderReviews(data)
    }catch(error){
        console.log(error)
    }
}

const renderReviews = (reviews) =>{
    const reviewDiv = document.querySelector('#reviewsDiv');
    reviewDiv.innerHTML = ''
    const helpDiv = document.createElement("div");
    reviews.forEach( (review) => { 
        if(review.author._id == userId){
            // recenze prihlaseneho uzivatele musi byt prvni
            userReview = review;
            reviewDiv.appendChild(createDOMReview(review));
            return;
        }
        helpDiv.appendChild(createDOMReview(review)) 
    })
    reviewDiv.append(helpDiv);
}

const createDOMReview = (review) => {
    const reviewElement = document.createElement("div");
    reviewElement.setAttribute("class","singleComment");

    const nameHeadline = document.createElement("h3");
    nameHeadline.textContent = review.author.email;
    reviewElement.appendChild(nameHeadline);

    const starImage = document.createElement("img");
    starImage.setAttribute("class","hvezdo");
    starImage.setAttribute("src",`img/hvezdy_${review.stars}.png`);
    starImage.setAttribute("alt","hvezdy");
    reviewElement.appendChild(starImage);

    if(review.author._id == userId){
        const modifyButton = document.createElement("button");
        modifyButton.textContent = "Uprav recenzi";
        modifyButton.setAttribute("id","modifyButton");
        modifyButton.addEventListener("click",modifyUserReview);
        reviewElement.appendChild(modifyButton);

        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Smaž recenzi";
        deleteButton.setAttribute("id","deleteButton");
        deleteButton.addEventListener("click",deleteUserReview);
        reviewElement.appendChild(deleteButton);
    }

    const cleanerDiv = document.createElement("div");
    cleanerDiv.setAttribute("class","cleaner");
    reviewElement.appendChild(cleanerDiv);

    const reviewContent = document.createElement("p");
    reviewContent.textContent = review.text;
    reviewElement.appendChild(reviewContent);

    return reviewElement;
}

const modifyUserReview = async () => {
    
const { value: formValues } = await Swal.fire({
    title: 'Změňte svou recenzi, člověče',
    html:
        '<textarea id="swal-text" class="swal2-textarea">'+userReview.text+'</textarea>' +
        '<select id="swal-stars" class="swal2-select">'+
            '<option checked value="5">Bez Chyby</option>'+
            '<option value="4">Výborné</option>'+
            '<option value="3">Průměrné</option>'+
            '<option value="2">Nic moc</option>'+
            '<option value="1">Nechutné</option>'+
        +'</select>',
    focusConfirm: false,
    preConfirm: () => {return [document.getElementById('swal-text').value,document.getElementById('swal-stars').value] }
    })

    if(formValues!=undefined){
        const data = {text:formValues[0],stars:formValues[1] };
        const result = await sendRequestAuth(`reviews?productId=${productId}`,"PATCH",data,token)
            
        if(result.errors){
            Swal.fire({
                icon: "error",
                title: "Nastala Chyba",
            })
        }else{
            Swal.fire({
            icon: "success",
            title: "Recenze úspěšně změněna",
            })
            .then(fetchReviews)
        }
    }
}

const deleteUserReview = async () => {
    const result = await Swal.fire({
        title: 'Jsi si jistý?',
        text: "Tato operace nejde vrátit zpět!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Ano, amaž to!'
    });

    if(!result.isDismissed){
        const fetchResult = await sendRequestWithoutDataAuth(`reviews/?productId=${productId}`,"DELETE",token)
        if(fetchResult.errors){
            Swal.fire({
                icon: "error",
                title: "Nastala Chyba",
            })
        }else{
            Swal.fire({
                icon: "success",
                title: "Recenze úspěšně vymazána",
            })
            .then(fetchReviews)
        }
    }
}