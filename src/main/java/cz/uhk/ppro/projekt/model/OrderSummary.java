package cz.uhk.ppro.projekt.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class OrderSummary{
    @Valid
    private DeliveryDetails deliveryDetails;
    private List<@Valid CartItem> cart;

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

    public static class DeliveryDetails {
        @Email(message = "Email není platný")
        @NotNull(message = "Email nebyl zadán")
        @NotBlank(message = "Email nebyl zadán")
        private String email;
        @Min(value = 420, message = "Neplatný prefix")
        @Max(value = 421, message = "Neplatný prefix")
        @NotNull(message = "Prefix nebyl zadán")
        private int phonePrefix;
        @Range(min= 100000000 ,max = 999999999, message = "Neplatné telefonní číslo")
        @NotNull(message = "Telefonní číslo nebylo zadáno")
        private int phoneNumber;
        @NotNull(message = "Jméno nebylo zadáno")
        @NotBlank(message = "Jméno nebylo zadáno")
        private String name;
        @NotNull(message = "Příjmení nebylo zadáno")
        @NotBlank(message = "Příjmení nebylo zadáno")
        private String surname;
        @NotNull(message = "Ulice nebyla zadána")
        @NotBlank(message = "Ulice nebyla zadána")
        private String street;
        @Min(value = 1,message = "Neplátné číslo domu")
        @NotNull(message = "Číslo domu nebylo zadáno")
        private int houseNumber;
        @NotNull(message = "Město nebylo zadáno")
        @NotBlank(message = "Město nebylo zadáno")
        private String city;
        @Range(min=10000, max=99999, message = "Neplatné PSČ")
        @NotNull(message = "PSČ nebylo zadáno")
        private int zipCode;
        @Min(value = 1, message = "Neplatné ID státu")
        @Max(value = 2,  message = "Neplatné ID státu")
        @NotNull(message = "Stát nebyl zadán")
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

    public static class CartItem {
        private int id;
        @Min(value = 1, message = "Neplatný košík")
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