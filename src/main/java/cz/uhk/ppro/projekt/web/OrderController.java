package cz.uhk.ppro.projekt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderController {

    @GetMapping("cart")
    public String renderCartPage(){
        return "cart";
    }

    @GetMapping("deliveryDetails")
    public String renderDeliveryDetailsPage(){
        return "deliveryDetails";
    }

    @GetMapping("summary")
    public String renderSummaryPage(){
        return "summary";
    }

    @PostMapping("deliveryDetails")
    public void summaryRequested(@RequestBody OrderSummary orderSummary){
        System.out.println(orderSummary);
    }

    private static class OrderSummary{
        private DeliveryDetails deliveryDetails;
        private List<CartItem> cart;

        public DeliveryDetails getDeliveryDetails() {
            return deliveryDetails;
        }

        public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
            this.deliveryDetails = deliveryDetails;
        }

        public List<CartItem> getCart() {
            return cart;
        }

        public void setCart(List<CartItem> cart) {
            this.cart = cart;
        }

        @Override
        public String toString() {
            return "OrderSummary{" +
                    "deliveryDetails=" + deliveryDetails +
                    ", cart=" + cart +
                    '}';
        }

        private static class DeliveryDetails {
            private String email;
            private int phonePrefix;
            private int phoneNumber;
            private String name;
            private String surname;
            private String street;
            private int houseNumber;
            private String city;
            private int zipCode;
            private int state;

            @Override
            public String toString() {
                return "DeliveryDetails{" +
                        "email='" + email + '\'' +
                        ", phonePrefix=" + phonePrefix +
                        ", phoneNumber=" + phoneNumber +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", street='" + street + '\'' +
                        ", houseNumber=" + houseNumber +
                        ", city='" + city + '\'' +
                        ", zipCode=" + zipCode +
                        ", state=" + state +
                        '}';
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getPhonePrefix() {
                return phonePrefix;
            }

            public void setPhonePrefix(int phonePrefix) {
                this.phonePrefix = phonePrefix;
            }

            public int getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(int phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSurname() {
                return surname;
            }

            public void setSurname(String surname) {
                this.surname = surname;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public int getHouseNumber() {
                return houseNumber;
            }

            public void setHouseNumber(int houseNumber) {
                this.houseNumber = houseNumber;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getZipCode() {
                return zipCode;
            }

            public void setZipCode(int zipCode) {
                this.zipCode = zipCode;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }

        private static class CartItem {
            private int id;
            private int ammount;

            @Override
            public String toString() {
                return "CartItem{" +
                        "id=" + id +
                        ", ammount=" + ammount +
                        '}';
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAmmount() {
                return ammount;
            }

            public void setAmmount(int ammount) {
                this.ammount = ammount;
            }
        }

    }
}