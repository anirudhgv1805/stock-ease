package com.anirudhgv.stockease.model;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shipping_addresses")
public class ShippingAddress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    private String country;


    public ShippingAddress() {
    }

    public ShippingAddress(Long addressId, User user, String addressLine1, String addressLine2, String city, String state, String postalCode, String country) {
        this.addressId = addressId;
        this.user = user;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ShippingAddress addressId(Long addressId) {
        setAddressId(addressId);
        return this;
    }

    public ShippingAddress user(User user) {
        setUser(user);
        return this;
    }

    public ShippingAddress addressLine1(String addressLine1) {
        setAddressLine1(addressLine1);
        return this;
    }

    public ShippingAddress addressLine2(String addressLine2) {
        setAddressLine2(addressLine2);
        return this;
    }

    public ShippingAddress city(String city) {
        setCity(city);
        return this;
    }

    public ShippingAddress state(String state) {
        setState(state);
        return this;
    }

    public ShippingAddress postalCode(String postalCode) {
        setPostalCode(postalCode);
        return this;
    }

    public ShippingAddress country(String country) {
        setCountry(country);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ShippingAddress)) {
            return false;
        }
        ShippingAddress shippingAddress = (ShippingAddress) o;
        return Objects.equals(addressId, shippingAddress.addressId) && Objects.equals(user, shippingAddress.user) && Objects.equals(addressLine1, shippingAddress.addressLine1) && Objects.equals(addressLine2, shippingAddress.addressLine2) && Objects.equals(city, shippingAddress.city) && Objects.equals(state, shippingAddress.state) && Objects.equals(postalCode, shippingAddress.postalCode) && Objects.equals(country, shippingAddress.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, user, addressLine1, addressLine2, city, state, postalCode, country);
    }

    @Override
    public String toString() {
        return "{" +
            " addressId='" + getAddressId() + "'" +
            ", user='" + getUser() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", country='" + getCountry() + "'" +
            "}";
    }
    
}

