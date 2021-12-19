const shippingDetailsForm = document.querySelector("form[action=\"validateDeliveryDetails\"]");
if(shippingDetailsForm) shippingDetailsForm.addEventListener("submit",submitOrderForValidation);

const orderFinalForm = document.querySelector("form[action=\"completeOrder\"]");
if(orderFinalForm) orderFinalForm.addEventListener("submit",submitOrder);
