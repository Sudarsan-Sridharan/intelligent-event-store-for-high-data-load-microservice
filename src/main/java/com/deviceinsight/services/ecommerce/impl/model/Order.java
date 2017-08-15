package com.deviceinsight.services.ecommerce.impl.model;

import com.deviceinsight.services.model.IProduct;
import com.deviceinsight.services.model.dao.BaseUUIDEntity;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "orders")
public class Order extends BaseUUIDEntity implements IProduct {

    private String firstName;
    private String lastName;
    private String streetName;
    private String streetNumber;
    private int postalCode;
    private String city;
    private String payment;

    public Order() {
    }

    public Order(String firstName, String lastName, String streetName, String streetNumber, int postalCode, String city, String payment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.payment = payment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
