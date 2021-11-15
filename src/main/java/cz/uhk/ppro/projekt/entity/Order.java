package cz.uhk.ppro.projekt.entity;
/*
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "orders", schema = "cukrarna", catalog = "")
public class Order {
    private int orderId;
    private String city;
    private String email;
    private String firstaname;
    private int houseNumber;
    private String lastname;
    private String street;
    private int telNumber;
    private int zipCode;
    private String state;
    private int phonePrefix;
    private Timestamp ordered;
    private byte completed;

    @Id
    @Column(name = "ORDER_ID", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "CITY", nullable = false, length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 70)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "FIRSTANAME", nullable = false, length = 80)
    public String getFirstaname() {
        return firstaname;
    }

    public void setFirstaname(String firstaname) {
        this.firstaname = firstaname;
    }

    @Basic
    @Column(name = "HOUSE_NUMBER", nullable = false)
    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = false, length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "STREET", nullable = false, length = 100)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "TEL_NUMBER", nullable = false)
    public int getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    @Basic
    @Column(name = "ZIP_CODE", nullable = false)
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "STATE", nullable = false, length = 100)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PHONE_PREFIX", nullable = false)
    public int getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(int phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    @Basic
    @Column(name = "ORDERED", nullable = false)
    public Timestamp getOrdered() {
        return ordered;
    }

    public void setOrdered(Timestamp ordered) {
        this.ordered = ordered;
    }

    @Basic
    @Column(name = "COMPLETED", nullable = false)
    public byte getCompleted() {
        return completed;
    }

    public void setCompleted(byte completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (houseNumber != order.houseNumber) return false;
        if (telNumber != order.telNumber) return false;
        if (zipCode != order.zipCode) return false;
        if (phonePrefix != order.phonePrefix) return false;
        if (completed != order.completed) return false;
        if (city != null ? !city.equals(order.city) : order.city != null) return false;
        if (email != null ? !email.equals(order.email) : order.email != null) return false;
        if (firstaname != null ? !firstaname.equals(order.firstaname) : order.firstaname != null) return false;
        if (lastname != null ? !lastname.equals(order.lastname) : order.lastname != null) return false;
        if (street != null ? !street.equals(order.street) : order.street != null) return false;
        if (state != null ? !state.equals(order.state) : order.state != null) return false;
        if (ordered != null ? !ordered.equals(order.ordered) : order.ordered != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstaname != null ? firstaname.hashCode() : 0);
        result = 31 * result + houseNumber;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + telNumber;
        result = 31 * result + zipCode;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + phonePrefix;
        result = 31 * result + (ordered != null ? ordered.hashCode() : 0);
        result = 31 * result + (int) completed;
        return result;
    }
}*/
